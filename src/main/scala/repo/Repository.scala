package repo

import com.google.code.morphia.{Morphia, Datastore}
import com.mysema.query.mongodb.morphia.MorphiaQuery
import com.mongodb.WriteConcern
import com.mysema.query.scala.EntityPathImpl

abstract class Repository[Entity](val mongo: Datastore, morphia: Morphia, queryEntity: EntityPathImpl[Entity]) {

  def query: MorphiaQuery[Entity] = new MorphiaQuery[Entity](morphia, mongo, queryEntity)

  def save(entity: Entity) = {
    mongo.save(entity, WriteConcern.ACKNOWLEDGED)
    entity
  }

}
