package com.concert.reservation.interfaces.scheduler;

import com.concert.reservation.domain.reservation.ReservationDomain;
import com.concert.reservation.domain.reservation.ReservationService;
import com.concert.reservation.domain.seat.SeatOptionService;
import com.concert.reservation.domain.seat.SeatOptionStatus;
import com.concert.reservation.domain.token.TokenService;
import com.concert.reservation.domain.token.entity.TokenRedis;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class TokenScheduler {

    private final TokenService tokenService;
    private final ReservationService reservationService;
    private final SeatOptionService seatOptionService;

    /**
     * 토큰 만료와 좌석 활성화 시키는 스케줄러
     *
     * @author  양종문
     * @since   2024-07-18
     * @history 2024-08-02 양종문 현재 사용하지 않아 주석 처리함
     */
    /*
    @Transactional
    public void tokensChangeStatusToExpireAndSeatOptionsChangeStatusToAvailable() {
        // 토큰 조회 (활성화가 된 지 4분이 지난 대상)
        List<TokenDomain> listTokenDomain = tokenService.findAllActiveTokensOlderThanFourMinutes();
        for (TokenDomain tokenDomain : listTokenDomain) {
            Long customerId = tokenDomain.getCustomerId();
            LocalDateTime entryDt = tokenDomain.getEntryDt();

            // 토큰 상태 값 변경 (활성 → 만료)
            tokenService.changeStatus(customerId, TokenStatus.EXPIRE);

            // 특정 고객의 미완료 상태의 예약 조회 (토큰 활성화 시점 이후에 예약된 대상으로 한정)
            List<ReservationDomain> listReservationDomain = reservationService.findAllIncompleteReservationsByCustomerIdAndReservationDt(customerId, entryDt);
            for (ReservationDomain reservationDomain : listReservationDomain) {
                Long seatOptionId = reservationDomain.getSeatOptionId();
                Long concertOptionId = reservationDomain.getConcertOptionId();

                // 좌석 상태 값 변경 (불가능 → 가능)
                seatOptionService.changeStatus(seatOptionId, concertOptionId, SeatOptionStatus.AVAILABLE);
            }
        }
    }
    */

    /**
     * 대기열 토큰을 활성화 시키는 스케줄러
     *
     * @author  양종문
     * @since   2024-08-02
     */
    @Scheduled(cron = "*/10 * * * * *") // 10초마다 실행
    @Transactional
    public void addActiveQueueFromWaitingQueue() {
        LocalDateTime startDt = LocalDateTime.now();
        log.info("Start addActiveQueueFromWaitingQueue");

        // 최대 활성화 토큰 수
        final int COUNT_MAX_ACTIVE = 5;

        // 토큰 활성화 상태 수 조회
        Long countActiveToken = tokenService.countActiveTokensFromRedis();
        log.info("countActiveToken : {}", countActiveToken);
        if (countActiveToken > COUNT_MAX_ACTIVE) return;

        // 대기 중 대기열 조회 (대기 중 대기열의 가장 빠른 토큰부터 활성화 대기열의 남은 자리만큼 조회 (최대 활성화 토큰 수 - 현재 활성화 토큰 수))
        List<TokenRedis> tokens = tokenService.getWaitingTokensFromRedis(0, COUNT_MAX_ACTIVE - (int) (long) countActiveToken);
        for (TokenRedis token : tokens) {
            String uuid = token.getUuid();
            Long customerId = token.getCustomerId();

            // 대기중 대기열 삭제
            Long countDelete = tokenService.deleteWaitingQueueByUUID(uuid, customerId);
            if (countDelete > 0) {
                // 활성화 대기열 추가
                tokenService.addActiveQueue(uuid, customerId);

                log.info("[대기중 → 활성화] UUID : {}, customerId : {}", uuid, customerId);

                // 토큰 활성화 상태 수 조회
                log.info("countActiveToken : {}", tokenService.countActiveTokensFromRedis());
            }
        }

        LocalDateTime endDt = LocalDateTime.now();

        log.info("소요 시간 (밀리초) : {}", ChronoUnit.MILLIS.between(startDt, endDt));
        log.info("End addActiveQueueFromWaitingQueue");
    }

    /**
     * 토큰 만료와 좌석 활성화 시키는 스케줄러
     *
     * @author  양종문
     * @since   2024-08-02
     */
    @Scheduled(cron = "0 */1 * * * *") // 1분마다 실행
    @Transactional
    public void expireTokensFromActiveQueue() {
        LocalDateTime startDt = LocalDateTime.now();
        log.info("Start expireTokensFromActiveQueue");

        // 활성화 토큰 조회
        List<TokenRedis> tokens = tokenService.getActiveTokensFromRedis();
        for (TokenRedis token : tokens) {
            String uuid = token.getUuid();
            Long customerId = token.getCustomerId();
            Long expireDt = token.getExpireDt();

            // 만료 시간이 지난 경우 대기열에서 삭제 처리
            if (expireDt < System.currentTimeMillis()) {
                Long countDelete = tokenService.deleteActiveQueueByUUID(uuid, customerId, expireDt);

                if (countDelete > 0) {
                    log.info("[활성화 → 제거] UUID : {}, customerId : {}, expireDt : {}", uuid, customerId, expireDt);
                    
                    // 특정 고객의 미완료 상태의 예약 조회 (토큰 활성화 시점 이후에 예약된 대상으로 한정)
                    Instant instant = Instant.ofEpochMilli(expireDt);
                    List<ReservationDomain> listReservationDomain = reservationService.findAllIncompleteReservationsByCustomerIdAndReservationDt(customerId, LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));
                    for (ReservationDomain reservationDomain : listReservationDomain) {
                        Long seatOptionId = reservationDomain.getSeatOptionId();
                        Long concertOptionId = reservationDomain.getConcertOptionId();

                        // 좌석 상태 값 변경 (불가능 → 가능)
                        seatOptionService.changeStatus(seatOptionId, concertOptionId, SeatOptionStatus.AVAILABLE);

                        log.info("[좌석 상태 값 변경 불가능 → 가능] seatOptionId : {}, concertOptionId : {}", seatOptionId, concertOptionId);
                    }
                }
            }
        }

        LocalDateTime endDt = LocalDateTime.now();

        log.info("소요 시간 (밀리초) : {}", ChronoUnit.MILLIS.between(startDt, endDt));
        log.info("End expireTokensFromActiveQueue");
    }
}
