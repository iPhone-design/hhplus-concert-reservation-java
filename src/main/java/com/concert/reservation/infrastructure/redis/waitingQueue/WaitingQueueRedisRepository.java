package com.concert.reservation.infrastructure.redis.waitingQueue;

import com.concert.reservation.domain.token.entity.TokenRedis;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class WaitingQueueRedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    private static final String WAITING_KEY = "WAITING";
    private static final String ACTIVE_KEY = "ACTIVE";

    /**
     * Waiting 토큰 추가
     *
     * @author 양종문
     * @since  2024-08-01
     * @param  customerId - 고객 ID
     * @return uuid
     */
    public String addWaitingQueue(Long customerId) {
        String uuid = String.valueOf(UUID.randomUUID());

        redisTemplate.opsForZSet().add(WAITING_KEY, TokenRedis.toSerialize(uuid, customerId), System.currentTimeMillis());

        return uuid;
    }

    /**
     * Waiting 토큰 목록 범위 조회
     *
     * @author  양종문
     * @since   2024-08-01
     * @param   start - 시작 값
     * @param   end - 끝 값
     * @return  Set<String>
     */
    public Set<String> getWaitingQueueRange(Integer start, Integer end) {
        return redisTemplate.opsForZSet().range(WAITING_KEY, start, end);
    }

    /**
     * Waiting 토큰 삭제
     *
     * @author 양종문
     * @since  2024-08-01
     * @param  uuid - uuid
     * @param  customerId - 고객 ID
     * @return Long
     */
    public Long removeWaitingQueueByUUID(String uuid, Long customerId) {
        return redisTemplate.opsForZSet().remove(WAITING_KEY, String.format("%s:%s", uuid, String.valueOf(customerId)));
    }

    /**
     * Active 토큰 추가 (만료 시간 5분)
     *
     * @author 양종문
     * @since  2024-08-01
     * @param  uuid - uuid
     * @param  customerId - 고객 ID
     */
    public void addActiveQueue(String uuid, Long customerId) {
        // 5분을 밀리초로 변환
        long fiveMinutesInMillis = 5 * 60 * 1000;

        redisTemplate.opsForSet().add(ACTIVE_KEY, TokenRedis.toSerialize(uuid, customerId, System.currentTimeMillis() + fiveMinutesInMillis));
    }

    /**
     * Active 토큰 토큰 수 조회
     *
     * @author  양종문
     * @since   2024-08-01
     */
    public Long countActiveTokens() {
        return redisTemplate.opsForSet().size(ACTIVE_KEY);
    }

    /**
     * Active 토큰 목록 조회
     *
     * @author  양종문
     * @since   2024-08-01
     * @return  Set<String>
     */
    public Set<String> getActiveQueue() {
        return redisTemplate.opsForSet().members(ACTIVE_KEY);
    }

    /**
     * Active 토큰 삭제
     *
     * @author  양종문
     * @since   2024-08-01
     * @param   uuid - uuid
     * @param   customerId - 고객 ID
     * @param   expireDt - 만료일시
     * @return  Long
     */
    public Long removeActiveQueueByUUID(String uuid, Long customerId, Long expireDt) {
        return redisTemplate.opsForSet().remove(ACTIVE_KEY, String.format("%s:%s:%s", uuid, customerId, expireDt));
    }
}