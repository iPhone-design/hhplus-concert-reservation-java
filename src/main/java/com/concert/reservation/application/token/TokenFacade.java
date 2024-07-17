package com.concert.reservation.application.token;

import com.concert.reservation.domain.token.TokenDomain;
import com.concert.reservation.domain.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenFacade {

    private final TokenService tokenService;

    /**
     * 고객 토큰 발급
     *
     * @author  양종문
     * @since   2024-07-09
     * @param   customerId - 고객 ID
     * @return  tokenDomain
     */
    public TokenDomain issueToken(Long customerId) {
        // 토큰 발급
        return tokenService.issueToken(customerId);
    }
}
