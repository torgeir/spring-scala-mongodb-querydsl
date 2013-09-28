package configuration

import com.google.code.morphia.Morphia
import com.mongodb.Mongo
import org.springframework.scala.context.function.FunctionalConfiguration
import repo.UserRepository
import services.UserService
import log.HasNullLogger

class MongoConfigMorphia extends FunctionalConfiguration {

  val mongo = singleton() {
    new Mongo("localhost")
  }

  val morphia = singleton() {
    new Morphia().mapPackage("gd.wa")
  }

  val userRepository = singleton() {
    new UserRepository(morphia().createDatastore(mongo(), "users-db"), morphia())
      with HasNullLogger
  }

  val userService = singleton() {
    new UserService(userRepository())
  }

}
