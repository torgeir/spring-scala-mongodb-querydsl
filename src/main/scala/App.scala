import org.springframework.data.mongodb.core.{MongoTemplate, MongoOperations}
import org.springframework.scala.context.function.FunctionalConfigApplicationContext

object App {

  def main (args: Array[String]) = {

    val applicationContext = FunctionalConfigApplicationContext[MongoConfig]
    val mongo: MongoOperations = applicationContext[MongoTemplate]

    val question = new Question("questionId-1")
    mongo.save(question)

    println("Saved.")

  }
}
