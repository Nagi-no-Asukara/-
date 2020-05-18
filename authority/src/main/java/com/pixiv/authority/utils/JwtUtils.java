package com.pixiv.authority.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUtils {

    /*
     * 解析 获得数据
     */
    public static Claims jwtParse(String token,String key)
    {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

        return claims;
    }
}
