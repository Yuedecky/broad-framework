package com.broad.web.framework.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.broad.web.framework.bean.JwtInfo;
import com.broad.web.framework.constant.CommonConstant;
import com.broad.web.framework.enums.TenantType;
import org.joda.time.DateTime;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

/**
 * @author broad
 * Jwt工具class
 */
public class JwtUtils {

    private JwtUtils() {

    }

    public static String generateToken(JwtInfo jwtInfo, String priKeyPath) throws Exception {
        System.err.println("当前token信息========>>>>>>"+jwtInfo.toString());
        return Jwts.builder()
                .claim(CommonConstant.JWT_LOGIN_NAME, jwtInfo.getLoginName())
                .claim(CommonConstant.JWT_NICK_NAME, jwtInfo.getNickName())
                .claim(CommonConstant.JWT_USER_ID, jwtInfo.getId())
                .claim(CommonConstant.JWT_TENANT_CODE, jwtInfo.getTenantCode())
                .claim(CommonConstant.JWT_USER_CODE, jwtInfo.getUserCode())
                .claim(CommonConstant.JWT_TENANT_TYPE, jwtInfo.getTenantType())
                .claim(CommonConstant.JWT_PLAT_CODE, jwtInfo.getPlatCode())
                .claim(CommonConstant.JWT_PRIMARY_ACCOUNT, jwtInfo.getPrimaryAccount())
                .claim(CommonConstant.JWT_TENANT_NAME, jwtInfo.getTenantName()).claim(CommonConstant.JWT_EXPIRES_DATE, jwtInfo.getExpires())
                .setIssuedAt(DateTime.now().toDate())
                .setSubject(CommonConstant.JWT_SUB)
                .setExpiration(jwtInfo.getExpires())
                .signWith(SignatureAlgorithm.RS256, RsaKeyHelper.builder().getPrivateKey(priKeyPath))
                .compact();
    }


    /**
     * @param token
     * @param pubKeyPath
     * @return
     * @throws Exception
     */
    public static JwtInfo getInfoFromToken(String token, String pubKeyPath) throws
            NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        Jws<Claims> claimsJws = parserToken(token, pubKeyPath);
        Claims body = claimsJws.getBody();
        Long userId = StringUtils.getLongValue(body.get(CommonConstant.JWT_USER_ID));
        String loginName = StringUtils.getObjectValue(body.get(CommonConstant.JWT_LOGIN_NAME));
        String nickName = StringUtils.getObjectValue(body.get(CommonConstant.JWT_NICK_NAME));
        String tenantCode = StringUtils.getObjectValue(body.get(CommonConstant.JWT_TENANT_CODE));
        String platCode = StringUtils.getObjectValue(body.get(CommonConstant.JWT_PLAT_CODE));
        String userCode = StringUtils.getObjectValue(body.get(CommonConstant.JWT_USER_CODE));
        TenantType tenantType = TenantType.valueOf(StringUtils.getObjectValue(body.get(CommonConstant.JWT_TENANT_TYPE)));
        Boolean primaryAccount = StringUtils.getBooleanValue(body.get(CommonConstant.JWT_PRIMARY_ACCOUNT));
        Date expireDate = StringUtils.getDateValue(body.get(CommonConstant.JWT_EXPIRES_DATE));
        String tenantName = StringUtils.getObjectValue(body.get(CommonConstant.JWT_TENANT_NAME));
        return new JwtInfo(userId, loginName,nickName,
                tenantCode, tenantType, userCode, platCode,
                primaryAccount, tenantName, expireDate);
    }

    /**
     * @Description: 解析token
     * @Date: 17:12 2018/1/29
     */
    public static Jws<Claims> parserToken(String token, String pubKeyPath) throws IOException,
            NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.parser().setSigningKey(RsaKeyHelper.builder().getPublicKey(pubKeyPath)).parseClaimsJws(token);
    }


}
