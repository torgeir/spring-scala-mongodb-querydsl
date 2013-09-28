package services

import gd.wa.User
import repo.UserRepository

class UserService(repo: UserRepository) extends Service[User](repo) {

  import com.mysema.query.scala.Helpers._
  import gd.wa.{QUser => user}

  def createUser(username: String, name: String): User = {
    findUserByUsername(username) match {
      case Some(user) => {
        logger.info(s"User with username $username already exists, returning existing one")
        user
      }
      case None => {
        logger.info(s"Creating user with username $username")
        repo.save(User(username, name))
      }
    }
  }

  def findUserByUsername(usernameToFind: String) = repo.query.where(user.username.eq(usernameToFind)).single

  def findUserNumber(number: Int) = repo.query.where(user.username.contains(number.toString)).single

  def numberOfUsers = repo.query.count()

  def removeAllUsers() = repo.deleteAll()
}
