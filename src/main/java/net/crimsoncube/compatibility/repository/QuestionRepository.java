package net.crimsoncube.compatibility.repository;

import net.crimsoncube.compatibility.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
