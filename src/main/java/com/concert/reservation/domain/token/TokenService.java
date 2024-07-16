package com.concert.reservation.domain.token;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    /**
     * 토큰 상세조회
     *
     * @author  양종문
     * @since   2024-07-09
     * @param   customerId - ID
     * @return  tokenDomain
     */
    public Optional<TokenDomain> findByCustomerId(Long customerId) {
        return tokenRepository.findByCustomerId(customerId);
    }

    /**
     * 활성화 토큰 수 체크
     * ※ 최대 활성화 토큰 100명까지 허용
     *
     * @author  양종문
     * @since   2024-07-09
     */
    public void checkActiveStatusCount() {
        // 활성화 토큰 조회
        List<TokenDomain> tokenDomain = tokenRepository.findActive();

        if (tokenDomain.size() > 100) {
            throw new IllegalArgumentException("대기열 인원 수 초과");
        }
    }

    /**
     * 토큰 발급
     * ※ 기존 토큰이 존재할 경우 토큰 상태 값을 변경하고 존재하지 않으면 신규로 생성한다.
     *
     * @author  양종문
     * @since   2024-07-09
     * @param   customerId - 토큰 ID
     * @return  tokenDomain
     */
    @Transactional
    public TokenDomain issueToken(Long customerId) {
        // 토큰 조회
        Optional<TokenDomain> tokenDomain = this.findByCustomerId(customerId);

        // 기존 토큰이 존재할 경우
        if (tokenDomain.isPresent()) {
            // 토큰 상태 값 변경 (활성화 → 대기)
            tokenDomain.get().changeStatusToWaiting();

            // 토큰 상태 값 변경
            return tokenRepository.save(tokenDomain.get());
        }
        else {
            // 토큰 신규 생성
            return tokenRepository.save(TokenDomain.createToken(customerId));
        }
    }

    /**
     * 토큰 상태 값 변경
     *
     * @author  양종문
     * @since   2024-07-15
     */
    @Transactional
    public void changeStatus(Long customerId, TokenStatus status) {
        // 토큰 조회
        Optional<TokenDomain> tokenDomain = Optional.of(this.findByCustomerId(customerId).orElseThrow(() -> new IllegalArgumentException("토큰 상세 정보가 없습니다.")));

        // 대기
        if (TokenStatus.WAITING.equals(status)) {
            tokenDomain.get().changeStatusToWaiting();
        }
        // 활성화
        else if (TokenStatus.ACTIVE.equals(status)) {
            tokenDomain.get().changeStatusToActive();
        }
        // 만료
        else if (TokenStatus.EXPIRE.equals(status)) {
            tokenDomain.get().changeStatusToExpire();
        }

        // 저장
        tokenRepository.save(tokenDomain.get());
    }

    /**
     * 토큰 상태 값 일괄 변경 (활성화 → 대기)
     *
     * @author  양종문
     * @since   2024-07-15
     */
    @Transactional
    public Integer bulkStatusToWaiting() {
        return tokenRepository.bulkStatusToWaiting(LocalDateTime.now(), LocalDateTime.now().plusMinutes(4));
    }

    /**
     * 토큰 활성화 상태 체크
     *
     * @author  양종문
     * @since   2024-07-16
     * @param   customerId - 고객 ID
     */
    public void checkActiveStatus(Long customerId) {
        // 토큰 조회
        Optional<TokenDomain> tokenDomain = Optional.of(this.findByCustomerId(customerId).orElseThrow(() -> new IllegalArgumentException("토큰 상세 정보가 없습니다.")));
        
        // 토큰 활성화 상태 체크
        if (TokenStatus.ACTIVE.equals(tokenDomain.get().getStatus())) {
            throw new RuntimeException("토큰이 활성화 상태가 아닙니다.");
        }
    }
}
