package log

trait HasLogger {
  lazy val logger: ILog = new TypesafeLogger(getClass)
}

