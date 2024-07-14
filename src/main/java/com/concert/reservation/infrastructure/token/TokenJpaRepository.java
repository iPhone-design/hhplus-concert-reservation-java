package com.concert.reservation.infrastructure.token;

import com.concert.reservation.domain.token.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TokenJpaRepository extends JpaRepository<Token, Long> {
    Token findByCustomerId(Long customerId);
    @Query("SELECT t FROM Token t WHERE t.status = 'WAITING' AND t.entryDt IS NULL ORDER BY t.tokenId LIMIT 1")
    Token findFirstWaiting();
    @Query("SELECT COUNT(*) FROM Token t WHERE t.status = 'ACTIVE' AND t.entryDt IS NOT NULL")
    Integer countActive();
    @Query("SELECT COUNT(*) FROM Token t WHERE t.status = 'WAITING' AND t.entryDt IS NULL")
    Integer countWaiting();
    @Query("SELECT t FROM Token t WHERE t.status = 'ACTIVE' AND t.entryDt < :checkTime")
    List<Token> findAllExpireTargetByCheckTime(@Param("checkTime") LocalDateTime checkTime);
    @Modifying
    @Query(value = "UPDATE token" +
                   "   SET status = :status" +
                   "     , entry_dt = :entry_dt" +
                   " WHERE token_id = :token_id", nativeQuery = true)
    void updateStatus(@Param("token_id") Long tokenId, @Param("status") String status, @Param("entry_dt") Timestamp entryDt);
}