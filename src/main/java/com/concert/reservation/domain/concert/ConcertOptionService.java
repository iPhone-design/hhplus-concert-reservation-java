package com.concert.reservation.domain.concert;

import com.concert.reservation.infrastructure.concert.ConcertOptionRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcertOptionService {

    private final ConcertOptionRepository concertOptionRepository;
    private final ConcertOptionRedisRepository concertOptionRedisRepository;

    /**
     * 예약 가능 콘서트 조회
     *
     * @author  양종문
     * @since   2024-07-10
     * @history 2024-08-02 양종문 - 예약 가능 콘서트를 조회 후 Redis 예약 가능 콘서트 목록을 추가
     * @return  List<ConcertOptionDomain>
     */
    public List<ConcertOptionDomain> findAllAvailableConcertForReservation() {
        // 현재일시
        LocalDateTime currentDt = LocalDateTime.now();

        // 예약 가능 콘서트 조회
        List<ConcertOptionDomain> concertOptionDomains = concertOptionRepository.findAllAvailableConcertForReservation(currentDt);

        for (ConcertOptionDomain concertOptionDomain : concertOptionDomains) {
            // Redis 예약 가능 콘서트 목록을 추가
            concertOptionRedisRepository.addConcertOption(concertOptionDomain.getConcertOptionId(), concertOptionDomain.getConcertId(), concertOptionDomain.getConcertName(), concertOptionDomain.getLocation(), concertOptionDomain.getOpenDt());
        }

        return concertOptionDomains;
    }

    /**
     * 예약 가능 콘서트 조회
     *
     * @author  양종문
     * @since   2024-08-02
     * @return  List<ConcertOptionDomain>
     */
    public List<ConcertOptionDomain> getConcertOptionsFromRedis() {
        // 현재일시
        LocalDateTime currentDt = LocalDateTime.now();

        // 예약 가능 콘서트 조회
        return concertOptionRedisRepository.getConcertOptions().stream().map(ConcertOptionDomain::toConcertOptionDomain).toList();
    }
}
