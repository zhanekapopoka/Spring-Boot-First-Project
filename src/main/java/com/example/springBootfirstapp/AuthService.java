package com.example.springBootfirstapp;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final Userservice userservice;
    final JwtProvider jwtProvider;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final UserRepository userRepository;

    public AuthService(Userservice userservice, JwtProvider jwtProvider, UserRepository userRepository) {
        this.jwtProvider = jwtProvider;
        this.userservice = userservice;
        this.userRepository = userRepository;
    }

    public JwtResponse login(JwtRequest authrequest) {
        User user = findUserFromRequest(authrequest);

        return createTokensIfPasswordCorrect(user, authrequest);
    }

    private User findUserFromRequest(JwtRequest authrequest) {
        return userservice.getByLogin(authrequest.getLogin())
                .orElseThrow(() -> new AuthException("Пользователь не найден"));
    }

     JwtResponse createTokensIfPasswordCorrect(User user, JwtRequest authrequest) {
        if (user.getPassword().equals(authrequest.getPassword())) {
            final String accesstoken = jwtProvider.generateAccessToken(user);
            final String refreshtoken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getLogin(), refreshtoken);
            return new JwtResponse(accesstoken, refreshtoken);
        } else {
            throw new AuthException("Неправильный пароль");}
    }

    public JwtResponse login(String refreshtoken) {
        if (jwtProvider.validateRefreshToken(refreshtoken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshtoken);
            final String login = claims.getSubject();
            final String saveRefreshTokin = refreshStorage.get(login);
            if (saveRefreshTokin != null && saveRefreshTokin.equals(refreshtoken)) {
                final User user = userservice.getByLogin(login)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse getAccessToken(String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userservice.getByLogin(login)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }

    public JwtResponse refresh(String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userservice.getByLogin(login)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getLogin(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Невалидный JWT токен");
    }

    public String register(RegisterRequest request) {
        if (userRepository.existsByLogin(request.getLogin())) {
            throw new AuthException("Пользователь уже существует");
        }
        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "Пользователь успешно добавлен";
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}