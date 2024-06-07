package net.crimsoncube.compatibility.repository;

import net.crimsoncube.compatibility.entity.Click;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClickRepository extends JpaRepository<Click, Long> {
}
