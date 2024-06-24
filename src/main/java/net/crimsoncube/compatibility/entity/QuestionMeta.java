package net.crimsoncube.compatibility.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "question-meta")
public class QuestionMeta {

    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
