package scala

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.querydsl.QueryDslPredicateExecutor
import org.bson.types.ObjectId
import gd.wa.Question

trait QuestionRepository extends MongoRepository[Question, ObjectId]
                         with QueryDslPredicateExecutor[Question] {
}
