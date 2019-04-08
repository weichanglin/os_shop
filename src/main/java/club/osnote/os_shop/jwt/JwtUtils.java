package club.osnote.os_shop.jwt;

import club.osnote.os_shop.dao.UserDao;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    public static String SECRET = "OSNoteABCD";


    public static String createToken(Long uid) {
        //签发时间
        Date istDate = new Date();

        //设置过期时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 5);
        Date expiresDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");


        String token = JWT.create()
                .withHeader(map)
                .withClaim("userInfo", uid)
                .withExpiresAt(expiresDate)
                .withIssuedAt(istDate)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }


    public static Long verifyToken(String token) throws Exception{
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
            return jwt.getClaims().get("userInfo").asLong();
        } catch (Exception e) {
            throw new RuntimeException("登录失效");
        }
    }

}