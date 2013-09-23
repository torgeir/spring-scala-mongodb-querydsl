import com.google.code.morphia.{Morphia, Datastore}
import com.mongodb.WriteConcern
import com.mysema.query.mongodb.morphia.MorphiaQuery
import com.mysema.query.types.expr.BooleanExpression
import com.mysema.query.types.Expression
import gd.wa._

import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory
import org.springframework.scala.context.function.FunctionalConfigApplicationContext
import scala.collection.JavaConverters._

object App {

  def main (args: Array[String]) = {


    val applicationContext = FunctionalConfigApplicationContext[MongoConfig]
    val mongoOperations = applicationContext[MongoOperations]

    val factory: MongoRepositoryFactory = new MongoRepositoryFactory(mongoOperations)
    val questionRepository: QuestionRepository = factory.getRepository(classOf[QuestionRepository])

    def odd(n: Int): Boolean = n % 2 == 0

    val torgeir = new User("Torgeir")
    val per = new User("Per")

    Range(1, 100).foreach { n =>
      val question = new Question(s"question-id-$n", n, if (odd(n)) per else torgeir)
      questionRepository.save(question)
    }

    import scala.collection.JavaConversions._

    val question: QQuestion = QQuestion.question
    val questions: Iterable[Question] =
      questionRepository.findAll(
        question.user.name.equalsIgnoreCase("torgeir") and question.questionId.contains("2"))

    for (q <- questions) {
      println(q)
    }

  }
}
