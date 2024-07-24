package com.concert.reservation.domain.seat;

import com.concert.reservation.domain.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SeatOptionServiceTest {

    @Mock
    private SeatOptionRepository seatOptionRepository;

    @InjectMocks
    private SeatOptionService seatOptionService;

    @Test
    @DisplayName("좌석 상세조회 (좌석이 존재하는 경우)")
    void findSeat() {
        // given
        Long seatOptionId = 51L;
        Long concertOptionId = 2L;
        SeatOptionDomain seatOptionDomain = SeatOptionDomain.builder()
                                                            .seatOptionId(seatOptionId)
                                                            .seatId(1L)
                                                            .concertOptionId(concertOptionId)
                                                            .price(50000L)
                                                            .status(SeatOptionStatus.AVAILABLE)
                                                            .build();

        // when
        when(seatOptionRepository.findSeat(seatOptionId, concertOptionId)).thenReturn(Optional.of(seatOptionDomain));

        SeatOptionDomain result = seatOptionService.findSeat(seatOptionId, concertOptionId);

        // then
        assertThat(result).isNotNull();
        assertThat(seatOptionDomain).isEqualTo(result);
    }

    @Test
    @DisplayName("좌석 상세조회 (좌석이 없는 경우)")
    void findSeat_NotFound() {
        // given
        Long seatOptionId = 51L;
        Long concertOptionId = 2L;

        // when
        when(seatOptionRepository.findSeat(seatOptionId, concertOptionId)).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> seatOptionService.findSeat(seatOptionId, concertOptionId)).isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("예약 가능 콘서트 좌석 조회")
    void findAllAvailableSeatForReservation() {
        // given
        LocalDate startDate = LocalDate.now();
        Long concertOptionId = 1L;
        List<SeatOptionDomain> listSeatOptionDomain = Arrays.asList(SeatOptionDomain.builder().seatOptionId(50L).seatId(1L).concertOptionId(concertOptionId).price(50000L).status(SeatOptionStatus.AVAILABLE).build()
                                                                  , SeatOptionDomain.builder().seatOptionId(52L).seatId(2L).concertOptionId(concertOptionId).price(50000L).status(SeatOptionStatus.AVAILABLE).build());

        // when
        when(seatOptionRepository.findAllAvailableSeatForReservation(anyLong(), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(listSeatOptionDomain);

        List<SeatOptionDomain> results = seatOptionService.findAllAvailableSeatForReservation(startDate, concertOptionId);

        // then
        assertThat(results).isNotNull();
        assertThat(results.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("좌석 상태 값 변경")
    void changeStatus() {
        // given
        Long seatOptionId = 1L;
        Long concertOptionId = 1L;
        SeatOptionDomain seatOptionDomain = SeatOptionDomain.builder()
                                                            .seatOptionId(seatOptionId)
                                                            .seatId(1L)
                                                            .concertOptionId(concertOptionId)
                                                            .price(50000L)
                                                            .status(SeatOptionStatus.AVAILABLE)
                                                            .build();

        // when
        when(seatOptionRepository.findSeat(seatOptionId, concertOptionId)).thenReturn(Optional.of(seatOptionDomain));

        seatOptionService.changeStatus(seatOptionId, concertOptionId, SeatOptionStatus.UNAVAILABLE);

        // then
        assertThat(seatOptionDomain.getStatus()).isEqualTo(SeatOptionStatus.UNAVAILABLE);
        verify(seatOptionRepository, times(1)).save(seatOptionDomain);
    }

    @Test
    @DisplayName("좌석 가능 상태 체크 (가능인 경우)")
    void testCheckAvailableStatus_Available() {
        // given
        Long seatOptionId = 1L;
        Long concertOptionId = 1L;
        SeatOptionDomain seatOptionDomain = SeatOptionDomain.builder()
                                                            .seatOptionId(seatOptionId)
                                                            .seatId(1L)
                                                            .concertOptionId(concertOptionId)
                                                            .price(50000L)
                                                            .status(SeatOptionStatus.AVAILABLE)
                                                            .build();

        // when
        when(seatOptionRepository.findSeat(seatOptionId, concertOptionId)).thenReturn(Optional.of(seatOptionDomain));

        // then
        assertDoesNotThrow(() -> seatOptionService.checkAvailableStatus(seatOptionId, concertOptionId));
        verify(seatOptionRepository, times(1)).findSeat(seatOptionId, concertOptionId);
    }

    @Test
    @DisplayName("좌석 가능 상태 체크 (불가능인 경우)")
    void testCheckAvailableStatus_Unavailable() {
        // given
        Long seatOptionId = 1L;
        Long concertOptionId = 1L;
        SeatOptionDomain seatOptionDomain = SeatOptionDomain.builder()
                                                            .seatOptionId(seatOptionId)
                                                            .seatId(1L)
                                                            .concertOptionId(concertOptionId)
                                                            .price(50000L)
                                                            .status(SeatOptionStatus.UNAVAILABLE)
                                                            .build();

        // when
        when(seatOptionRepository.findSeat(seatOptionId, concertOptionId)).thenReturn(Optional.of(seatOptionDomain));

        // then
        assertThatThrownBy(() -> seatOptionService.checkAvailableStatus(seatOptionId, concertOptionId)).isInstanceOf(CustomException.class);
        verify(seatOptionRepository, times(1)).findSeat(seatOptionId, concertOptionId);
    }
}