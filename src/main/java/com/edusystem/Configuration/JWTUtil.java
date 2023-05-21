package com.edusystem.Configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {
    final long ONE_MINUTE_IN_MILLIS = 60000;
    final long EXPIRATION_TIME = 8 * 60 * ONE_MINUTE_IN_MILLIS; // thay thế cho timeUnit
    final private Key jwtKey = new SecretKeySpec("secret".getBytes(), SignatureAlgorithm.HS256.getJcaName());

    // 1.1 tạo token
    public String createToken(Map<String,Object> claims, UserDetails userDetails){
        return Jwts.builder().setClaims(claims)
                .setSubject(userDetails.getUsername())
                .claim("authorities",userDetails.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
                .signWith(jwtKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("authorities",userDetails.getAuthorities());
        return createToken(claims, userDetails);
    }

//    public String generateToken(UserDetails userDetails){
//        Map<String,Object> claims = new HashMap<>();
//        return createToken(claims, userDetails);
//    }

    //backbackup   public String generateToken(UserDetails userDetails,Map<String,Object> claims){
//        return createToken(claims,userDetails);
//    }



    // validate
    public boolean isTokenExpirated(String token){
        Date dateExp = extractClaims(token, Claims::getExpiration);
        return dateExp.before(new Date());
    }

//    public boolean hasClaim(String token, String claimName){
//        final Claims claims = extractAllClaims(token);
//        return claims.get(claimName) != null;
//    }

    // 2.truy cập tất cả thông tin xác thực
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 3.xác thực thông tin người dùng và quyền truy cặp dưới dạng key-value JWT
    public <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
        final Claims claims = extractAllClaims(token);
        return  claimsTFunction.apply(claims);
    }

    // 4.lấy thông tin người dùng
    public String extractUserName (String token){
        return extractClaims(token, Claims::getSubject);
    }

    // 5. kiểm tra token valid
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        return  userName.equals(userDetails.getUsername())
                && !isTokenExpirated(token);
    }
}
