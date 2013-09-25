import com.mysema.query.codegen.GenericExporter
import com.mysema.query.scala.{ScalaTypeMappings, ScalaEntitySerializer}
import java.io.File
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.mapping.Document

object CreateQuerydslClasses {

  def main(args: Array[String]) = {

    println("Creating querydsl classes")

    val exporter = new GenericExporter()
    exporter.setTargetFolder(new File(args(0)))
    exporter.setSerializerClass(classOf[ScalaEntitySerializer])
    exporter.setTypeMappingsClass(classOf[ScalaTypeMappings])
    exporter.setEntityAnnotation(classOf[Document])
    exporter.setSkipAnnotation(classOf[Transient])
    exporter.setCreateScalaSources(true)
    exporter.export(classOf[User])

  }
}
