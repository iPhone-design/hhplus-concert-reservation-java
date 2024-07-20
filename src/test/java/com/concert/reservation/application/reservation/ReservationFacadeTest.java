package com.concert.reservation.application.reservation;

import com.concert.reservation.domain.reservation.ReservationDomain;
import com.concert.reservation.domain.reservation.ReservationService;
import com.concert.reservation.domain.reservation.ReservationStatus;
import com.concert.reservation.domain.seat.SeatOptionService;
import com.concert.reservation.domain.seat.SeatOptionStatus;
import com.concert.reservation.domain.token.TokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ReservationFacadeTest {

    @Mock
    private TokenService tokenService;
    @Mock
    private SeatOptionService seatOptionService;
    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationFacade reservationFacade;

    @Test
    @DisplayName("콘서트 좌석 예약 요청")
    void reservationConcert() {
        // given
        Long customerId = 1L;
        Long concertOptionId = 2L;
        Long seatOptionId = 3L;

        ReservationDomain reservationDomain = ReservationDomain.builder()
                                                               .customerId(customerId)
                                                               .concertOptionId(concertOptionId)
                                                               .seatOptionId(seatOptionId)
                                                               .status(ReservationStatus.INCOMPLETE)
                                                               .build();

        // when
        doNothing().when(seatOptionService).checkAvailableStatus(seatOptionId, concertOptionId);
        doNothing().when(seatOptionService).changeStatus(seatOptionId, concertOptionId, SeatOptionStatus.UNAVAILABLE);
        when(reservationService.save(any(ReservationDomain.class))).thenReturn(reservationDomain);

        ReservationDomain result = reservationFacade.reservationConcert(customerId, concertOptionId, seatOptionId);

        // then
        assertEquals(reservationDomain.getStatus(), result.getStatus());
        verify(seatOptionService, times(1)).checkAvailableStatus(seatOptionId, concertOptionId);
        verify(seatOptionService, times(1)).changeStatus(seatOptionId, concertOptionId, SeatOptionStatus.UNAVAILABLE);
        verify(reservationService, times(1)).save(any(ReservationDomain.class));
    }
}