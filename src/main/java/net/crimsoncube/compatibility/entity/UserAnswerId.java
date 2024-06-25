package net.crimsoncube.compatibility.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserAnswerId implements Serializable {

    private Long userId;
    private Long questionId;

    @JoinColumn(referencedColumnName = "id")
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @JoinColumn(referencedColumnName = "id")
    @Column(name = "question_id")
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAnswerId answerId = (UserAnswerId) o;
        return Objects.equals(userId, answerId.userId) && Objects.equals(questionId, answerId.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, questionId);
    }
}
