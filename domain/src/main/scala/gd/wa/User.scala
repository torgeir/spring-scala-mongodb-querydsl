package gd.wa

import org.bson.types.ObjectId
import com.google.code.morphia.annotations.{Entity, Id}
import scala.annotation.meta.field

@Entity
case class User(@(Id @field) id: ObjectId,
                username: String,
                name: String) extends Document {
  private def this() = this(null, null, null)
  override def toString = s"User($id, $username, $name)"
}

object User {
  def apply(username: String, name: String) = new User(null, username, name)
}