package site.aoba.devicemanagement2api.application.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import site.aoba.devicemanagement2api.infrastructure.mappers.UserMapper;
import site.aoba.devicemanagement2api.infrastructure.models.UserModel;

import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationService {
    private final UserMapper userMapper;
    private final Pbkdf2PasswordEncoder encoder;
    @Value("${site.aoba.authorization.jwt.key}")
    private String jwtKey = "773C542543664C65B30E713CFD5845E6";
    @Value("${site.aoba.authorization.jwt.issuer}")
    private String jwtIssuer = "site.aoba.authorization";

    /**
     * Login
     * @param userId userId
     * @param password password
     * @return jwt token or null when incorrect
     */
    public String login(Long userId, String password) {
        UserModel user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return null;
        }
        if (!encoder.matches(password, user.getUserPasswordHash())) {
            return null;
        }
        Date exp = new Date();
        exp.setTime(exp.getTime() + 86400000);
        return JWT.create()
                .withIssuer(jwtIssuer)
                .withClaim("role", user.getUserTypeId())
                .withClaim("userId", userId)
                .withClaim("userName", user.getUserName())
                .withIssuedAt(new Date())
                .withExpiresAt(exp)
                .sign(Algorithm.HMAC256(jwtKey));
    }
}
