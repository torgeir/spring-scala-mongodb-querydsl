
class MongoConfig {

//  val mongo: BeanLookupFunction[Mongo] =
//    singleton() {
//      new Mongo("localhost")
//    }
//
//  val mongoOperations: BeanLookupFunction[MongoOperations] = bean() {
//    new MongoTemplate(mongo(), "questions-db")
//  }

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
