package services

import gd.wa.{QUser, User}
import repo.UserRepository

class UserService(repo: UserRepository) extends Service[User](repo) {

  import com.mysema.query.scala.Helpers._

  def createUser(username: String, name: String): User = {
    findUserByUsername(username) match {
      case Some(user) => user
      case None => {
        val user = User(username, name)
        repo.save(user)
        findUserByUsername(username).get
      }
    }
  }

  def findUserByUsername(username: String) =
    repo.query.where(QUser.username.eq(username)).single

  def findUserNumber(number: Int) =
    repo.query.where(QUser.username.contains(number.toString)).single

  def numberOfUsers = repo.query.count()

  def removeAllUsers() =
    repo.deleteAll()
}
