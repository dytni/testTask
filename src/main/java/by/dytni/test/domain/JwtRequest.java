package by.dytni.test.domain;

import lombok.Getter;
import lombok.Setter;

///
///Класс для конфигурации запроса
///
@Setter
@Getter
public class JwtRequest {

    private String username;
    private String password;

}
