package Novi.Student.PurrB.Services;

import Novi.Student.PurrB.Exceptions.RecordNotFoundException;
import Novi.Student.PurrB.Models.User;
import Novi.Student.PurrB.Repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtService {

    private final static String SECRET_KEY = "yabbadabbadooyabbadabbadooyabbadabbadooyabbadabbadoo";

    @Autowired
    private UserRepository userRepository;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public User getUserByUsername(String username){

        Optional<User> optionalUser = userRepository.findById(username);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new RecordNotFoundException("Geen gebruiker gevonden");
        }
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T>
            claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }
    private String createToken(Map<String, Object> claims, String
            subject) {
        long validPeriod = 1000 * 60 * 60 * 24 * 10; // 10 days in ms
        long currentTime = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + validPeriod))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails
            userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) &&
                !isTokenExpired(token);
    }
}
