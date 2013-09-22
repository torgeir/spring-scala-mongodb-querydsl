import com.google.code.morphia.{Datastore, Morphia}
import com.mongodb.Mongo
import gd.wa.Question
import org.springframework.scala.context.function.{BeanLookupFunction, FunctionalConfiguration}

class MongoConfig extends FunctionalConfiguration {

  val mongo: BeanLookupFunction[Mongo] =
    singleton() {
      new Mongo("localhost")
    }

  val morphia: BeanLookupFunction[Morphia] =
    singleton() {
      /** https://github.com/mongodb/morphia/wiki/MappingObjects */
      new Morphia().map(classOf[Question])

      /** or */
      //morphia.mapPackage("my.package.with.only.mongo.entities");
  }

  val datastore: BeanLookupFunction[Datastore] =
    singleton() {
      morphia().createDatastore(mongo(), "questions-db")
    }

}
