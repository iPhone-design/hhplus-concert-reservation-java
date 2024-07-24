package com.concert.reservation.domain.concert;

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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConcertOptionServiceTest {

    @Mock
    private ConcertOptionRepository concertOptionRepository;

    @InjectMocks
    private ConcertOptionService concertOptionService;

    @Test
    @DisplayName("예약 가능 콘서트 조회")
    void findAllAvailableConcertForReservation() {
        // given
        List<ConcertOptionDomain> concertOptionDomains = Arrays.asList(ConcertOptionDomain.builder().concertOptionId(1L).concertId(1L).concertName("잠실 콘서트").location("잠실").openDt(LocalDateTime.now().plusDays(10)).build()
                                                                     , ConcertOptionDomain.builder().concertOptionId(2L).concertId(2L).concertName("신촌 콘서트").location("신촌").openDt(LocalDateTime.now().plusDays(10)).build());

        // when
        when(concertOptionRepository.findAllAvailableConcertForReservation(any(LocalDateTime.class))).thenReturn(concertOptionDomains);

        List<ConcertOptionDomain> results = concertOptionService.findAllAvailableConcertForReservation();

        // then
        assertThat(results).isNotNull();
        assertThat(results.size()).isEqualTo(concertOptionDomains.size());
    }
}