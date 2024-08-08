package com.concert.reservation.domain.token;

import com.concert.reservation.domain.exception.CustomException;
import com.concert.reservation.domain.token.entity.TokenRedis;
import com.concert.reservation.infrastructure.waitingQueue.WaitingQueueRedisRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;
    private final WaitingQueueRedisRepository waitingQueueRedisRepository;

    /**
     * 첫 번째 대기열 고객 상세조회
     *
     * @author  양종문
     * @since   2024-07-18
     * @return  TokenDomain
     */
    public TokenDomain findFirstWaiting() {
        return tokenRepository.findFirstWaiting();
    }

    /**
     * 대기열 등수를 포함한 고객 상세조회
     *
     * @author  양종문
     * @since   2024-07-18
     * @param   customerId - 고객 ID
     * @return  Integer
     */
    public TokenDomain findByCustomerIdWithRank(Long customerId) {
        // 토큰 조회
        Optional<TokenDomain> tokenDomain = Optional.of(this.findByCustomerId(customerId).orElseThrow(() -> new CustomException(HttpStatus.UNAUTHORIZED, "토큰 상세 정보가 없습니다.")));
        // 대기열 등수 조회
        tokenDomain.get().setRank(tokenRepository.findRankByCustomerId(customerId));

        return tokenDomain.get();
    }

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
     * 토큰 조회 (활성화가 된 지 4분이 지난 대상)
     *
     * @author  양종문
     * @since   2024-07-15
     * @return  TokenDomain
     */
    public List<TokenDomain> findAllActiveTokensOlderThanFourMinutes() {
        return tokenRepository.findAllActiveTokensOlderThanFourMinutes(LocalDateTime.now().minusMinutes(4));
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
            throw new CustomException(HttpStatus.ACCEPTED, "대기열 인원 수 초과");
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
    public void changeStatus(Long customerId, TokenStatus status) {
        // 토큰 조회
        Optional<TokenDomain> tokenDomain = Optional.of(this.findByCustomerId(customerId).orElseThrow(() -> new CustomException(HttpStatus.UNAUTHORIZED, "토큰 상세 정보가 없습니다.")));

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
     * 토큰 활성화 상태 체크
     *
     * @author  양종문
     * @since   2024-07-16
     * @param   customerId - 고객 ID
     */
    public void checkActiveStatus(Long customerId) {
        // 토큰 조회
        Optional<TokenDomain> tokenDomain = Optional.of(this.findByCustomerId(customerId).orElseThrow(() -> new CustomException(HttpStatus.UNAUTHORIZED, "토큰 상세 정보가 없습니다.")));

        // 토큰 활성화 상태 체크
        if (!TokenStatus.ACTIVE.equals(tokenDomain.get().getStatus())) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "토큰이 활성화 상태가 아닙니다.");
        }
    }

    /**
     * 모든 토큰 삭제
     *
     * @author  양종문
     * @since   2024-07-21
     */
    public void deleteAll() {
        tokenRepository.deleteAll();
    }

    /**
     * 토큰 발급
     *
     * @author  양종문
     * @since   2024-08-01
     * @param   customerId - 고객 ID
     * @return  TokenRedis
     */
    public TokenRedis issueTokenToRedis(Long customerId) {
        // Waiting 대기열 추가
        String uuid = waitingQueueRedisRepository.addWaitingQueue(customerId);

        return TokenRedis.builder().uuid(uuid).customerId(customerId).build();
    }

    /**
     * 대기열 토큰 상세조회
     *
     * @author  양종문
     * @since   2024-08-01
     * @param   uuid - uuid
     * @return  TokenRedis
     */
    public TokenRedis getWaitingTokenByUUIDFromRedis(String uuid) {
        // Waiting 대기열 목록 범위 조회
        Set<String> tokens = waitingQueueRedisRepository.getWaitingQueueRange(0, -1);
        if (tokens.isEmpty()) throw new CustomException(HttpStatus.NOT_FOUND, "토큰 정보가 존재하지 않습니다.");

        return tokens.stream().map(TokenRedis::toTokenRedis).filter(tokenRedis -> (uuid.equals(tokenRedis.getUuid()))).findFirst().orElseThrow(() -> new CustomException(HttpStatus.UNAUTHORIZED, "토큰 상세 정보가 없습니다."));
    }

    /**
     * 대기열 토큰 상세조회
     *
     * @author 양종문
     * @since  2024-08-01
     * @param  customerId - 고객 ID
     * @return TokenRedis
     */
    public Optional<TokenRedis> getWaitingTokenByCustomerIdFromRedis(Long customerId) {
        // Waiting 대기열 목록 범위 조회
        Set<String> tokens = waitingQueueRedisRepository.getWaitingQueueRange(0, -1);
        if (!tokens.isEmpty()) return tokens.stream().map(TokenRedis::toTokenRedis).filter(tokenRedis -> (customerId.equals(tokenRedis.getCustomerId()))).findFirst();

        return Optional.empty();
    }

    /**
     * 대기열 토큰 목록 조회
     *
     * @author  양종문
     * @since   2024-08-01
     * @param   start - 시작 값
     * @param   end - 끝 값
     * @return  List<TokenRedis>
     */
    public List<TokenRedis> getWaitingTokensFromRedis(Integer start, Integer end) {
        // Waiting 대기열 목록 범위 조회
        Set<String> tokens = waitingQueueRedisRepository.getWaitingQueueRange(start, end);
        if (!tokens.isEmpty()) {
            return tokens.stream().map(TokenRedis::toTokenRedis).toList();
        }

        return List.of();
    }

    /**
     * 대기중 토큰 삭제
     *
     * @author  양종문
     * @since   2024-08-01
     * @param   uuid - uuid
     * @param   customerId - 고객 ID
     * @return  Long
     */
    public Long deleteWaitingQueueByUUID(String uuid, Long customerId) {
        return waitingQueueRedisRepository.removeWaitingQueueByUUID(uuid, customerId);
    }

    /**
     * 활성화 토큰 조회
     *
     * @author  양종문
     * @since   2024-08-01
     * @return  List<TokenRedis>
     */
    public List<TokenRedis> getActiveTokensFromRedis() {
        // Redis 활성화 토큰 전체 조회
        Set<String> tokens = waitingQueueRedisRepository.getActiveQueue();
        if (!tokens.isEmpty()) {
            return tokens.stream().map(TokenRedis::toTokenRedis).toList();
        }

        return List.of();
    }

    /**
     * 활성화 토큰 상세조회
     *
     * @author 양종문
     * @since  2024-08-08
     * @param  customerId - 고객 ID
     * @return TokenRedis
     */
    public Optional<TokenRedis> getActiveTokenByCustomerIdFromRedis(Long customerId) {
        // Active 대기열 목록 범위 조회
        Set<String> tokens = waitingQueueRedisRepository.getActiveQueue();
        if (tokens.isEmpty()) throw new CustomException(HttpStatus.NOT_FOUND, "토큰 정보가 존재하지 않습니다.");

        return tokens.stream().map(TokenRedis::toTokenRedis).filter(tokenRedis -> (customerId.equals(tokenRedis.getCustomerId()))).findFirst();
    }

    /**
     * 토큰 활성화 상태 체크
     *
     * @author  양종문
     * @since   2024-08-01
     * @param   uuid - uuid
     */
    public void checkActiveStatusFromRedis(String uuid) {
        // 활성화 대기열 존재 여부 체크
        Boolean isExist = waitingQueueRedisRepository.existActiveQueueByUUID(uuid);
        if (!isExist) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "토큰이 활성화 상태가 아닙니다.");
        }
    }

    /**
     * 토큰 활성화 상태 수 조회
     *
     * @author  양종문
     * @since   2024-08-02
     * @return  Long
     */
    public Long countActiveTokensFromRedis() {
        return waitingQueueRedisRepository.countActiveTokens();
    }

    /**
     * 활성화 토큰 추가
     *
     * @author 양종문
     * @since 2024-08-02
     * @param uuid       - uuid
     * @param customerId - 고객 ID
     */
    public void addActiveQueue(String uuid, Long customerId) {
        waitingQueueRedisRepository.addActiveQueue(uuid, customerId);
    }

    /**
     * 활성화 토큰 삭제
     *
     * @author  양종문
     * @since   2024-08-01
     * @param   uuid - uuid
     * @param   customerId - 고객 ID
     * @param   expireDt - 만료일시
     * @return  Long
     */
    public Long deleteActiveQueueByUUID(String uuid, Long customerId, Long expireDt) {
        return waitingQueueRedisRepository.removeActiveQueueByUUID(uuid, customerId, expireDt);
    }
}
