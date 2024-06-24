package net.crimsoncube.compatibility.service;

import net.crimsoncube.compatibility.api.v1.request.QuestionAnswerRequest;
import net.crimsoncube.compatibility.entity.Question;
import net.crimsoncube.compatibility.entity.QuestionMeta;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private static final Logger log = LogManager.getLogger(QuestionService.class);

    public List<Question> getCurrentQuestions(String userName) {

        // List<Question> result = new ArrayList<>();
        // TODO: Add code
        // return result;
        return stubQuestions();

    }

    public boolean answerQuestion(String userName, QuestionAnswerRequest answerRequest) {

        // whee

        return false;
    }


    // DEV -----------------------------------------------------------------
    private List<Question> stubQuestions() {

        List<Question> result = new ArrayList<>();

        Question q1 = new Question();
        q1.setId(1L);
        q1.setQuestionText("How are you?");
        QuestionMeta m1 = new QuestionMeta();
        m1.setId(1L);
        q1.setMeta(m1);

        result.add(q1);

        Question q2 = new Question();
        q2.setId(2L);
        q2.setQuestionText("How tired are you?");
        QuestionMeta m2 = new QuestionMeta();
        m2.setId(2L);
        q2.setMeta(m2);

        result.add(q2);

        return result;
    }
}
