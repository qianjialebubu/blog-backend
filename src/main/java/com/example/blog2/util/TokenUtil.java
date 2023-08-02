package com.example.blog2.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.blog2.po.User;
import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;

public class TokenUtil {

//    private static final long EXPIRE_TIME= 60*1000; // 过期时间设置为1分钟
    private static final long EXPIRE_TIME= 10*60*60*1000; // 过期时间设置为10小时
    private static final String TOKEN_SECRET="qjl20210714";  //密钥盐

    /**
     * 签名生成
     * @param user
     * @return
     */
    public static String sign(User user){
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("userId", user.getId().toString())
                    .withClaim("userType", user.getType())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 签名验证
     * @param token
     * @return
     */
    public static boolean verify(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("userId: " + jwt.getClaim("userId").asString());
            System.out.println("userType: " + jwt.getClaim("userType").asString());
            System.out.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * 管理员认证
     * @param token
     * @return
     */
    public static boolean adminVerify(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            if ( "1".equals(jwt.getClaim("userType").asString())){
                System.out.println("管理员认证通过");
                return true;
            }
            return false;
        } catch (Exception e){
            return false;
        }
    }

}