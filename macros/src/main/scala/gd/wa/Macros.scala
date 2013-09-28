import scala.reflect.macros.Context
import scala.util.matching.Regex

object Macros {

  import scala.language.experimental.macros

  def regex(str: String): Regex = macro regexImpl

  def regexImpl(c: Context)
               (str: c.Expr[String]): c.Expr[Regex] = {

    import c.universe._

    str.tree match {
      case Literal(Constant(string: String)) =>
        string.r
        reify { str.splice.r }
    }
  }
}
