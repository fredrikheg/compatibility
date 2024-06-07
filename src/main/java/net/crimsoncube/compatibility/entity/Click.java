package net.crimsoncube.compatibility.entity;

import jakarta.persistence.*;
import org.springframework.context.annotation.Lazy;

@Entity
public class Click {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer clicks;

    @OneToMany
    @Lazy
    private ClickOwner owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }
}
