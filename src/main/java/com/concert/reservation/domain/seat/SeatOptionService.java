package com.concert.reservation.domain.seat;

import com.concert.reservation.domain.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
     * @return  SeatOptionDomain
     */
    public SeatOptionDomain findSeat(Long seatOptionId, Long concertOptionId) {
        // 좌석 조회
        return seatOptionRepository.findSeat(seatOptionId, concertOptionId).orElseThrow(() -> new CustomException(HttpStatus.ACCEPTED, "좌석 상세조회 정보가 없습니다."));
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
    public List<SeatOptionDomain> findAllAvailableSeatForReservation(LocalDate startDate, Long concertOptionId) {
        // 시작일시
        LocalDateTime startDt = startDate.atStartOfDay();
        // 끝일시 (시작일에 1일을 더 해준다. 하루 범위로 검색하기 위함)
        LocalDateTime endDt = startDate.plusDays(1).atStartOfDay();

        // 예약 가능 콘서트 좌석 조회
        return seatOptionRepository.findAllAvailableSeatForReservation(concertOptionId, startDt, endDt);
    }

    /**
     * 좌석 상태 값 변경
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   seatOptionId - 좌석 옵션 ID
     * @param   concertOptionId - 콘서트 옵션 ID
     */
    public void changeStatus(Long seatOptionId, Long concertOptionId, SeatOptionStatus status) {
        // 좌석 조회
        SeatOptionDomain seatOptionDomain = this.findSeat(seatOptionId, concertOptionId);
        
        // 가능
        if (SeatOptionStatus.AVAILABLE.equals(status)) {
            seatOptionDomain.changeStatusToAvailable();
        }
        // 불가능
        else if (SeatOptionStatus.UNAVAILABLE.equals(status)) {
            seatOptionDomain.changeStatusToUnavailable();
        }

        // 저장
        seatOptionRepository.save(seatOptionDomain);
    }

    /**
     * 좌석 가능 상태 체크
     *
     * @author  양종문
     * @since   2024-07-16
     * @param   seatOptionId - 좌석 옵션 ID
     * @param   concertOptionId - 콘서트 옵션 ID
     */
    public void checkAvailableStatus(Long seatOptionId, Long concertOptionId) {
        // 좌석 조회
        SeatOptionDomain seatOptionDomain = this.findSeat(seatOptionId, concertOptionId);

        if (!SeatOptionStatus.AVAILABLE.equals(seatOptionDomain.getStatus())) {
            throw new CustomException(HttpStatus.ACCEPTED, "불가능 상태의 좌석입니다.");
        }
    }
}
