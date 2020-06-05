package com.broad.web.framework.sms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午3:14-2020/5/16
 * @Last modified by:
 */
@Data
public class SmsTemplate {

    /**
     * 供应商类型
     * #ProviderType{ALI:OK,阿里云短信;TENCENT:0,腾讯云短信;BAIDU:1000,百度云短信}
     */
    @ApiModelProperty(value = "供应商类型")
    @NotNull(message = "供应商类型不能为空")
    private ProviderType providerType;

    /**
     * 应用ID
     */
    @ApiModelProperty(value = "应用ID")
    @NotEmpty(message = "应用ID不能为空")
    @Length(max = 255, message = "应用ID长度不能超过255")
    private String appId;

    /**
     * 应用密码
     */
    @ApiModelProperty(value = "应用密码")
    @NotEmpty(message = "应用密码不能为空")
    @Length(max = 255, message = "应用密码长度不能超过255")
    private String appSecret;

    /**
     * SMS服务域名
     * 百度、其他厂商会用
     */
    @ApiModelProperty(value = "SMS服务域名")
    @Length(max = 255, message = "SMS服务域名长度不能超过255")
    private String url;

    /**
     * 模板编码
     * 用于api发送
     */
    @ApiModelProperty(value = "模板编码")
    @Length(max = 20, message = "模板编码长度不能超过20")
    private String customCode;

    /**
     * 模板名称
     */
    @ApiModelProperty(value = "模板名称")
    @Length(max = 255, message = "模板名称长度不能超过255")
    private String name;

    /**
     * 模板内容
     */
    @ApiModelProperty(value = "模板内容")
    @NotEmpty(message = "模板内容不能为空")
    @Length(max = 255, message = "模板内容长度不能超过255")
    private String content;

    /**
     * 模板参数
     */
    @ApiModelProperty(value = "模板参数")
    @NotEmpty(message = "模板参数不能为空")
    @Length(max = 255, message = "模板参数长度不能超过255")
    private String templateParams;

    /**
     * 模板CODE
     */
    @ApiModelProperty(value = "模板CODE")
    @NotEmpty(message = "模板CODE不能为空")
    @Length(max = 50, message = "模板CODE长度不能超过50")
    private String templateCode;

    /**
     * 模板签名
     */
    @ApiModelProperty(value = "模板签名名称")
    @Length(max = 100, message = "模板签名名称长度不能超过100")
    private String signName;

    /**
     * 模板描述
     */
    @ApiModelProperty(value = "模板描述")
    @Length(max = 255, message = "模板描述长度不能超过255")
    private String templateDescribe;


}
