package com.broad.web.framework.bean;

import com.broad.web.framework.constant.CommonConstant;
import com.broad.web.framework.enums.TenantType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Date;

/**
 * JwtInfo 信息封裝類
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtInfo implements Serializable {

    private Long id;

    //登陆账号
    private String loginName;

    private TenantType tenantType;

    private String userCode;
    //显示名称
    private String nickName;
    /**
     * 商户所属的平台code
     */
    private String platCode;

    /**
     * 过期时间
     */
    private Date expires = DateTime.now().plusMinutes(2 * 60).toDate();

    private String tenantCode;

    private String accountCode;

    /**
     * 是否是主帐号
     */
    private Boolean primaryAccount;

    private String tenantName;


    public JwtInfo(Long id, String loginName,String nickName,
                   String tenantCode, TenantType tenantType,
                   String userCode, String platCode,
                   Boolean primaryAccount, String tenantName, Date expires) {
        this.id = id;
        this.loginName = loginName;
        this.nickName = nickName;
        this.tenantCode = tenantCode;
        this.tenantType = tenantType;
        this.userCode = userCode;
        this.platCode = platCode;
        this.primaryAccount = primaryAccount;
        this.tenantName = tenantName;
        this.expires = expires;
    }


    /**
     * 生成默认 info
     * @return
     */
    public static final JwtInfo defaultInfo() {
        final JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(0L);
        jwtInfo.setTenantCode(CommonConstant.JWT_TENANT_CODE);
        jwtInfo.setLoginName(CommonConstant.JWT_LOGIN_NAME);
        jwtInfo.setNickName(CommonConstant.JWT_NICK_NAME);
        jwtInfo.setUserCode(CommonConstant.JWT_USER_CODE);
        jwtInfo.setPlatCode(CommonConstant.JWT_PLAT_CODE);
        jwtInfo.setTenantName(CommonConstant.JWT_TENANT_NAME);
        jwtInfo.setTenantType(TenantType.PLATFORM);
        jwtInfo.setPrimaryAccount(false);
        return jwtInfo;
    }
}
