import compiler
from compiler.ast import *

strcount = 0
class Annot(Node):
    def __init__(self,annot):
        self.annot = annot
    def __repr__(self):
        return '\nAnnot(%s)\n' % repr(self.annot)
    def getChildren(self):
        return self.annot,
    def getChildNodes(self):
        return ()

class UClass(Node):
    def __init__(self,node):
        self.node = node
        self.init = None
        for stmt in node.code:
            print stmt.__class__
            if isinstance(stmt,Function) and stmt.name=='__init__':
                self.init = stmt
    def __repr__(self):
        node = self.node
        return '\nUClass(%s, %s, %s, %s, %s)\n' % (repr(node.name),repr(node.bases), repr(node.doc), repr(node.code), repr(self.init))
    def getChildNodes(self):
        return self.node.getChildNodes()
    def getChildren(self):
        node = self.node
        children = []
        children.append(node.nname)
        children.extend(flatten(node.bases))
        children.append(node.doc)
        children.append(node.code)
        children.append(self.init)
        return tuple(children)

class CastNode(Node):
	def __init__(self,cast,child):
		self.child = child
		self.cast = cast
	def __repr__(self):
		return cast+'( %s )',repr(self.child)
	def getChildNodes(self):
		return (self.child,)
	def getChildren(self):
		return (self.child,)
def identifyCasts(ast):
	"""its currently useless. Stupid Python AST is hard to modify"""
	nodes = ast.getChildNodes()
	for i in range(len(nodes)):
		child = nodes[i]
		if isinstance(child,compiler.ast.CallFunc):
			funcexpr = child.getChildNodes()[0]
			if isinstance(funcexpr,compiler.ast.Name):
				name = funcexpr.name
				if name=="float32":
					print child


def isAnnot(node):
    if isinstance(node,Discard) and isinstance(node.expr,Const) and isinstance(node.expr.value,str):
        return True
    return False

class ASTPrinter(object):
    def __init__(self):
        self.strings = []
    def visitConst(self,node):
        global strcount
        if isinstance(node.value,str):
            self.strings.append(node.value)
            node.value = 'PyString ( '+repr(len(self.strings)-1)+' )'
    def visitStmt(self,node):
        nodes = node.nodes
        for i in range(len(nodes)):
            if isAnnot(nodes[i]):
                self.visitConst(nodes[i].expr)
                nodes[i] = Annot(nodes[i].expr.value)
            elif isinstance(nodes[i],Class):
                nodes[i] = UClass(nodes[i])
        for child in nodes:
            compiler.visitor.walk(child,self)
    def visitUClass(self,node):
        node.node.doc = None
        for child in node.getChildNodes():
            compiler.visitor.walk(child,self)
