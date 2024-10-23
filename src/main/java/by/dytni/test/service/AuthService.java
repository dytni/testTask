package by.dytni.test.service;

import by.dytni.test.domain.JwtProvider;
import by.dytni.test.domain.JwtRequest;
import by.dytni.test.domain.JwtResponse;
import by.dytni.test.models.User;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;

    //логин пользователя и проверка за хэшированного пароля и обновление токена
    public JwtResponse login(@NonNull JwtRequest authRequest) throws AuthException {
        final User user = userService.getByUsername(authRequest.getUsername())
                .orElseThrow(() -> new AuthException("Пользователь не найден"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(authRequest.getPassword(),user.getPassword())) {
            throw new AuthException("Неправильный пароль");
        }

        final String accessToken = jwtProvider.generateAccessToken(user);
        final String refreshToken = jwtProvider.generateRefreshToken(user);
        refreshStorage.put(user.getUsername(), refreshToken);
        return new JwtResponse(accessToken, refreshToken);
    }
    //метод для получения токена
    public JwtResponse getAccessToken(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String username = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(username);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByUsername(username)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }
    //метод для обновления токена устанавливает 10 дней валидности
    public JwtResponse refresh(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String username = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(username);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByUsername(username)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getUsername(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Невалидный JWT токен");
    }

}
