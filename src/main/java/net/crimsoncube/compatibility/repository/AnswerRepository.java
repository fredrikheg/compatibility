package net.crimsoncube.compatibility.repository;

import net.crimsoncube.compatibility.entity.UserAnswer;
import net.crimsoncube.compatibility.entity.UserAnswerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<UserAnswer, UserAnswerId> {
}
