package services

import repo.Repository
import log.HasLogger

abstract class Service[Entity](repo: Repository[Entity]) extends HasLogger {

}
