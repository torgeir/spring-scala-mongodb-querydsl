package gd.wa

import org.bson.types.ObjectId
import com.mysema.query.annotations.QueryEntity
import com.google.code.morphia.annotations.Id

@QueryEntity
case class User(@Id _id: ObjectId,
                username: String,
                name: String) {

  private def this() = this(null, null, null)

  override def toString = s"User(${_id}, $username, $name)"
}

object User {
  def apply(username: String, name: String) = new User(null, username, name)
}