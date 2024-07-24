package com.concert.reservation.domain.reservation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    @DisplayName("예약 상세조회")
    void findByReservationId() {
        // given
        Long reservationId = 1L;
        ReservationDomain reservationDomain = ReservationDomain.builder()
                                                               .reservationId(reservationId)
                                                               .concertOptionId(1L)
                                                               .seatOptionId(51L)
                                                               .customerId(1L)
                                                               .status(ReservationStatus.INCOMPLETE)
                                                               .reservationDt(LocalDateTime.now())
                                                               .build();

        // when
        when(reservationRepository.findByReservationId(reservationId)).thenReturn(reservationDomain);

        ReservationDomain result = reservationService.findByReservationId(reservationId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getReservationId()).isEqualTo(reservationId);
    }

    @Test
    @DisplayName("예약 상세조회")
    void findByConcertOptionIdAndSeatOptionIdAndCustomerId() {
        // given
        Long reservationId = 1L;
        Long concertOptionId = 1L;
        Long seatOptionId = 51L;
        Long customerId = 1L;
        ReservationDomain reservationDomain = ReservationDomain.builder()
                                                               .reservationId(reservationId)
                                                               .concertOptionId(concertOptionId)
                                                               .seatOptionId(seatOptionId)
                                                               .customerId(customerId)
                                                               .status(ReservationStatus.INCOMPLETE)
                                                               .reservationDt(LocalDateTime.now())
                                                               .build();

        // when
        when(reservationRepository.findByConcertOptionIdAndSeatOptionIdAndCustomerId(concertOptionId, seatOptionId, customerId)).thenReturn(reservationDomain);

        ReservationDomain result = reservationService.findByConcertOptionIdAndSeatOptionIdAndCustomerId(concertOptionId, seatOptionId, customerId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getCustomerId()).isEqualTo(customerId);
    }

    @Test
    @DisplayName("특정 고객의 미완료 상태의 예약 조회")
    void findAllIncompleteReservationsByCustomerIdAndReservationDt() {
        // given
        Long customerId = 1L;
        List<ReservationDomain> listReservation = Arrays.asList(ReservationDomain.builder().reservationId(1L).concertOptionId(1L).seatOptionId(51L).customerId(customerId).status(ReservationStatus.INCOMPLETE).reservationDt(LocalDateTime.now().plusMinutes(10)).build()
                                                              , ReservationDomain.builder().reservationId(2L).concertOptionId(2L).seatOptionId(10L).customerId(customerId).status(ReservationStatus.INCOMPLETE).reservationDt(LocalDateTime.now().plusMinutes(1)).build());

        // when
        when(reservationRepository.findAllIncompleteReservationsByCustomerIdAndReservationDt(anyLong(), any(LocalDateTime.class))).thenReturn(listReservation);

        List<ReservationDomain> results = reservationService.findAllIncompleteReservationsByCustomerIdAndReservationDt(customerId, LocalDateTime.now());

        // then
        assertThat(results.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("예약 상태 값 변경")
    void changeStatus() {
        // given
        Long reservationId = 1L;
        ReservationDomain reservationDomain = ReservationDomain.builder()
                                                               .reservationId(reservationId)
                                                               .concertOptionId(1L)
                                                               .seatOptionId(51L)
                                                               .customerId(1L)
                                                               .status(ReservationStatus.INCOMPLETE)
                                                               .reservationDt(LocalDateTime.now())
                                                               .build();

        // when
        when(reservationService.findByReservationId(reservationId)).thenReturn(reservationDomain);

        reservationService.changeStatus(reservationId, ReservationStatus.COMPLETE);

        // then
        assertThat(reservationDomain.getStatus()).isEqualTo(ReservationStatus.COMPLETE);
        verify(reservationRepository, times(1)).save(reservationDomain);
    }

    @Test
    @DisplayName("예약 저장")
    void save() {
        // given
        ReservationDomain reservationDomain = ReservationDomain.builder()
                                                               .reservationId(1L)
                                                               .concertOptionId(1L)
                                                               .seatOptionId(51L)
                                                               .customerId(1L)
                                                               .status(ReservationStatus.INCOMPLETE)
                                                               .reservationDt(LocalDateTime.now())
                                                               .build();

        // when
        when(reservationRepository.save(reservationDomain)).thenReturn(reservationDomain);

        ReservationDomain result = reservationService.save(reservationDomain);

        // then
        assertThat(result).isNotNull();
    }
}