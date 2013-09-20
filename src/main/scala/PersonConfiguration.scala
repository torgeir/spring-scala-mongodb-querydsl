import org.springframework.scala.context.function.FunctionalConfiguration

class PersonConfiguration extends FunctionalConfiguration {
  bean() {
    new Person("John", "Doe")
  }
}
