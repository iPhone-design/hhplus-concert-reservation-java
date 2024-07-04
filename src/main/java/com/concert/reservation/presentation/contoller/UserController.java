package com.concert.reservation.presentation.contoller;

import com.concert.reservation.domain.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 잔액 충전 API
     *
     * @author  양종문
     * @since   2024-07-05
     * @param   userDto
     * @return  UserDto
     */
    @PostMapping("/charge")
    public UserDto userCharge(UserDto userDto) {
        return new UserDto();
    }

    /**
     * 잔액 조회 API
     *
     * @author  양종문
     * @since   2024-07-05
     * @param   userDto
     * @return  UserDto
     */
    @PostMapping("/amount")
    public UserDto userAmount(UserDto userDto) {
        return new UserDto();
    }
}
