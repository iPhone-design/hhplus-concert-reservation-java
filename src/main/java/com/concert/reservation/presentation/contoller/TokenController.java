package com.concert.reservation.presentation.contoller;

import com.concert.reservation.domain.dto.TokenDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {
    /**
     * 유저 토큰 발급 API
     *
     * @author  양종문
     * @since   2024-07-05
     * @param   tokenDto
     * @return  TokenDto
     */
    @PostMapping("/issue")
    public TokenDto tokenIssue(@RequestBody TokenDto tokenDto) {
        return new TokenDto();
    }
}
