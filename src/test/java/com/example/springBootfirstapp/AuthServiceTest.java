package com.example.springBootfirstapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @Mock
    private Userservice userservice;

    @Mock
    private JwtProvider jwtProvider;

    @InjectMocks
    private AuthService authService;

    @Test
    public void createTokensIfPasswordCorrectShouldReturnTokens() {
        User user = new User();
        user.setLogin("zhanel");
        user.setPassword("1234");
        user.setFirstName("Zhanel");
        user.setLastName("Nau");
        user.setRoles(Collections.singleton(Role.USER));
        JwtRequest request = new JwtRequest();
        request.setLogin("zhanel");
        request.setPassword("1234");

        when(jwtProvider.generateAccessToken(user)).thenReturn("access-token");
        when(jwtProvider.generateRefreshToken(user)).thenReturn("refresh-token");
        JwtResponse response = authService.createTokensIfPasswordCorrect(user, request);

        assertEquals("access-token", response.getAccessToken());
        assertEquals("refresh-token", response.getRefreshToken());
    }

    @Test
    public void loginReturnsTokenIfPasswordPasses(){
        User user = new User();
        user.setLogin("zhanel2");
        user.setPassword("1234");
        user.setFirstName("zhan");
        user.setLastName("nel");
        user.setRoles(Collections.singleton(Role.USER));

        JwtRequest request=new JwtRequest();
        request.setLogin("zhanel2");
        request.setPassword(user.getPassword());

        when(userservice.getByLogin("zhanel2")).thenReturn(Optional.of(user));

        when(jwtProvider.generateAccessToken(user)).thenReturn("access-token");
        when(jwtProvider.generateRefreshToken(user)).thenReturn("refresh-token");

        JwtResponse response = authService.login(request);

        assertEquals("access-token", response.getAccessToken());
        assertEquals("refresh-token",response.getRefreshToken());
    }

}