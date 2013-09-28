import configuration.MongoConfigMorphia
import gd.wa.User
import org.springframework.scala.context.function.FunctionalConfigApplicationContext
import services.UserService

object App {

  def main(args: Array[String]) = {

    val applicationContext = FunctionalConfigApplicationContext[MongoConfigMorphia]
    val userService = applicationContext[UserService]

    userService.removeAllUsers()

    Range(0, 101).foreach {
      n => userService.createUser(s"user-$n@example.com", s"User $n")
    }

    println("number of users", userService.numberOfUsers)

    val email = "user-23@example.com"
    val user = userService.findUserByUsername(email)
    println("found user", user.getOrElse(s"no user found for $email"))

    val user2 = userService.findUserByUsername(email)
    println("equal users", user == user2, user.get, user2.get)

    val user42: Option[User] = userService.findUserNumber(42)
    println(user42)

    val updatedUser42: Option[User] = user42.map {
      u => userService.updateNameForUser(s"${u.name} with new name", u)
    }
    println(updatedUser42)

  }
}