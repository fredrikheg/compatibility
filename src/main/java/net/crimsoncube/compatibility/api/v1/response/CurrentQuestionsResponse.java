package net.crimsoncube.compatibility.api.v1.response;

import net.crimsoncube.compatibility.entity.Question;

import java.util.List;

public class CurrentQuestionsResponse {

    private List<Question> questions;

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
