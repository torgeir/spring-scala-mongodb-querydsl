import com.google.code.morphia.annotations.{Transient, Embedded, Entity}
import com.mysema.query.codegen.GenericExporter
import com.mysema.query.scala.{ScalaTypeMappings, ScalaEntitySerializer}
import gd.wa._

object GenerateScalaCode {

  def main (args: Array[String]) = {
    createScalaCode
  }

  def createScalaCode = {
    val exporter = new GenericExporter()
    exporter.setTargetFolder(new java.io.File("extra"))
    exporter.setSerializerClass(classOf[ScalaEntitySerializer])
    exporter.setTypeMappingsClass(classOf[ScalaTypeMappings])
    exporter.setEmbeddedAnnotation(classOf[Embedded])
    //    exporter.setEmbeddableAnnotation(classOf[Embeddable])
    exporter.setEntityAnnotation(classOf[Entity])
    //    exporter.setSupertypeAnnotation(classOf[MappedSuperclass])
    exporter.setSkipAnnotation(classOf[Transient])
    exporter.setCreateScalaSources(true)
    exporter.export(classOf[Question].getPackage)
  }

}
