package configuration

import com.mongodb.Mongo
import org.springframework.data.mongodb.core.{MongoOperations, MongoTemplate}
import org.springframework.scala.context.function.{FunctionalConfiguration, BeanLookupFunction}

@Deprecated
class MongoConfigSpringData extends FunctionalConfiguration{

  val mongo: BeanLookupFunction[Mongo] = singleton() {
      new Mongo("localhost")
    }

  val mongoOperations: BeanLookupFunction[MongoOperations] = bean() {
    new MongoTemplate(mongo(), "users-db")
  }

//  val mongoRepositoryFactory: BeanLookupFunction[MongoRepositoryFactory] = singleton() {
//    new MongoRepositoryFactory(mongoOperations())
//  }

//  val userRepo: BeanLookupFunction[SpringDataUserRepository] = bean() {
//    mongoRepositoryFactory().getRepository(classOf[SpringDataUserRepository])
//  }

}
