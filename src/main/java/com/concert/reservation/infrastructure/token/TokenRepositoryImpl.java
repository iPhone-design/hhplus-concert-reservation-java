package com.concert.reservation.infrastructure.token;

import com.concert.reservation.domain.token.TokenDomain;
import com.concert.reservation.domain.token.TokenRepository;
import com.concert.reservation.domain.token.entity.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository {

    private final TokenJpaRepository tokenJpaRepository;

    /**
     * 첫 번째 대기열 고객 상세조회
     *
     * @author  양종문
     * @since   2024-07-18
     * @return  TokenDomain
     */
    @Override
    public TokenDomain findFirstWaiting() {
        return tokenJpaRepository.findFirstWaiting().toDomain();
    }

    /**
     * 토큰 상세조회
     *
     * @author  양종문
     * @since   2024-07-09
     * @param   customerId - ID
     * @return  tokenDomain
     */
    @Override
    public Optional<TokenDomain> findByCustomerId(Long customerId) {
        return tokenJpaRepository.findByCustomerId(customerId).map(Token::toDomain);
    }

    /**
     * 활성화 토큰 조회
     *
     * @author  양종문
     * @since   2024-07-09
     * @return  List<TokenDomain>
     */
    @Override
    public List<TokenDomain> findActive() {
        return tokenJpaRepository.findActive().stream()
                                              .map(Token::toDomain)
                                              .collect(Collectors.toList());
    }

    /**
     * 토큰 저장
     *
     * @author  양종문
     * @since   2024-07-09
     * @param   tokenDomain - 토큰 도메인
     * @return  tokenDomain
     */
    @Override
    public TokenDomain save(TokenDomain tokenDomain) {
        return tokenJpaRepository.save(Token.toEntity(tokenDomain)).toDomain();
    }

    /**
     * 토큰 상태 값 일괄 변경 (활성화 → 대기)
     *
     * @author  양종문
     * @since   2024-07-15
     * @return  Integer
     */
    @Override
    public Integer bulkStatusToWaiting(LocalDateTime currentDt, LocalDateTime expireDt) {
        return tokenJpaRepository.bulkStatusToWaiting(currentDt, expireDt);
    }
}