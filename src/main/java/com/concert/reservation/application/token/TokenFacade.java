package com.concert.reservation.application.token;

import com.concert.reservation.domain.token.TokenDomain;
import com.concert.reservation.domain.token.TokenService;
import com.concert.reservation.domain.token.TokenStatus;
import jakarta.transaction.Transactional;
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

    /**
     * 고객 토큰 조회
     *
     * @author  양종문
     * @since   2024-07-09
     * @param   customerId - 고객 ID
     * @return  tokenDomain
     */
    @Transactional
    public TokenDomain findByCustomerId(Long customerId) {
        // 활성화 토큰 수 체크 (최대 활성화 토큰 100명까지 허용)
        tokenService.checkActiveStatusCount();

        // 첫 번째 대기열 고객 상세조회
        TokenDomain tokenDomain = tokenService.findFirstWaiting();
        
        // 본인 순번이면 토큰 활성화 처리
        if (tokenDomain.getCustomerId().equals(customerId)) {
            // 토큰 상태 값 변경 (대기 → 활성)
            tokenService.changeStatus(customerId, TokenStatus.ACTIVE);

            // 토큰 상세조회
            return tokenService.findByCustomerId(customerId).orElseThrow(() -> new IllegalArgumentException("토큰 상세 정보가 없습니다."));
        }
        else {
            // 대기열 등수를 포함한 고객 상세조회
            return tokenService.findByCustomerIdWithRank(customerId);
        }
    }
}
