package net.crimsoncube.compatibility.api.v1.response.exposed;

public class AnswerDto {

    private String text;
    private Integer answer;
    private String answerText;

    public AnswerDto(String text, Integer answer) {
        this.text = text;
        this.answer = answer;
        this.answerText = getAnswerText();
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
}
