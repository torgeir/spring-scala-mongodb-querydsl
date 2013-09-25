
import org.springframework.data.mongodb.core.mapping.Document

@Document
case class User(val name: String) {

}
