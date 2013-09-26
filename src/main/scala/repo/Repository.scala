package repo

import com.google.code.morphia.{Morphia, Datastore}
import com.mysema.query.mongodb.morphia.MorphiaQuery
import com.mongodb.{WriteResult, WriteConcern}
import com.mysema.query.scala.EntityPathImpl
import gd.wa.{QUser, User}

abstract class Repository[Entity](val mongo: Datastore, morphia: Morphia, queryEntity: EntityPathImpl[Entity]) {

  val query: MorphiaQuery[Entity] = new MorphiaQuery[Entity](morphia, mongo, queryEntity)

  def save(entity: Entity) = mongo.save(entity, WriteConcern.SAFE)

}
