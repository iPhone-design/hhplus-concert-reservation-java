package com.concert.reservation.presentation.contoller;

import com.concert.reservation.domain.dto.ReservationDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    /**
     * 좌석 예약 요청 API
     *
     * @author  양종문
     * @since   2024-07-05
     * @param   reservationDto
     * @return  ReservationDto
     */
    @PostMapping("/seat")
    public ReservationDto reservationSeat(@RequestBody ReservationDto reservationDto) {
        return new ReservationDto();
    }
}