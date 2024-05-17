package net.crimsoncube.compatibility.repository;

import net.crimsoncube.compatibility.entity.RefreshToken;
import net.crimsoncube.compatibility.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUserId(Long userId);

    @Modifying
    int deleteByUser(User user);
}
