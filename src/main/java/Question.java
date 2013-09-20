import com.mysema.query.annotations.QueryEntity;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@QueryEntity
public class Question {

    @Id
    ObjectId id;

    String questionId;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
