package com.concert.reservation.infrastructure.token;

import com.concert.reservation.domain.token.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenJpaRepository extends JpaRepository<Token, Long> {
    // 대기 상태의 토큰 목록 조회
    @Query(value = """
             SELECT token_id
                  , customer_id
                  , status
                  , waiting_start_dt
                  , entry_dt
               FROM token
              WHERE status = 'WAITING'
             ORDER BY waiting_start_dt
             """, nativeQuery = true)
    List<Token> findAllWaitingStatus();

    // 토큰 상세조회
    Optional<Token> findByCustomerId(Long customerId);

    // 활성화 토큰 조회
    @Query("SELECT t FROM Token t WHERE t.status = 'ACTIVE' AND t.entryDt IS NOT NULL")
    List<Token> findActive();

    // 토큰 상태 값 일괄 변경 (활성화 → 대기)
    @Modifying
    @Query(value = "UPDATE token" +
                   "   SET status = 'WAITING'" +
                   "     , waiting_start_dt = :currentDt" +
                   "     , entry_dt = null" +
                   " WHERE status = 'ACTIVE'" +
                   "   AND entry_dt > :expireDt", nativeQuery = true)
    Integer bulkStatusToWaiting(@Param("currentDt") LocalDateTime currentDt, @Param("expireDt") LocalDateTime expireDt);
}