package com.concert.reservation.domain.token;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public interface TokenRepository {
    TokenDomain findFirstWaiting();
    Integer findRankByCustomerId(Long customerId);
    Optional<TokenDomain> findByCustomerId(Long customerId);
    List<TokenDomain> findAllActiveTokensOlderThanFourMinutes(LocalDateTime expireDt);
    List<TokenDomain> findActive();
    TokenDomain save(TokenDomain tokenDomain);
}