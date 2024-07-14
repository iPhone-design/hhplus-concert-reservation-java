package com.concert.reservation.infrastructure.token;

import com.concert.reservation.domain.token.TokenRepository;
import com.concert.reservation.domain.token.TokenCommand;
import com.concert.reservation.domain.token.TokenDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository {

    private final TokenJpaRepository tokenJpaRepository;

    /**
     * 고객 토큰 조회
     *
     * @author  양종문
     * @since   2024-07-09
     * @param   customerId - 고객 ID
     * @return  tokenDomain
     */
    @Override
    public TokenDomain findByCustomerId(Long customerId) {
        return TokenCommand.toDomain(tokenJpaRepository.findByCustomerId(customerId));
    }

    /**
     * 첫 번째 대기중 토큰 조회
     *
     * @author  양종문
     * @since   2024-07-09
     * @return  tokenDomain
     */
    @Override
    public TokenDomain findFirstWaiting() {
        return TokenCommand.toDomain(tokenJpaRepository.findFirstWaiting());
    }

    /**
     * 활성화 토큰 수 조회
     *
     * @author  양종문
     * @since   2024-07-11
     * @return  Integer
     */
    @Override
    public Integer countActive() {
        return tokenJpaRepository.countActive();
    }

    /**
     * 대기중 토큰 수 조회
     *
     * @author  양종문
     * @since   2024-07-10
     * @return  Integer
     */
    @Override
    public Integer countWaiting() {
        return tokenJpaRepository.countWaiting();
    }

    /**
     * 토큰 만료 대상 조회
     *
     * @author  양종문
     * @since   2024-07-10
     * @param   checkTime - 체크 시간 (현재시간 + 4분)
     * @return  List<TokenDomain>
     */
    @Override
    public List<TokenDomain> findAllExpireTargetByCheckTime(LocalDateTime checkTime) {
        return tokenJpaRepository.findAllExpireTargetByCheckTime(checkTime).stream()
                                                                           .map(TokenCommand::toDomain)
                                                                           .collect(Collectors.toList());
    }

    /**
     * 고객 토큰 저장
     *
     * @author  양종문
     * @since   2024-07-09
     * @param   tokenDomain - 토큰 도메인
     * @return  tokenDomain
     */
    @Override
    public TokenDomain save(TokenDomain tokenDomain) {
        return TokenCommand.toDomain(tokenJpaRepository.save(TokenCommand.toEntity(tokenDomain)));
    }

    /**
     * 고객 토큰 상태 수정
     *
     * @author  양종문
     * @since   2024-07-11
     * @param   tokenDomain - 토큰 도메인
     */
    @Override
    public void modifyStatus(TokenDomain tokenDomain) {
        tokenJpaRepository.updateStatus(tokenDomain.getTokenId(), tokenDomain.getStatus(), tokenDomain.getEntryDt());
    }

    /**
     * 고객 토큰 삭제
     *
     * @author  양종문
     * @since   2024-07-09
     * @param   tokenId - 토큰 ID
     */
    @Override
    public void deleteById(Long tokenId) {
        tokenJpaRepository.deleteById(tokenId);
    }
}