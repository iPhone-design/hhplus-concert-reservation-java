package com.concert.reservation.domain.concert;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcertOptionService {

    private final ConcertOptionRepository concertOptionRepository;

    /**
     * 예약 가능 콘서트 조회
     *
     * @author  양종문
     * @since   2024-07-10
     * @return  List<ConcertOptionResponse>
     */
    public List<ConcertOptionDomain> findAllAvailableConcertForReservation() {
        // 현재일시
        LocalDateTime currentDt = LocalDateTime.now();

        // 예약 가능 콘서트 조회
        return concertOptionRepository.findAllAvailableConcertForReservation(currentDt);
    }
}
