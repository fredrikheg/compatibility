package net.crimsoncube.compatibility.repository;

import net.crimsoncube.compatibility.entity.UserAnswer;
import net.crimsoncube.compatibility.entity.UserAnswerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface AnswerRepository extends JpaRepository<UserAnswer, UserAnswerId> {

    @Query(value = "select qu.question_text, ua.answer, ua.question_id from user_answer ua join questions qu on qu.id = ua.question_id where ua.user_id = ?1", nativeQuery = true)
    List<Map<String, Object>> findByUserId(Long userId);
}
