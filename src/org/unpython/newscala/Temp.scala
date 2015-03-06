import org.unpython.compiler._
import org.unpython.compiler.frontend._

object Name{
    def unapply(tree:PyTree):Option[String] = {
        tree.getType() match{
          case PythonParser.NAME => Some(tree.getNameString)
          case _ => None
        }
    }
}


