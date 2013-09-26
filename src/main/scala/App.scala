import configuration.MongoConfigMorphia
import gd.wa.{QUser, User}
import org.springframework.scala.context.function.FunctionalConfigApplicationContext
import repo.UserRepository

object App {

  def main (args: Array[String]) = {

    val applicationContext = FunctionalConfigApplicationContext[MongoConfigMorphia]
    val repo: UserRepository = applicationContext[UserRepository]

    repo.deleteAll()

    Range(1, 100).foreach {
      n =>
        val user = if (n % 2 == 0) User("torgeir") else User("per")
        repo.save(user)
    }

    import com.mysema.query.scala.Helpers._

    val list = repo.query.where(QUser.name.equalsIgnoreCase("torgeir")).select
    println(list.size) // 49
  }

}