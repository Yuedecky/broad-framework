package com.broad.web.framework.response;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.broad.web.framework.constant.ReturnCode;
import org.slf4j.Logger;

/**
 * @param <T>
 * @author broad
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WebResponse<T> {
    public static final String DEF_ERROR_MESSAGE = "系统繁忙，请稍候再试";
    public static final String HYSTRIX_ERROR_MESSAGE = "请求超时，请稍候再试";

    public static final String TIMEOUT_CODE = "403";

    public WebResponse() {
        this.status = "200";
    }

    public WebResponse(T data) {
        this.data = data;
    }

    public WebResponse(T data, Boolean success) {
        this.data = data;
        this.success = success;
    }

    public WebResponse(String status, String msg) {
        this.msg = msg;
        this.status = status;
    }

    public WebResponse(String status, T data, String msg) {
        this.msg = msg;
        this.status = status;
        this.data = data;
    }

    public WebResponse(String[] errorConstant) {
        this.status = errorConstant[0];
        this.msg = errorConstant[1];
    }

    public static <T> WebResponse<T> success(T data) {
        return result("200", data, "success");
    }

    public static <T> WebResponse<T> error(T data) {
        return result(ReturnCode.ERROR, data, DEF_ERROR_MESSAGE);
    }

    /**
     * 请求失败消息
     *
     * @param msg
     * @return
     */
    public static <E> WebResponse<E> fail(String code, String msg) {
        return result(code, null, StrUtil.isEmpty(code) ? DEF_ERROR_MESSAGE : msg);
    }

    /**
     * 超时的响应
     * @param <E>
     * @return
     */
    public static <E> WebResponse<E> timeout() {
        return fail(TIMEOUT_CODE, HYSTRIX_ERROR_MESSAGE);
    }

    public static <T> WebResponse<T> error(String msg) {
        return result(ReturnCode.ERROR, null, msg);
    }

    public WebResponse(String msg) {
        this.msg = msg;
    }

    @ApiModelProperty(value = "状态码")
    private String status = "200";

    @ApiModelProperty(value = "数据")
    private T data;

    @ApiModelProperty(value = "提示信息")
    private String msg = "success";

    @ApiModelProperty(value = "异常信息")
    private String[] errorConstant = new String[]{};

    @ApiModelProperty(value = "结果是否成功")
    @JsonIgnore(value = true)
    private Boolean success = true;

    public boolean isOk() {
        return "200".equals(this.status);
    }

    public boolean isSuccess() {
        return isOk();
    }

    public boolean isError() {
        return !isSuccess();
    }

    public void error(String code, String message) {
        this.status = code;
        this.msg = message;
        this.success = false;
    }

    public void error(WebResponse<?> response) {
        this.error(response.getStatus(), response.getMsg());
    }

    public static <T> WebResponse<T> buildSuccess() {
        return buildSuccess(null);
    }

    public static <T> WebResponse<T> buildSuccess(T data) {
        return success(data);
    }

    /**
     * @param code
     * @param data
     * @param msg
     * @param <E>
     * @return
     */
    public static <E> WebResponse<E> result(String code, E data, String msg) {
        return new WebResponse<>(code, data, msg);
    }



    public static <T> WebResponse<T> buildError(WebResponse<?> result) {
        WebResponse<T> response = new WebResponse<T>();
        response.error(result);
        return response;
    }


    public static <T> WebResponse<T> buildError(String code, String message) {
        WebResponse<T> response = new WebResponse<T>();
        response.error(code, message);
        return response;
    }
}