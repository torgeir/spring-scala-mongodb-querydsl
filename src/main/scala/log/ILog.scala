package log

trait ILog {
  def debug(msg: String, args: Any*): Unit
  def info(msg: String, args: Any*): Unit
  def error(msg: String, args: Any*): Unit
  def warn(msg: String, args: Any*): Unit
}

