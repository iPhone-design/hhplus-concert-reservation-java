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
     * 좌석 상세조회
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

        // 좌석 조회
        return seatOptionRepository.findSeat(seatOptionId, concertOptionId, startDt, endDt).orElseThrow(() -> new IllegalArgumentException("좌석 상세조회 정보가 없습니다."));
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

        // 예약 가능 콘서트 좌석 조회
        return seatOptionRepository.findAllAvailableSeatForReservation(concertOptionId, startDt, endDt);
    }

    /**
     * 좌석 가능 처리
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   seatOptionId - 좌석 옵션 ID
     * @param   concertOptionId - 콘서트 옵션 ID
     * @param   date - 날짜
     */
    @Transactional
    public void changeStatusToAvailable(Long seatOptionId, Long concertOptionId, LocalDate date) {
        // 좌석 조회
        SeatOptionDomain seatOptionDomain = this.findSeat(seatOptionId, concertOptionId, date);

        // 자석 상태값 변경 (불가능 → 가능)
        seatOptionDomain.changeStatusToAvailable();
        
        // 저장
        seatOptionRepository.save(seatOptionDomain);
    }

    /**
     * 좌석 불가능 처리
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   seatOptionId - 좌석 옵션 ID
     * @param   concertOptionId - 콘서트 옵션 ID
     * @param   date - 날짜
     */
    @Transactional
    public void changeStatusToUnavailable(Long seatOptionId, Long concertOptionId, LocalDate date) {
        // 좌석 조회
        SeatOptionDomain seatOptionDomain = this.findSeat(seatOptionId, concertOptionId, date);

        // 자석 상태값 변경 (가능 → 불가능)
        seatOptionDomain.changeStatusToUnavailable();

        // 저장
        seatOptionRepository.save(seatOptionDomain);
    }
}
