package com.concert.reservation.application.token;

import com.concert.reservation.domain.token.TokenDomain;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenFacadeTest {

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private TokenFacade tokenFacade;

    private Long customerId;
    private TokenDomain existingToken;
    private TokenDomain newToken1;
    private TokenDomain newToken2;

    @BeforeEach
    void setUp() {
        customerId = 1L;
        existingToken = TokenDomain.builder().tokenId(1L).customerId(customerId).status("ACTIVE").build();
        newToken1 = TokenDomain.builder().tokenId(1L).customerId(customerId).status("WAITING").build();
        newToken2 = TokenDomain.builder().tokenId(2L).customerId(customerId).status("WAITING").build();
    }

    @Test
    @Description("기존에 토큰이 존재하는 경우")
    void issueToken_withExistingToken() {
        // given
        when(tokenService.findByCustomerId(customerId)).thenReturn(existingToken);
        when(tokenService.save(any(TokenDomain.class))).thenReturn(newToken2);

        // when
        TokenDomain result = tokenFacade.issueToken(customerId);

        // then
        verify(tokenService).deleteById(existingToken.getTokenId());
        verify(tokenService).save(any(TokenDomain.class));
        assertNotNull(result);
        assertEquals(2, result.getTokenId());
    }

    @Test
    @Description("기존에 토큰이 존재하지 않는 경우")
    void issueToken_withoutExistingToken() {
        // given
        when(tokenService.findByCustomerId(customerId)).thenReturn(null);
        when(tokenService.save(any(TokenDomain.class))).thenReturn(newToken1);

        // when
        TokenDomain result = tokenFacade.issueToken(customerId);

        // then
        verify(tokenService).save(any(TokenDomain.class));
        assertNotNull(result);
        assertEquals(1L, result.getTokenId());
    }
}