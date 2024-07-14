package com.concert.reservation.infrastructure.concert;

import com.concert.reservation.domain.concert.ConcertOptionRepository;
import com.concert.reservation.domain.concert.ConcertOptionCommand;
import com.concert.reservation.domain.concert.ConcertOptionDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConcertOptionRepositoryImpl implements ConcertOptionRepository {

    private final ConcertOptionJpaRepository concertOptionJpaRepository;

    /**
     * 예약 가능 콘서트 조회
     *
     * @author  양종문
     * @since   2024-07-10
     * @param   currentDt - 현재일시
     * @return  List<ConcertOptionDomain>
     */
    @Override
    public List<ConcertOptionDomain> findAllAvailableConcertForReservation(LocalDateTime currentDt) {
        return concertOptionJpaRepository.findAllAvailableConcertForReservation(currentDt).stream()
                                                                                          .map(ConcertOptionCommand::toDomain)
                                                                                          .collect(Collectors.toList());
    }
}