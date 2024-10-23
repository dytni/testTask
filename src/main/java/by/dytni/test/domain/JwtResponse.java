package by.dytni.test.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
///
///Класс для конфигурации ответа
///
@Getter
@AllArgsConstructor
public class JwtResponse {

    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;

}
