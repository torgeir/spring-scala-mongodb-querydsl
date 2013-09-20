import org.springframework.scala.context.function.FunctionalConfigApplicationContext

object App {

  def main (args: Array[String]) = {

    val applicationContext = FunctionalConfigApplicationContext[PersonConfiguration]
    val john = applicationContext[Person]
    println(john.firstname)

    println("Works.")
  }
}
