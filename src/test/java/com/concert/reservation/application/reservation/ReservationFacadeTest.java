package com.concert.reservation.application.reservation;

import com.concert.reservation.domain.reservation.ReservationDomain;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class ReservationFacadeTest {

    @Autowired
    private ReservationFacade reservationFacade;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DisplayName("콘서트 좌석 예약 요청")
    void reservationConcert() {
        // given
        Long customerId = 1L;
        Long concertOptionId = 2L;
        Long seatOptionId = 51L;

        // when
        ReservationDomain reservationDomain = reservationFacade.reservationConcert(customerId, concertOptionId, seatOptionId);

        // then
        assertThat(reservationDomain).isNotNull();
    }

    @Test
    @DisplayName("콘서트 좌석 예약 요청 (낙관적락 적용)")
    void reservationConcertWithOptimisticLock() throws InterruptedException {
        // given
        Long customerId = 1L;
        Long concertOptionId = 2L;
        Long seatOptionId = 51L;

        int threadCount = 3;
        CountDownLatch latch = new CountDownLatch(threadCount);
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        log.info("Start {}", LocalDateTime.now());

        for (int idx = 0; idx < threadCount; idx++) {
            executorService.submit(() -> {
                try {
                    // 캐시 삭제
                    entityManager.clear();

                    reservationFacade.reservationConcert(customerId, concertOptionId, seatOptionId);
                }
                finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        log.info("End {}", LocalDateTime.now());
    }
}