package gd.wa

import org.bson.types.ObjectId
import com.mysema.query.annotations.QueryEntity
import com.google.code.morphia.annotations.Id

@QueryEntity
case class User(@Id _id: ObjectId, name: String) {

  private def this() = this(null, null)

  override def toString = s"User(${_id}, $name)"

}

object User {
  def apply(name: String) = new User(null, name)
}