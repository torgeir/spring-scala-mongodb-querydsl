package log

class NullLogger extends ILog {
   def debug(msg: String, args: Any*) = {}
   def info(msg: String, args: Any*)  = {}
   def warn(msg: String, args: Any*)  = {}
   def error(msg: String, args: Any*) = {}
 }
