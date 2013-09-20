import com.mongodb.Mongo
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.scala.context.function.{BeanLookupFunction, FunctionalConfiguration}

class MongoConfig extends FunctionalConfiguration {

  val mongo: BeanLookupFunction[Mongo] =
    bean() {
      new Mongo("localhost")
    }

  val mongoTemplate: BeanLookupFunction[MongoTemplate] =
    bean() {
      new MongoTemplate(mongo(), "questions")
    }
}
