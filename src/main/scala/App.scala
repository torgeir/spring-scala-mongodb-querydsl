import com.google.code.morphia.{Morphia, Datastore}
import com.mongodb.WriteConcern
import com.mysema.query.mongodb.morphia.MorphiaQuery
import gd.wa._

import org.springframework.scala.context.function.FunctionalConfigApplicationContext

object App {

  def main (args: Array[String]) = {

    import com.mysema.query.scala.Helpers._

    val applicationContext = FunctionalConfigApplicationContext[MongoConfig]
    val db: Datastore = applicationContext[Datastore]

    db.delete(db.createQuery(classOf[Question]))

    def odd(n: Int): Boolean = n % 2 == 0

    val torgeir = new User("Torgeir")
    val per = new User("Per")

    Range(1, 100).foreach { n =>
      val question = new Question(s"question-id-$n", n, if (odd(n)) per else torgeir)
      db.save(question, WriteConcern.NORMAL)
    }
    println(s"Saved 100 questions.")

    val query = new MorphiaQuery[Question](applicationContext[Morphia], db, QQuestion)
    val list: List[Question] = query
        .where(QQuestion.user.name.equalsIgnoreCase("torgeir")
           and QQuestion.number > 50)
        .select

    println(s"Torgeir has ${list.size} questions with number > 50")
  }
}
