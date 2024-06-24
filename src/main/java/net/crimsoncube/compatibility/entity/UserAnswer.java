package net.crimsoncube.compatibility.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class UserAnswer {

    private UserAnswerId id;
    private Integer answer;

    @EmbeddedId
    public UserAnswerId getId() {
        return id;
    }

    public void setId(UserAnswerId id) {
        this.id = id;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }
}
