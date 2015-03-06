package org.unpython.newscala
import org.unpython.compiler._;
import org.unpython.compiler.types._;
import org.unpython.compiler.frontend._;
import scala.MatchError;
import java.util.HashSet;
import java.util.HashMap;
import scala.collection.mutable.Queue;
//Livness analysis : function -> annotated tree of function
object Liveness{
    def computeLiveVars(tree:PyTree) = {
        //println(tree.map);
        var iters = 0;
        tree.getType match{
            case PythonParser.FUNCTION => {
                var changed = true;
                while(changed){
                    changed = liveVarsHelper(tree.getFunBody);
                    iters += 1;
                }
            }
            case _ => new MatchError("match error " +tree.toStringTree);
        }
       // println("Took "+iters+" iterations ");
    }
    def getReadSet(expr:PyTree):HashSet[Integer] = {
        val readset = new HashSet[Integer];
        val queue = new Queue[PyTree];
        queue += expr;
        while(! queue.isEmpty){
            val front = queue.dequeue;
            if(front.getType==PythonParser.NAME) {
              readset.add(front.symindex);
            }
            else{
                for(i <- 0 to front.getChildCount()-1){
                    queue += (front.getChild(i));
                }
            }
        }
        if(readset.contains(-1)) {
        //this should not happen, only for debug
          println("Readset contains -1: "+expr.toStringTree);
          readset.remove(-1);
        }
        return readset;
    }
    def liveVarsHelper(tree:PyTree):boolean = {
        var changed = false;
        tree.getType match{
          case PythonParser.STMT => {
            changed = changed | addToSet(tree.getChild(tree.getChildCount()-1).liveAfter,tree.liveAfter);
            for(i <- (tree.getChildCount() -1).until(0,-1)){
                val prevchild = tree.getChild(i-1);
                changed = changed | liveVarsHelper(tree.getChild(i));
                changed = changed | addToSet(tree.getChild(i-1).liveAfter,tree.getChild(i).liveBefore);
            }
            changed = changed | liveVarsHelper(tree.getChild(0));
            changed = changed | addToSet(tree.liveBefore,tree.getChild(0).liveBefore);
            return changed;
          }
          case PythonParser.ASSIGN => {
            val lhs = tree.getAssignList.getChild(0);
            val readset = getReadSet(tree.getAssignExpr);
            //assignment to primtive, subscript of array and to object can be handled
            lhs.getType match{
                case PythonParser.ASSNAME => {
                    val temp:HashSet[Integer] = tree.liveAfter.clone.asInstanceOf[HashSet[Integer]];
                    temp.remove(lhs.symindex);
                    changed = changed | addToSet(tree.liveBefore,temp);
                }
                case PythonParser.SUBSCRIPTS => {
                    val subread = getReadSet(lhs);
                    changed = changed | addToSet(tree.liveBefore,tree.liveBefore);
                    changed = changed | addToSet(tree.liveBefore,subread);
                }
                case _ =>{
                    throw new MatchError("assignment type not supported in liveness analysis");
                }
            }
            changed = changed | addToSet(tree.liveBefore,readset);
            return changed;
            
          }
          //should also handle parallel loop here
          case PythonParser.FOR | PythonParser.CFOR | PythonParser.PARFOR | PythonParser.GPUFOR => {
            val loopCounterName = tree.getChild(0).getAssName();
            //println("Computing for "+loopCounterName);
            val inittree = tree.getChild(1).getChild(0);
            val testtree = tree.getChild(1).getChild(1);
            val finaltree = tree.getChild(1).getChild(2);
            val bodytree = tree.getChild(2);
            
            val loopid = tree.getChild(0).symindex;
            val finalReadSet = getReadSet(finaltree);
            val testReadSet = getReadSet(testtree);
            val initReadSet = getReadSet(inittree);

            changed = changed | addToSet(testtree.liveAfter,tree.liveAfter);
            changed = changed | addToSet(testtree.liveBefore,testtree.liveAfter);
            changed = changed | addToSet(testtree.liveBefore,testReadSet);

            changed = changed | addToSet(bodytree.liveAfter,finaltree.liveBefore);
            changed = changed | liveVarsHelper(bodytree);
            changed = changed | addToSet(testtree.liveAfter,bodytree.liveBefore);

            changed = changed | addToSet(finaltree.liveAfter,testtree.liveBefore);
            changed = changed | addToSet(finaltree.liveBefore,finaltree.liveAfter);
            changed = changed | addToSet(finaltree.liveBefore,finalReadSet);

            changed = changed | addToSet(inittree.liveAfter,testtree.liveBefore);
            val temp:HashSet[Integer] = inittree.liveAfter.clone.asInstanceOf[HashSet[Integer]]; 
            temp.remove(loopid);
            changed = changed | addToSet(inittree.liveBefore,temp);
            changed = changed | addToSet(inittree.liveBefore,initReadSet);

            changed = changed | addToSet(tree.liveBefore,inittree.liveBefore);
            //println(loopCounterName+" "+tree.liveBefore+" "+tree.liveAfter);
            return changed;
          }
          case PythonParser.RETURN => {
            return false;
          }
          case _ => { throw new MatchError("match error "+tree.toStringTree)}


        }
    }
    
    def addToSet(base:HashSet[Integer],toadd:HashSet[Integer]):Boolean = {
        val setiter = toadd.iterator;
        var changed = false;
        while(setiter.hasNext){
            val next = setiter.next;
            val added = base.add(next);
            changed = changed | added;
        }
        return changed;
    }
    
    def setUnion(set1:HashSet[Integer],set2:HashSet[Integer]):HashSet[Integer] = {
        val union = new HashSet[Integer];
        val iter1 = set1.iterator;
        while(iter1.hasNext) union.add(iter1.next);
        val iter2 = set2.iterator;
        while(iter2.hasNext) union.add(iter2.next);
        return union;
    }
    def liveAtAnyPoint(tree:PyTree):HashSet[Integer] = {
        val set = new HashSet[Integer];
        tree.getType match{
            case PythonParser.STMT => {
                for(i <- 0 to (tree.getChildCount()-1)){
                    addToSet(set,liveAtAnyPoint(tree.getChild(i)));    
                }
            }
            case PythonParser.CFOR => {
                addToSet(set,tree.liveAfter);
                addToSet(set,tree.liveBefore);
                addToSet(set,liveAtAnyPoint(tree.getChild(2)));
            }
            case _ => {
                addToSet(set,tree.liveBefore);
                addToSet(set,tree.liveAfter);
            }
        }
        if(set.contains(-1)) {
        //this should never happen, only for debug
          println(tree.toStringTree);
          set.remove(-1);
        }
        
        return set;
    }

    def liveInBody(loop:PyTree):HashSet[Integer] = {
        val body = loop.getChild(2);
        val excludeSet =  setUnion(loop.liveBefore,loop.liveAfter);
        val live = liveAtAnyPoint(body);
        val set = new HashSet[Integer];
        val liveiter = live.iterator;
        while(liveiter.hasNext){
            val next = liveiter.next;
            if(!excludeSet.contains(next)) set.add(next);
        }
        //println("Found liveInBody "+set);
        return set;
    }

}

