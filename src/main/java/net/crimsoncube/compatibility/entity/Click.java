package net.crimsoncube.compatibility.entity;

import jakarta.persistence.*;

@Entity
public class Click {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer clicks;

    private Long ownerId;

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

    // No reference back here.
    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long owner) {
        this.ownerId = owner;
    }
}
