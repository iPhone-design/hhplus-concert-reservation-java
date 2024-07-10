package com.concert.reservation.infrastructure.token;

import com.concert.reservation.infrastructure.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TokenJpaRepository extends JpaRepository<Token, Long> {
    Token findByCustomerId(Long customerId);
    @Query("SELECT t FROM Token t WHERE t.status = 'WAITING' AND t.entryDt IS NULL ORDER BY t.tokenId LIMIT 1")
    Token findFirstWaiting();
    @Query("SELECT COUNT(*) FROM Token t WHERE t.status = 'WAITING' AND t.entryDt IS NULL")
    Integer countWaiting();
    @Query("SELECT t FROM Token t WHERE t.status = 'ACTIVE' AND t.entryDt < :checkTime")
    List<Token> findAllExpireTargetByCheckTime(@Param("checkTime") LocalDateTime checkTime);
}