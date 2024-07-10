package com.concert.reservation.application.seat;

import com.concert.reservation.domain.seat.SeatOptionCommand;
import com.concert.reservation.presentation.concert.SeatOptionResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatOptionService {

    private final SeatOptionRepository seatOptionRepository;

    /**
     * 예약 가능 콘서트 좌석 조회
     *
     * @author  양종문
     * @since   2024-07-10
     * @param   startDate - 시작일
     * @param   concertOptionId - 콘서트 옵션 ID
     * @return  List<SeatOptionResponse>
     */
    @Transactional
    public List<SeatOptionResponse> findAllAvailableSeatForReservation(LocalDate startDate, Long concertOptionId) {
        // 시작일시
        LocalDateTime startDt = startDate.atStartOfDay();
        // 끝일시 (시작일에 1일을 더 해준다. 하루 범위로 검색하기 위함)
        LocalDateTime endDt = startDate.plusDays(1).atStartOfDay();

        return SeatOptionCommand.toResponse(seatOptionRepository.findAllAvailableSeatForReservation(concertOptionId, startDt, endDt));
    }
}
