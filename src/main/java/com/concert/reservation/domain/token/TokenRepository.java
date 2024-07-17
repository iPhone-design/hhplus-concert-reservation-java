package com.concert.reservation.domain.token;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public interface TokenRepository {
    List<TokenDomain> findAllWaitingStatus();
    Optional<TokenDomain> findByCustomerId(Long customerId);
    List<TokenDomain> findActive();
    Integer bulkStatusToWaiting(LocalDateTime currentDt, LocalDateTime expireDt);
    TokenDomain save(TokenDomain tokenDomain);
}