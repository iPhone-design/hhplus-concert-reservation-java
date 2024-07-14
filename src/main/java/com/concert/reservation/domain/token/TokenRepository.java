package com.concert.reservation.domain.token;

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