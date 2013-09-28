import com.google.code.morphia.annotations.Entity
import com.mysema.query.annotations._
import com.mysema.query.codegen.GenericExporter
import com.mysema.query.scala.{ScalaTypeMappings, ScalaEntitySerializer}
import java.io.File

object CreateQuerydslClasses {

  def main(args: Array[String]) = {

    println("Creating querydsl classes")

    val exporter = new GenericExporter()
    exporter.setTargetFolder(new File(args(0)))
    exporter.setEntityAnnotation(classOf[Entity])
    exporter.setSkipAnnotation(classOf[QueryTransient])
    exporter.setSupertypeAnnotation(classOf[QuerySupertype])
    exporter.setEmbeddableAnnotation(classOf[QueryEmbeddable])
    exporter.setEmbeddedAnnotation(classOf[QueryEmbedded])
    exporter.setSkipAnnotation(classOf[QueryExclude])

    exporter.setSerializerClass(classOf[ScalaEntitySerializer])
    exporter.setTypeMappingsClass(classOf[ScalaTypeMappings])
    exporter.setCreateScalaSources(true)

    exporter.export("gd.wa")
  }

  /*
  class ScalaEntityWithStaticSerializer @Inject()(override val typeMappings: TypeMappings) extends ScalaEntitySerializer(typeMappings) {

    override def writeCompanionObject(model: EntityType, queryType: Type, writer: ScalaWriter) = {
      val queryTypeName = writer.getRawName(queryType)
      val variable = model.getUncapSimpleName

      writer.beginObject(queryTypeName + " extends "+queryTypeName+"(\""+variable+"\")")
      writer.line("override def as(variable: String) = new ", queryTypeName, "(variable)")
      writer.line("")
      writer.line("val ", variable, " = new ", queryTypeName, "(\"", variable, "\")")
      writer.end()
    }
  }
  */
}
