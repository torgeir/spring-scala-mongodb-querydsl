package repo

import com.google.code.morphia.{Morphia, Datastore}
import gd.wa.{QUser, User}
import com.mongodb.WriteResult


class UserRepository(mongo: Datastore, morphia: Morphia)
  extends Repository[User](mongo: Datastore, morphia: Morphia, QUser) {

  def deleteAll(): WriteResult = mongo.delete(mongo.createQuery(classOf[User]))

}
