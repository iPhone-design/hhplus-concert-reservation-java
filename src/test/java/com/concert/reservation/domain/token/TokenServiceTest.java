package com.concert.reservation.domain.token;

import com.concert.reservation.domain.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {

    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private TokenService tokenService;

    @Test
    @DisplayName("첫 번째 대기열 고객 상세조회")
    void findFirstWaiting() {
        // given
        TokenDomain tokenDomain = TokenDomain.builder()
                                             .tokenId(1L)
                                             .customerId(1L)
                                             .status(TokenStatus.WAITING)
                                             .waitingStartDt(LocalDateTime.now())
                                             .build();

        // when
        when(tokenRepository.findFirstWaiting()).thenReturn(tokenDomain);

        TokenDomain result = tokenService.findFirstWaiting();

        // then
        assertThat(result).isEqualTo(tokenDomain);
    }

    @Test
    @DisplayName("대기열 등수를 포함한 고객 상세조회")
    void findByCustomerIdWithRank() {
        // given
        Long customerId = 1L;
        TokenDomain tokenDomain = TokenDomain.builder()
                                             .tokenId(1L)
                                             .customerId(customerId)
                                             .status(TokenStatus.WAITING)
                                             .waitingStartDt(LocalDateTime.now())
                                             .rank(10)
                                             .build();

        // when
        when(tokenRepository.findByCustomerId(customerId)).thenReturn(Optional.of(tokenDomain));
        when(tokenRepository.findRankByCustomerId(customerId)).thenReturn(10);

        TokenDomain result = tokenService.findByCustomerIdWithRank(customerId);

        // then
        assertThat(result.getRank()).isEqualTo(tokenDomain.getRank());
    }

    @Test
    @DisplayName("토큰 상세조회")
    void findByCustomerId() {
        // given
        Long customerId = 1L;
        TokenDomain tokenDomain = TokenDomain.builder()
                                             .tokenId(1L)
                                             .customerId(customerId)
                                             .status(TokenStatus.WAITING)
                                             .waitingStartDt(LocalDateTime.now())
                                             .build();

        // when
        when(tokenRepository.findByCustomerId(customerId)).thenReturn(Optional.of(tokenDomain));

        Optional<TokenDomain> result = tokenService.findByCustomerId(customerId);

        // then
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(tokenDomain);
    }

    @Test
    @DisplayName("토큰 조회 (활성화가 된 지 4분이 지난 대상)")
    void findAllActiveTokensOlderThanFourMinutes() {
        // given
        List<TokenDomain> tokenDomains = Arrays.asList(TokenDomain.builder().tokenId(1L).customerId(1L).status(TokenStatus.ACTIVE).entryDt(LocalDateTime.now().minusMinutes(5)).build()
                                                     , TokenDomain.builder().tokenId(2L).customerId(2L).status(TokenStatus.ACTIVE).entryDt(LocalDateTime.now().minusMinutes(10)).build());
        // when
        when(tokenRepository.findAllActiveTokensOlderThanFourMinutes(any(LocalDateTime.class))).thenReturn(tokenDomains);

        List<TokenDomain> results = tokenService.findAllActiveTokensOlderThanFourMinutes();

        // then
        assertThat(tokenDomains.size()).isEqualTo(results.size());
    }

    @Test
    @DisplayName("활성화 토큰 수 체크")
    void checkActiveStatusCount() {
        // given
        List<TokenDomain> tokenDomains = new ArrayList<>();
        for (int idx = 0; idx < 105; idx++) {
            tokenDomains.add(TokenDomain.builder().tokenId(idx + 1L).customerId(idx + 1L).status(TokenStatus.ACTIVE).entryDt(LocalDateTime.now().minusMinutes(5)).build());
        }

        // when
        when(tokenRepository.findActive()).thenReturn(tokenDomains);

        // then
        assertThatThrownBy(() -> tokenService.checkActiveStatusCount()).isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("토큰 발급 (이미 토큰이 존재하는 경우)")
    void issueToken_ExistingToken() {
        // given
        Long customerId = 1L;
        TokenDomain tokenDomain = TokenDomain.builder()
                                             .tokenId(1L)
                                             .customerId(customerId)
                                             .status(TokenStatus.ACTIVE)
                                             .waitingStartDt(LocalDateTime.now())
                                             .build();

        // when
        when(tokenService.findByCustomerId(customerId)).thenReturn(Optional.of(tokenDomain));
        when(tokenRepository.save(any(TokenDomain.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TokenDomain result = tokenService.issueToken(customerId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo(TokenStatus.WAITING);

        verify(tokenRepository, times(1)).findByCustomerId(customerId);
        verify(tokenRepository, times(1)).save(tokenDomain);
    }

    @Test
    @DisplayName("토큰 발급 (토큰이 존재하지 않는 경우)")
    void issueToken_NotExistingToken() {
        // given
        Long customerId = 1L;
        TokenDomain tokenDomain = TokenDomain.builder()
                                             .customerId(customerId)
                                             .status(TokenStatus.WAITING)
                                             .waitingStartDt(LocalDateTime.now())
                                             .build();

        // when
        when(tokenService.findByCustomerId(customerId)).thenReturn(Optional.empty());
        when(tokenRepository.save(any(TokenDomain.class))).thenReturn(tokenDomain);

        TokenDomain result = tokenService.issueToken(customerId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo(TokenStatus.WAITING);
        assertThat(result.getCustomerId()).isEqualTo(customerId);
    }

    @Test
    @DisplayName("토큰 상태 값 변경")
    void changeStatus() {
        // given
        Long customerId = 1L;
        TokenDomain tokenDomain = TokenDomain.builder()
                                             .tokenId(1L)
                                             .customerId(customerId)
                                             .status(TokenStatus.WAITING)
                                             .waitingStartDt(LocalDateTime.now())
                                             .build();

        // when
        when(tokenRepository.findByCustomerId(customerId)).thenReturn(Optional.of(tokenDomain));

        tokenService.changeStatus(customerId, TokenStatus.ACTIVE);

        // then
        verify(tokenRepository, times(1)).save(tokenDomain);
    }

    @Test
    @DisplayName("토큰 활성화 상태 체크")
    void checkActiveStatus() {
        // given
        Long customerId = 1L;
        TokenDomain tokenDomain = TokenDomain.builder()
                                             .tokenId(1L)
                                             .customerId(customerId)
                                             .status(TokenStatus.WAITING)
                                             .waitingStartDt(LocalDateTime.now())
                                             .build();

        when(tokenRepository.findByCustomerId(customerId)).thenReturn(Optional.of(tokenDomain));

        assertThatThrownBy(() -> tokenService.checkActiveStatus(customerId)).isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("모든 토큰 삭제")
    void deleteAll() {
        // given-when
        doNothing().when(tokenRepository).deleteAll();

        tokenService.deleteAll();

        // then
        verify(tokenRepository, times(1)).deleteAll();
    }
}