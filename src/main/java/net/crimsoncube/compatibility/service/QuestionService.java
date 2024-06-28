package net.crimsoncube.compatibility.service;

import net.crimsoncube.compatibility.api.v1.request.QuestionAnswerRequest;
import net.crimsoncube.compatibility.api.v1.response.exposed.AnswerDto;
import net.crimsoncube.compatibility.entity.*;
import net.crimsoncube.compatibility.repository.AnswerRepository;
import net.crimsoncube.compatibility.repository.QuestionRepository;
import net.crimsoncube.compatibility.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private static final Logger log = LogManager.getLogger(QuestionService.class);
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(UserRepository userRepository, AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    public List<Question> getCurrentQuestions(String userName) {

        try {
            User user = userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("Username not found: " +userName));
            return questionRepository.findUnansweredQuestionsByUser(user.getId());
        } catch (UsernameNotFoundException e) {
            log.warn(e.getMessage());
        }
        return new ArrayList<>();
    }

    public boolean answerQuestion(String userName, QuestionAnswerRequest answerRequest) {

        log.info("User {} answered question {} with {}", userName, answerRequest.getQuestionId(), answerRequest.getAnswer());

        if(answerRequest.getAnswer() == -1) {
            return false;
        }

        try {
            User user = userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("User not found: " + userName));

            UserAnswer answer = new UserAnswer();
            answer.setAnswer(answerRequest.getAnswer());
            UserAnswerId id = new UserAnswerId();
            id.setQuestionId(answerRequest.getQuestionId());
            id.setUserId(user.getId());
            answer.setId(id);

            answerRepository.save(answer);

            return true;

        } catch (UsernameNotFoundException e) {
            log.warn("Tired to answer question for unknown user {}", userName);
            return false;
        }
    }

    public List<AnswerDto> getAnsweredQuestions(String username) {

        List<AnswerDto> result = new ArrayList<>();

        try {
            User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(""));
            answerRepository.findByUserId(user.getId()).forEach(a -> {

                AnswerDto answer = new AnswerDto((String)a.get("question_text"), (Integer)a.get("answer"), (Long)a.get("question_id"));
                result.add(answer);
            });

            return result;

        } catch (UsernameNotFoundException e) {
            log.error("Username {} not found when trying to get answered questions", username);
        }

        return result;
    }
}
