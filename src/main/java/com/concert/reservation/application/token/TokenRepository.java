package com.concert.reservation.application.token;

import com.concert.reservation.domain.token.TokenDomain;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface TokenRepository {
    TokenDomain findByCustomerId(Long customerId);
    TokenDomain findFirstWaiting();
    Integer countActive();
    Integer countWaiting();
    List<TokenDomain> findAllExpireTargetByCheckTime(LocalDateTime checkTime);
    TokenDomain save(TokenDomain tokenDomain);
    void modifyStatus(TokenDomain tokenDomain);
    void deleteById(Long tokenId);
}