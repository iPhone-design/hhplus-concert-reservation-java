package com.concert.reservation.presentation.contoller;

import com.concert.reservation.domain.dto.ConcertDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/concert")
public class ConcertController {
    /**
     * 예약 가능 날짜 조회 API
     *
     * @author  양종문
     * @since   2024-07-05
     * @return  ConcertDto
     */
    @GetMapping("/date")
    public ConcertDto consertDate() {
        return new ConcertDto();
    }

    /**
     * 예약 가능 좌석 조회 API
     *
     * @author  양종문
     * @since   2024-07-05
     * @param   date
     * @return  ConcertDto
     */
    @GetMapping("/seat")
    public ConcertDto consertSeat(@RequestParam(name = "date") String date) {
        return new ConcertDto();
    }
}
