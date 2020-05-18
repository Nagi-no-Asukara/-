package com.pixiv.authority.config.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Date;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtToken {

    //私钥
    private  String key;

    //签名失效时间 毫秒
    private Long ttl;


    /**
     * id: 登录用户的id
     * subjec：登录用户名
     * map: 要填入的数据
     * @return
     */
    public String createJwt(String id, String name, Map<String,Object> map){

        //设置失效时间
        long now=System.currentTimeMillis(); //当前毫秒
        long exp=now+ttl;


        JwtBuilder jwtBuilder= Jwts.builder().setId(id).setSubject(name)
                .setIssuedAt(new Date())//签发时间
                .signWith(SignatureAlgorithm.HS256,key);
        //setClaims 这里有个坑 设置claims会覆盖掉原先设置的claims(Id和Subject）
        if(map!=null)
            for(Map.Entry<String,Object> entry:map.entrySet())
            {
                jwtBuilder.claim(entry.getKey(),entry.getValue());
            }

        jwtBuilder.setExpiration(new Date(exp));

        return jwtBuilder.compact();
    }

    public String createJwt(String id){
        //设置失效时间
        long now=System.currentTimeMillis(); //当前毫秒
        long exp=now+ttl;

        JwtBuilder jwtBuilder= Jwts.builder().setId(id)
                .setIssuedAt(new Date())//签发时间
                .signWith(SignatureAlgorithm.HS256,key);
        jwtBuilder.setExpiration(new Date(exp));

        return jwtBuilder.compact();
    }

    public String createJwt(Map<String,Object> map){

        //设置失效时间
        long now=System.currentTimeMillis(); //当前毫秒
        long exp=now+ttl;

        System.out.println(key);
        JwtBuilder jwtBuilder= Jwts.builder()
                .setIssuedAt(new Date())//签发时间
                .signWith(SignatureAlgorithm.HS256,key);
        //setClaims 这里有个坑 设置claims会覆盖掉原先设置的claims(Id和Subject）
        if(map!=null)
            for(Map.Entry<String,Object> entry:map.entrySet())
            {
                jwtBuilder.claim(entry.getKey(),entry.getValue());
            }

        jwtBuilder.setExpiration(new Date(exp));

        return jwtBuilder.compact();
    }


    /*
     * 解析 获得数据
     */
    public  Claims jwtParse(String token)
    {
        Claims claims=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims;
    }



}
