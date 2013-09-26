package configuration

import com.google.code.morphia.Morphia
import com.mongodb.Mongo
import org.springframework.scala.context.function.{BeanLookupFunction, FunctionalConfiguration}
import repo.UserRepository
import services.UserService

class MongoConfigMorphia extends FunctionalConfiguration {

  val mongo: BeanLookupFunction[Mongo] = singleton() {
    new Mongo("localhost")
  }

  val morphia: BeanLookupFunction[Morphia] = singleton() {
    new Morphia().mapPackage("gd.wa")
  }

  val userRepository: BeanLookupFunction[UserRepository] = singleton() {
    new UserRepository(morphia().createDatastore(mongo(), "users-db"), morphia())
  }

  val userService: BeanLookupFunction[UserService] = singleton() {
    new UserService(userRepository())
  }

}
