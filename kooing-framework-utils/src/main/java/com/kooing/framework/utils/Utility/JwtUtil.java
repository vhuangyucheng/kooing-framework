package com.kooing.framework.utils.Utility;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : kooing
 * @date : 2017/12/4 - 11:15
 * @desription :
 * @update by :
 */
@Slf4j
public class JwtUtil {
    private static final String KEY = "KOOING-SAAS";
    public static String getToken(int memberId){
        String compactJws = Jwts.builder()
                .setId(String.valueOf(memberId))
                .signWith(SignatureAlgorithm.HS512, KEY.getBytes())
                .compact();

        return compactJws;
    }
    public static String checkToken(String token){
        String id;
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey( KEY.getBytes())
                    .parseClaimsJws( token).getBody();
            id = claims.getId();
        }catch (Exception e){
            return "errer";
        }
        return id;
    }
}
