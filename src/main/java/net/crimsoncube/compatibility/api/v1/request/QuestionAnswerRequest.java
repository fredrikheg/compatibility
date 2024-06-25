package net.crimsoncube.compatibility.api.v1.request;

public class QuestionAnswerRequest {

    private Long questionId;
    private Integer answer;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }
}
