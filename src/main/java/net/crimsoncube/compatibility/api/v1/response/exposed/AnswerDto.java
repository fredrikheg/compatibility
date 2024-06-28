package net.crimsoncube.compatibility.api.v1.response.exposed;

public class AnswerDto {

    private Long questionId;
    private String text;
    private Integer answer;
//    private final String answerText; // This is needed for JSON introspection.. breaks if removed

    public AnswerDto(String text, Integer answer, Long questionId) {
        this.text = text;
        this.answer = answer;
        this.questionId = questionId;
//        this.answerText = getAnswerText();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswerText() {

        return switch (answer) {
            case 1 -> "Never";
            case 2 -> "Nah";
            case 3 -> "Indiff";
            case 4 -> "Yeah";
            case 5 -> "Always";
            default -> "undefined";
        };
    }

    public Integer getAnswer(){
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
