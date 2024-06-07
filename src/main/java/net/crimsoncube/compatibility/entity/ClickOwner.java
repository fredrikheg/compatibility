package net.crimsoncube.compatibility.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.HashSet;
import java.util.Set;

@Entity
public class ClickOwner {

    // We want a very loose coupling here
    @Id
    private Long userId;

    @ManyToOne
    private Set<Click> clicks;

    public ClickOwner() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<Click> getClicks() {
        if(clicks == null) {
            clicks = new HashSet<>();
        }
        return clicks;
    }

    public void setClicks(Set<Click> clicks) {
        this.clicks = clicks;
    }
}
