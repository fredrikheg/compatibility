package net.crimsoncube.compatibility.security.service;

import jakarta.transaction.Transactional;
import net.crimsoncube.compatibility.entity.RefreshToken;
import net.crimsoncube.compatibility.repository.RefreshTokenRepository;
import net.crimsoncube.compatibility.repository.UserRepository;
import net.crimsoncube.compatibility.security.exception.RefreshTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.chrono.ChronoZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${compatibility.app.jwtRefreshExpirationMs}")
    private int refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {

        Optional<RefreshToken> existingRefreshToken = refreshTokenRepository.findByUserId(userId);

        RefreshToken refreshToken = existingRefreshToken.orElseGet(RefreshToken::new);

        Date expiryDate = Date.from(Instant.now().plusMillis(refreshTokenDurationMs));

        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(expiryDate);
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshTokenRepository.save(refreshToken);

        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken refreshToken) {

        Date now = new Date();

        if(refreshToken.getExpiryDate().after(now)) {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenException(refreshToken.getToken(), "RefreshToken expired. Please sign in again");
        }

        return refreshToken;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}
