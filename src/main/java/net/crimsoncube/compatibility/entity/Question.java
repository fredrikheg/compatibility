package net.crimsoncube.compatibility.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question {

    private Long id;
    private String questionText;
    private Set<Question> subQuestions = new HashSet<>();
    private QuestionMeta meta;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    @OneToOne
    public QuestionMeta getMeta() {
        return meta;
    }

    public void setMeta(QuestionMeta meta) {
        this.meta = meta;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Question> getSubQuestions() {
        return subQuestions;
    }

    public void setSubQuestions(Set<Question> subQuestions) {
        this.subQuestions = subQuestions;
    }
}
