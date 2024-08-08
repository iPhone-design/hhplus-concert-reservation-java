package com.concert.reservation.infrastructure.concert;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ConcertOptionRedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    private static final String CONCERT_KEY = "CONCERT_OPTION";

    /**
     * 예약 가능 콘서트 추가
     *
     * @author  양종문
     * @since   2024-08-02
     * @param   concertOptionId - 콘서트 옵션 ID
     * @param   concertId - 콘서트 ID
     * @param   concertName - 콘서트명
     * @param   location - 장소
     * @param   openDt - 오픈일시
     * @return  Long
     */
    public Long addConcertOption(Long concertOptionId, Long concertId, String concertName, String location, LocalDateTime openDt) {
        return redisTemplate.opsForSet().add(CONCERT_KEY, String.format("%s:%s:%s:%s:%s", concertOptionId, concertId, concertName, location, openDt));
    }

    /**
     * 예약 가능 콘서트 목록 조회
     *
     * @author  양종문
     * @since   2024-08-02
     * @return  Set<String>
     */
    public Set<String> getConcertOptions() {
        return redisTemplate.opsForSet().members(CONCERT_KEY);
    }

    /**
     * 예약 가능 콘서트 목록 삭제
     *
     * @author  양종문
     * @since   2024-08-02
     * @return  Boolean
     */
    public Boolean deleteConcertOption() {
        return redisTemplate.delete(CONCERT_KEY);
    }
}