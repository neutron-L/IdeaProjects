package cn.edu.bupt.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ikariyui on 2018/3/15.
 */
public class JwtToken {

    public static String SECRET = "iloveyou";


    public static String createToken(String key) throws UnsupportedEncodingException {
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE,30);
        Date expireDate = instance.getTime();

        HashMap<String, Object> map = new HashMap<>();
        map.put("alg","HS256");
        map.put("typ","JWT");

        String uid = JWT.create()
                .withHeader(map)
                .withClaim("uid", key)
                .withExpiresAt(expireDate)
                .withIssuedAt(date)
                .sign(Algorithm.HMAC256(SECRET));
        return uid;
    }

    public static Map<String, Claim> verifyToken(String token) throws UnsupportedEncodingException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT verify = verifier.verify(token);
        return verify.getClaims();
    }

    public static String verifyTokenUid(String token) throws UnsupportedEncodingException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT verify = verifier.verify(token);
        Map<String, Claim> claims = verify.getClaims();
        Claim uid = claims.get("uid");
        return uid.asString();

    }

}
