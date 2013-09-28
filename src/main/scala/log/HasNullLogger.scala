package log

trait HasNullLogger extends HasLogger {
  override lazy val logger = new NullLogger
}

