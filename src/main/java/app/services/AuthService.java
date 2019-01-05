package app.services;

import app.domain.model.User;
import app.exceptions.NotAuthenticatedUserException;
import app.exceptions.UserDoesNotExistException;
import app.exceptions.WrongPasswordException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class AuthService {
    private final static String SECRET = "secret";
    private final static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    private final int EXPIRES_DELTA_IN_SECS = 6000;

    @Autowired
    private UserService userService;

    private final static JWTVerifier verifier = JWT.require(ALGORITHM)
            .withIssuer("auth0")
            .build();

    public String auth(String email, String password) {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new UserDoesNotExistException();
        }

        if (!user.getPassword().equals(password)) {
            throw new WrongPasswordException();
        }
        return generateJwtToken(user);
    }

    private String generateJwtToken(User user) {
        Date expiresAt = Date.from(Instant.now().plusSeconds(EXPIRES_DELTA_IN_SECS));

        return JWT.create()
                .withIssuer("auth0")
                .withSubject(user.getEmail())
                .withClaim("login", user.getLogin())
                .withExpiresAt(expiresAt)
                .sign(ALGORITHM);
    }

    public void checkAuthentication(User user, String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);
        DecodedJWT decodedJWT = verifier.verify(jwt);

        if (!user.getEmail().equals(decodedJWT.getSubject())) {
            throw new NotAuthenticatedUserException();
        }

        if (!user.getLogin().equals(decodedJWT.getClaim("login"))) {
            throw new NotAuthenticatedUserException();
        }
    }
}
