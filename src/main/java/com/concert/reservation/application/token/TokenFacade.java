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
        // 고객 토큰 조회
        TokenDomain tokenDomain = tokenService.findByCustomerId(customerId);
        
        // 고객 토큰이 존재할 경우
        if (tokenDomain != null) {
            // 고객 토큰 삭제
            tokenService.deleteById(tokenDomain.getTokenId());
        }
        
        // 고객 토큰 저장
        return tokenService.save(TokenDomain.builder().customerId(customerId).status("WAITING").build());
    }
}
