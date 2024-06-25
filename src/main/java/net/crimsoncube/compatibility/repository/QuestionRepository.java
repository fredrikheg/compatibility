package net.crimsoncube.compatibility.repository;

import net.crimsoncube.compatibility.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "select * from questions where questions.id not in (select question_id from user_answer where user_id = ?1)",
            nativeQuery = true)
    List<Question> findUnansweredQuestionsByUser(Long userId);
}
