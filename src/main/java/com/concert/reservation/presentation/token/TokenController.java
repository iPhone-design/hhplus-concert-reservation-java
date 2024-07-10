package com.concert.reservation.presentation.token;

import com.concert.reservation.application.token.TokenFacade;
import com.concert.reservation.domain.token.TokenCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenController {

    private final TokenFacade tokenFacade;

    /**
     * 고객 토큰 발급 API
     *
     * @author  양종문
     * @since   2024-07-09
     * @param   tokenRequest - 토큰 요청
     * @return  tokenResponse
     */
    @PostMapping("/issue")
    public TokenResponse issueToken(@Valid @RequestBody TokenRequest tokenRequest) {
        return TokenCommand.toResponse(tokenFacade.issueToken(tokenRequest.getCustomerId()));
    }
}
