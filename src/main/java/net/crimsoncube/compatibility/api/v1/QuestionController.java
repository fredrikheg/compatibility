package net.crimsoncube.compatibility.api.v1;

import net.crimsoncube.compatibility.api.v1.request.QuestionAnswerRequest;
import net.crimsoncube.compatibility.api.v1.response.CurrentQuestionsResponse;
import net.crimsoncube.compatibility.api.v1.response.MessageResponse;
import net.crimsoncube.compatibility.entity.Question;
import net.crimsoncube.compatibility.service.QuestionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class QuestionController {

    private static final Log log = LogFactory.getLog(QuestionController.class);

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/api/question/current")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getCurrent(Principal principal) {

        List<Question> questions = questionService.getCurrentQuestions(principal.getName());
        CurrentQuestionsResponse response = new CurrentQuestionsResponse();
        response.setQuestions(questions);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/question/answer")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> answerQuestion(Principal principal, @RequestBody QuestionAnswerRequest request) {

        if(questionService.answerQuestion(principal.getName(), request)) {
            return ResponseEntity.ok(new MessageResponse("OK"));
        } else {
            return ResponseEntity.ok(new MessageResponse("ERROR"));
        }
    }
}
