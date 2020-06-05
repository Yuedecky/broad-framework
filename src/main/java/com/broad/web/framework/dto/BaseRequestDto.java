package com.broad.web.framework.dto;

import com.broad.web.framework.enums.TenantType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.broad.web.framework.utils.LoginUtils;

import java.io.Serializable;

/**
 * @author broad
 * @date 20191215
 **/
@Data
@ApiModel("基础操作dto")
public class BaseRequestDto implements Serializable {

    @ApiModelProperty(value = "当前操作人用户code",hidden = true)
    private String curUserCode;

    @ApiModelProperty(value = "当前商户code",hidden = true)
    private String curTenantCode;


    @ApiModelProperty(value = "当前用户名称",hidden = true)
    private String curUserName;

    @ApiModelProperty(value = "当前商户名称",hidden = true)
    private String curTenantName;

    @ApiModelProperty(value = "当前商户类型",hidden = true)
    private TenantType curTenantType;

    @ApiModelProperty(value = "是否是主帐号",hidden = true)
    private Boolean isPrimary;

    public static BaseRequestDto fromRequest(){
        final BaseRequestDto requestDto = new BaseRequestDto();
        requestDto.setCurTenantCode(LoginUtils.currentTenantCode());
        requestDto.setCurTenantName(LoginUtils.currentTenantName());
        requestDto.setCurUserCode(LoginUtils.currentUserCode());
        requestDto.setCurUserName(LoginUtils.currentUserName());
        requestDto.setCurTenantType(LoginUtils.currentTenantType());
        requestDto.setIsPrimary(LoginUtils.isPrimaryAccount());
        return requestDto;
    }
}
