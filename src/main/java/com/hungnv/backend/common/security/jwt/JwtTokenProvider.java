package com.hungnv.backend.common.security.jwt;

import com.hungnv.backend.common.config.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Component
public class JwtTokenProvider {
    private final JwtProperties props;
    private final Key key;

    public JwtTokenProvider(JwtProperties props) {
        this.props = props;
        this.key = Keys.hmacShaKeyFor(normalizeSecret(props.getSecret()));
    }

    public String generateToken(String subject, Map<String, Object> claims) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(props.getExpirationSeconds());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuer(props.getIssuer())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(exp))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getSubject(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public Instant getExpiration(String token) {
        Date exp = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getExpiration();
        return exp.toInstant();
    }

    private static byte[] normalizeSecret(String secret) {
        Objects.requireNonNull(secret);
        byte[] raw;
        try {
            raw = Decoders.BASE64.decode(secret);
        } catch (Exception ex) {
            raw = secret.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        }
        if (raw.length >= 32) return raw;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(raw);
        } catch (Exception ex) {
            return Arrays.copyOf(raw, 32);
        }
    }
}
