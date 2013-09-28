package log

import com.typesafe.scalalogging.slf4j.{Logger, Logging}
import org.slf4j.LoggerFactory

class TypesafeLogger(self: Class[_]) extends ILog with Logging {

  private lazy val log = Logger(LoggerFactory.getLogger(self.getName))

  def debug(msg: String, args: Any*) = log.debug(msg, args)
  def info(msg: String, args: Any*)  = log.info(msg, args)
  def warn(msg: String, args: Any*)  = log.warn(msg, args)
  def error(msg: String, args: Any*) = log.error(msg, args)
}
