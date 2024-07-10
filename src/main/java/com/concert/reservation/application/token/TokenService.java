package com.concert.reservation.application.token;

import com.concert.reservation.domain.token.TokenDomain;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

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
        return tokenRepository.findByCustomerId(customerId);
    }

    /**
     * 첫 번째 대기열 토큰 조회
     *
     * @author  양종문
     * @since   2024-07-10
     * @return  tokenDomain
     */
    @Transactional
    public TokenDomain findFirstWaiting() {
        return tokenRepository.findFirstWaiting();
    }

    /**
     * 대기중 토큰 수 조회
     *
     * @author  양종문
     * @since   2024-07-09
     * @return  Integer
     */
    @Transactional
    public Integer countWaiting() {
        return tokenRepository.countWaiting();
    }

    /**
     * 토큰 만료 대상 조회
     *
     * @author  양종문
     * @since   2024-07-10
     * @return  List<TokenDomain>
     */
    @Transactional
    public List<TokenDomain> findAllExpireTargetByCheckTime() {
        // 현재 시간에서 4분을 더 해준다. (토큰이 활성화가 된 후 약 4분이 지난 토큰들을 대상으로 하기 위함)
        LocalDateTime checkTime = LocalDateTime.now().plusMinutes(4);
        return tokenRepository.findAllExpireTargetByCheckTime(checkTime);
    }

    /**
     * 고객 토큰 저장
     *
     * @author  양종문
     * @since   2024-07-09
     * @param   tokenDomain - 토큰 도메인
     * @return  tokenDomain
     */
    @Transactional
    public TokenDomain save(TokenDomain tokenDomain) {
        return tokenRepository.save(tokenDomain);
    }

    /**
     * 고객 토큰 삭제
     *
     * @author  양종문
     * @since   2024-07-09
     * @param   tokenId - 토큰 ID
     */
    @Transactional
    public void deleteById(Long tokenId) {
        tokenRepository.deleteById(tokenId);
    }
}
