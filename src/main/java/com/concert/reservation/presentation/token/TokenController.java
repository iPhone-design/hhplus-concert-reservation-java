package com.concert.reservation.presentation.token;

import com.concert.reservation.application.token.TokenFacade;
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
        return TokenResponse.toResponse(tokenFacade.issueToken(tokenRequest.getCustomerId()));
    }

    /**
     * 고객 토큰 조회 API
     *
     * @author  양종문
     * @since   2024-07-18
     * @param   customerId - 고객 ID
     * @return  tokenResponse
     */
    @GetMapping("/{customer-id}")
    public TokenResponse findByCustomerId(@PathVariable(name = "customer-id") Long customerId) {
        return TokenResponse.toResponse(tokenFacade.findByCustomerId(customerId));
    }
}
