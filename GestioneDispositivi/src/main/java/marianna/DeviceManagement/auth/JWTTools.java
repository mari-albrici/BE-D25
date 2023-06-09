package marianna.DeviceManagement.auth;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import marianna.DeviceManagement.entities.User;
import marianna.DeviceManagement.exceptions.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Jwts;
import java.util.Date;

@Component
public class JWTTools {
    private static String secret;
    private static int expiration;

    @Value("${spring.application.jwt.secret}")
    public void setSecret(String secretKey) {
        secret = secretKey;
    }

    @Value("${spring.application.jwt.expirationindays}")
    public void setExpiration(String expirationInDays) {
        expiration = Integer.parseInt(expirationInDays) * 24 * 60 * 60 * 1000;
    }

    static public String createToken(User u) {
        String token = Jwts.builder().setSubject(u.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();
        return token;
    }

    static public void isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        } catch (MalformedJwtException e) {
            throw new UnauthorizedException("Token is not valid");
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException("Token is expired");
        } catch (Exception e) {
            throw new UnauthorizedException("There have been token problems, please login again.");
        }
    }

    static public String extractSubject(String token) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token)
                .getBody().getSubject();
    }
}
