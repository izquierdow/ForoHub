package alura.foro.api.infra.security;

import alura.foro.api.domain.moderador.Moderador;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    /**
     * Genera un token JWT (JSON Web Token) a partir de un objeto Moderador.
     */
    public String generateToken(Moderador moderador) {
        try {
            // Configura el algoritmo de firma HMAC256 con el secreto de la API.
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            // Crea un token JWT con los datos del moderador y firma con el algoritmo.
            return JWT.create()
                    .withIssuer("alura.foro")
                    .withSubject(moderador.getUsername())
                    .withClaim("id", moderador.getId())
                    .withExpiresAt(generarExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }
    }

    /**
     * Obtiene el sujeto (subject) del token JWT.
     */
    public String getSubject(String token) {
        if (token == null) {  // Validación: El token no debe ser nulo.
            throw new RuntimeException();
        }

        DecodedJWT verifier = null;

        try {
            // Configura el algoritmo de verificación HMAC256 con el secreto de la API.
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            // Verifica y decodifica el token.
            verifier = JWT.require(algorithm)
                    .withIssuer("alura.foro")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }

        if (verifier == null || verifier.getSubject() == null) {
            throw new RuntimeException("Verificador inválido");
        }

        // Devuelve el sujeto (subject) del token.
        return verifier.getSubject();
    }

    /**
     * Genera una marca de tiempo (Instant) para la expiración del token, 12 horas en el futuro.
     */
    public Instant generarExpiracion() {
        return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-03:00"));
    }
}