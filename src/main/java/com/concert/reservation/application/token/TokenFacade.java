package com.concert.reservation.application.token;

import com.concert.reservation.domain.customer.CustomerService;
import com.concert.reservation.domain.exception.CustomException;
import com.concert.reservation.domain.token.TokenDomain;
import com.concert.reservation.domain.token.TokenService;
import com.concert.reservation.domain.token.TokenStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenFacade {

    private final TokenService tokenService;
    private final CustomerService customerService;

    /**
     * 고객 토큰 발급
     *
     * @author  양종문
     * @since   2024-07-09
     * @param   customerId - 고객 ID
     * @return  tokenDomain
     */
    public TokenDomain issueToken(Long customerId) {
        // 고객 상세조회
        customerService.findById(customerId);

        // 토큰 발급
        return tokenService.issueToken(customerId);
    }

    /**
     * 고객 토큰 유효성 체크
     *
     * @author  양종문
     * @since   2024-07-19
     * @param   customerId - 고객 ID
     */
    public void checkActiveStatus(Long customerId) {
        // 토큰 활성화 상태 체크
        tokenService.checkActiveStatus(customerId);
    }

    /**
     * 고객 토큰 조회 및 본인 순번이면 토큰 활성화 처리
     *
     * @author  양종문
     * @since   2024-07-09
     * @param   customerId - 고객 ID
     * @return  tokenDomain
     */
    @Transactional
    public TokenDomain findByCustomerIdAndThenChangeStatusToActive(Long customerId) {
        // 토큰 조회
        tokenService.findByCustomerId(customerId).orElseThrow(() -> new CustomException(HttpStatus.UNAUTHORIZED, "토큰 상세 정보가 없습니다."));

        // 활성화 토큰 수 체크 (최대 활성화 토큰 100명까지 허용)
        tokenService.checkActiveStatusCount();

        // 첫 번째 대기열 고객 상세조회
        TokenDomain tokenDomain = tokenService.findFirstWaiting();
        
        // 본인 순번이면 토큰 활성화 처리
        if (tokenDomain.getCustomerId().equals(customerId)) {
            // 토큰 상태 값 변경 (대기 → 활성)
            tokenService.changeStatus(customerId, TokenStatus.ACTIVE);

            // 토큰 상세조회
            return tokenService.findByCustomerId(customerId).orElseThrow(() -> new CustomException(HttpStatus.UNAUTHORIZED, "토큰 상세 정보가 없습니다."));
        }
        else {
            // 대기열 등수를 포함한 고객 상세조회
            return tokenService.findByCustomerIdWithRank(customerId);
        }
    }
}
