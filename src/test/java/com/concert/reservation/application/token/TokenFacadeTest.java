package com.concert.reservation.application.token;

import com.concert.reservation.domain.exception.CustomException;
import com.concert.reservation.domain.token.TokenDomain;
import com.concert.reservation.domain.token.TokenService;
import com.concert.reservation.domain.token.TokenStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TokenFacadeTest {

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private TokenFacade tokenFacade;

    @Test
    @DisplayName("고객 토큰 발급")
    void issueToken() {
        // given
        Long customerId = 1L;
        TokenDomain tokenDomain = new TokenDomain();
        tokenDomain.setCustomerId(customerId);

        // when
        when(tokenService.issueToken(customerId)).thenReturn(tokenDomain);

        TokenDomain result = tokenFacade.issueToken(customerId);

        // then
        assertEquals(tokenDomain.getCustomerId(), result.getCustomerId());
        verify(tokenService, times(1)).issueToken(customerId);
    }

    @Test
    @DisplayName("고객 토큰 유효성 체크")
    void checkActiveStatus() {
        // given
        Long customerId = 1L;

        // when-then
        tokenFacade.checkActiveStatus(customerId);
        verify(tokenService, times(1)).checkActiveStatus(customerId);
    }

    @Test
    @DisplayName("토큰이 존재하고 첫 번째 순서의 대기열인 경우")
    void findByCustomerIdAndThenChangeStatusToActive_whenTokenExistsAndIsFirstWaiting() {
        // given
        Long customerId = 1L;
        TokenDomain tokenDomain = new TokenDomain();
        tokenDomain.setCustomerId(customerId);

        // when
        when(tokenService.findByCustomerId(customerId)).thenReturn(Optional.of(tokenDomain));
        doNothing().when(tokenService).checkActiveStatusCount();
        when(tokenService.findFirstWaiting()).thenReturn(tokenDomain);
        doNothing().when(tokenService).changeStatus(customerId, TokenStatus.ACTIVE);
        when(tokenService.findByCustomerId(customerId)).thenReturn(Optional.of(tokenDomain));

        TokenDomain result = tokenFacade.findByCustomerIdAndThenChangeStatusToActive(customerId);

        // then
        assertEquals(tokenDomain.getStatus(), result.getStatus());
        verify(tokenService, times(2)).findByCustomerId(customerId);
        verify(tokenService, times(1)).checkActiveStatusCount();
        verify(tokenService, times(1)).findFirstWaiting();
        verify(tokenService, times(1)).changeStatus(customerId, TokenStatus.ACTIVE);
    }

    @Test
    @DisplayName("토큰이 존재하고 첫 번째 순서의 대기열이 아닌 경우")
    void findByCustomerIdAndThenChangeStatusToActive_whenTokenExistsAndIsNotFirstWaiting() {
        // given
        Long customerId = 1L;
        Long otherCustomerId = 2L;
        TokenDomain tokenDomain1 = new TokenDomain();
        tokenDomain1.setCustomerId(otherCustomerId);
        TokenDomain tokenDomain2 = new TokenDomain();
        tokenDomain2.setCustomerId(customerId);

        // when
        when(tokenService.findByCustomerId(customerId)).thenReturn(Optional.of(tokenDomain2));
        doNothing().when(tokenService).checkActiveStatusCount();
        when(tokenService.findFirstWaiting()).thenReturn(tokenDomain1);
        when(tokenService.findByCustomerIdWithRank(customerId)).thenReturn(tokenDomain2);

        TokenDomain result = tokenFacade.findByCustomerIdAndThenChangeStatusToActive(customerId);

        // then
        assertEquals(tokenDomain2, result);
        verify(tokenService, times(1)).findByCustomerId(customerId);
        verify(tokenService, times(1)).checkActiveStatusCount();
        verify(tokenService, times(1)).findFirstWaiting();
        verify(tokenService, times(1)).findByCustomerIdWithRank(customerId);
        verify(tokenService, never()).changeStatus(anyLong(), any(TokenStatus.class));
    }

    @Test
    @DisplayName("토큰이 존재하지 않는 경우")
    void findByCustomerIdAndThenChangeStatusToActive_whenTokenDoesNotExist() {
        // given
        Long customerId = 1L;

        // when
        when(tokenService.findByCustomerId(customerId)).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> {
            tokenFacade.findByCustomerIdAndThenChangeStatusToActive(customerId);
        });

        // then
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
        assertEquals("토큰 상세 정보가 없습니다.", exception.getDetail());
        verify(tokenService, times(1)).findByCustomerId(customerId);
        verify(tokenService, never()).checkActiveStatusCount();
        verify(tokenService, never()).findFirstWaiting();
        verify(tokenService, never()).changeStatus(anyLong(), any(TokenStatus.class));
    }
}