package services

import repo.Repository

abstract class Service[Entity](repo: Repository[Entity]) {

}
