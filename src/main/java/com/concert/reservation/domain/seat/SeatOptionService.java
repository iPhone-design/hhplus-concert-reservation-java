package com.concert.reservation.domain.seat;

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
     * 좌석 조회
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   seatOptionId - 좌석 옵션 ID
     * @param   concertOptionId - 콘서트 옵션 ID
     * @param   date - 날짜
     * @return  SeatOptionDomain
     */
    public SeatOptionDomain findSeat(Long seatOptionId, Long concertOptionId, LocalDate date) {
        // 시작일시
        LocalDateTime startDt = date.atStartOfDay();
        // 끝일시 (시작일에 1일을 더 해준다. 하루 범위로 검색하기 위함)
        LocalDateTime endDt = date.plusDays(1).atStartOfDay();

        return seatOptionRepository.findSeat(seatOptionId, concertOptionId, startDt, endDt);
    }

    /**
     * 예약 가능 콘서트 좌석 조회
     *
     * @author  양종문
     * @since   2024-07-10
     * @param   startDate - 시작일
     * @param   concertOptionId - 콘서트 옵션 ID
     * @return  List<SeatOptionDomain>
     */
    @Transactional
    public List<SeatOptionDomain> findAllAvailableSeatForReservation(LocalDate startDate, Long concertOptionId) {
        // 시작일시
        LocalDateTime startDt = startDate.atStartOfDay();
        // 끝일시 (시작일에 1일을 더 해준다. 하루 범위로 검색하기 위함)
        LocalDateTime endDt = startDate.plusDays(1).atStartOfDay();

        return seatOptionRepository.findAllAvailableSeatForReservation(concertOptionId, startDt, endDt);
    }

    /**
     * 좌석 상태 값 수정
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   seatOptionDomain - 좌석 옵션 도메인
     * @param   status - 상태
     */
    @Transactional
    public void modifyStatus(SeatOptionDomain seatOptionDomain, String status) {
        seatOptionDomain.setStatus(status);
        seatOptionRepository.modifyStatus(seatOptionDomain);
    }
}
