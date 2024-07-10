package com.concert.reservation.application.concert;

import com.concert.reservation.domain.Concert.ConcertOptionCommand;
import com.concert.reservation.presentation.concert.ConcertOptionResponse;
import jakarta.transaction.Transactional;
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
    @Transactional
    public List<ConcertOptionResponse> findAllAvailableConcertForReservation() {
        // 현재일시
        LocalDateTime currentDt = LocalDateTime.now();

        return ConcertOptionCommand.toResponse(concertOptionRepository.findAllAvailableConcertForReservation(currentDt));
    }
}
