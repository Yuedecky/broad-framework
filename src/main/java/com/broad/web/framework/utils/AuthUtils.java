package com.broad.web.framework.utils;

import com.broad.web.framework.exception.AuthException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import com.broad.web.framework.bean.AuthProperties;
import com.broad.web.framework.bean.JwtInfo;
import com.broad.web.framework.constant.CommonConstant;
import com.broad.web.framework.constant.ReturnCode;
import com.broad.web.framework.context.CommonContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author broad
 * 生成token 工具类
 */
@Component
public class AuthUtils {

    @Autowired
    private AuthProperties authProperties;

    public JwtInfo getJwtFromToken(String token) {
        try {
            return JwtUtils.getInfoFromToken(token, authProperties.getPubKey().getPath());
        } catch (ExpiredJwtException ex) {
            throw new AuthException(ReturnCode.USER_TOKEN_EXPIRED);
        } catch (SignatureException ex) {
            throw new AuthException(ReturnCode.USER_TOKEN_ERROR);
        } catch (IllegalArgumentException ex) {
            throw new AuthException(ReturnCode.USER_TOKEN_NULL);
        } catch (Exception e) {
            throw new AuthException(ReturnCode.USER_TOKEN_ERROR);
        }
    }


    /**
     * @Description: 生成token
     * @Date: 11:26 2018/1/31
     */
    public String generateToken(JwtInfo jwtInfo) {
        try {
            return JwtUtils.generateToken(jwtInfo, authProperties.getPriKey().getPath());
        } catch (Exception e) {
            throw new AuthException(ReturnCode.GENERATE_TOKEN_ERROR);
        }
    }


    public String generateDefault(JwtInfo jwtInfo) {
        String jws = generateToken(jwtInfo);
        CommonContextHolder.set(CommonConstant.CONTEXT_DEFAULT_TOKEN, jwtInfo);
        return jws;
    }
}
