package com.concert.reservation.infrastructure.token;

import com.concert.reservation.domain.token.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenJpaRepository extends JpaRepository<Token, Long> {
    // 첫 번째 대기열 고객 상세조회
    @Query(value = """
                     SELECT a.token_id
                          , a.customer_id
                          , a.status
                          , a.waiting_start_dt
                          , a.entry_dt
                       FROM (
                                 SELECT token_id
                                      , customer_id
                                      , status
                                      , waiting_start_dt
                                      , entry_dt
                                      , ROW_NUMBER() OVER (ORDER BY waiting_start_dt) AS rank
                                   FROM token
                                  WHERE status = 'WAITING'
                            ) a
                     WHERE a.rank = 1
                    """, nativeQuery = true)
    Token findFirstWaiting();
    
    // 대기열 등수 조회
    @Query(value = """
                    SELECT COUNT(*) + 1 AS rank
                      FROM token
                     WHERE status = 'WAITING'
                       AND waiting_start_dt < (
                                                   SELECT waiting_start_dt
                                                     FROM token
                                                    WHERE status = 'WAITING'
                                                      AND customer_id = :customer_id
                                              )
                    """, nativeQuery = true)
    Integer findRankByCustomerId(@Param("customer_id") Long customerId);

    // 토큰 상세조회
    Optional<Token> findByCustomerId(Long customerId);

    // 토큰 조회 (활성화가 된 지 4분이 지난 대상)
    @Query(value = """
                    SELECT token_id
                         , customer_id
                         , status
                         , waiting_start_dt
                         , entry_dt
                      FROM token
                     WHERE status = 'ACTIVE'
                       AND entry_dt < :expire_dt
                    """, nativeQuery = true)
    List<Token> findAllActiveTokensOlderThanFourMinutes(@Param("expire_dt") LocalDateTime expireDt);

    // 활성화 토큰 조회
    @Query("SELECT t FROM Token t WHERE t.status = 'ACTIVE' AND t.entryDt IS NOT NULL")
    List<Token> findActive();
}