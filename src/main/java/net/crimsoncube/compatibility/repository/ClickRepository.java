package net.crimsoncube.compatibility.repository;

import net.crimsoncube.compatibility.entity.Click;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ClickRepository extends JpaRepository<Click, Long> {
    Optional<Set<Click>> findByOwnerId(Long ownerId);
}
