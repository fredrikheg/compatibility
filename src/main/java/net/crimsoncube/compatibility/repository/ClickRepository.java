package net.crimsoncube.compatibility.repository;

import net.crimsoncube.compatibility.entity.Click;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClickRepository extends JpaRepository<Click, Long> {

    @Query(value = "select * from click where owner_id = ?1 order by id asc", nativeQuery = true)
    List<Click> findClicksByOwner(Long ownerId);
}
