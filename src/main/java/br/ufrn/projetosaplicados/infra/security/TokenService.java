package br.ufrn.projetosaplicados.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.ufrn.projetosaplicados.model.Usuario;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String segredo;

    public String generateToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredo);

            String token = JWT.create()
                .withIssuer("PROJETO-PA1")
                .withSubject(usuario.getEmail())
                .withExpiresAt(this.generateExpirationDate())
                .sign(algorithm);
            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("erro quando autenticando");
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredo);

            return JWT.require(algorithm)
                    .withIssuer("PROJETO-PA1")
                    .build().verify(token).getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2)
                .toInstant(ZoneOffset.of("-3"));
    }
}
