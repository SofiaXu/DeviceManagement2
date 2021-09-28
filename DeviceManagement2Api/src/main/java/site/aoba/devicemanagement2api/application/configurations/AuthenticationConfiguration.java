package site.aoba.devicemanagement2api.application.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class AuthenticationConfiguration {
    @Value("${site.aoba.authorization.password.salt}")
    private String salt = "773C542543664C65B30E713CFD5845E6";

    @Bean
    public Pbkdf2PasswordEncoder providePasswordEncoder() {
        Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder(salt, 10000,256/8);
        encoder.setAlgorithm(Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
        return encoder;
    }
}
