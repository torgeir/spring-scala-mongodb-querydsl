import com.google.code.morphia.{Datastore, Morphia}
import com.mongodb._
import gd.wa.Question
import java.util
import org.springframework.data.mongodb.core.aggregation.{Aggregation, AggregationResults, TypedAggregation}
import org.springframework.data.mongodb.core.convert.MongoConverter
import org.springframework.data.mongodb.core.geo.GeoResults
import org.springframework.data.mongodb.core.mapreduce.{GroupByResults, GroupBy, MapReduceOptions, MapReduceResults}
import org.springframework.data.mongodb.core._
import org.springframework.data.mongodb.core.query.{Criteria, NearQuery, Update, Query}
import org.springframework.scala.context.function.{BeanLookupFunction, FunctionalConfiguration}

class MongoConfig extends FunctionalConfiguration {

  val mongo: BeanLookupFunction[Mongo] =
    singleton() {
      new Mongo("localhost")
    }

  val mongoOperations: BeanLookupFunction[MongoOperations] = bean() {
    new MongoTemplate(mongo(), "questions-db")
  }

//  val morphia: BeanLookupFunction[Morphia] =
//    singleton() {
      /** https://github.com/mongodb/morphia/wiki/MappingObjects */
//      new Morphia().map(classOf[Question])

      /** or */
      //morphia.mapPackage("my.package.with.only.mongo.entities");
//  }

//  val datastore: BeanLookupFunction[Datastore] =
//    singleton() {
//      morphia().createDatastore(mongo(), "questions-db")
//    }

}
