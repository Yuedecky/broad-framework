package com.broad.web.framework.exception;

import lombok.extern.slf4j.Slf4j;
import com.broad.web.framework.constant.ReturnCode;
import com.broad.web.framework.response.WebResponse;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

/**
 * todo: 解决feign服务间调用 底层异常被 validator抛出 上层服务无感知的问题
 *
 * @author broad
 */
@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 匹配error并处理
     */
    private final static String ERROR_PATH = "/error";


    public WebResponse<?> methodArgumentErrorHandler(MethodArgumentNotValidException ex) {
        // 同样是获取BindingResult对象，然后获取其中的错误信息
        // 如果前面开启了fail_fast，事实上这里只会有一个信息
        //如果没有，则可能又多个
        log.warn("进入methodArgumentErrorHandler,ex:{}", ex);
        List<String> errorInformation = ex.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
        log.error("ex method argument error message:{}", errorInformation.toString());
        return new WebResponse<>("400", errorInformation.toString());
    }


    public WebResponse validationErrorHandler(ConstraintViolationException ex) {
        log.warn("进入global exception handler's validationErrorHandler:{}", ex);
        List<String> errorInformation = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        log.error("ex validation error message:{}", errorInformation.toString());
        return new WebResponse("400", errorInformation.toString());
    }

    /**
     * <p>
     * 方法说明:本方法处理Exception抛出异常的情况。
     * 注意GET/POST用错了，SpringMVC的处理也是在异常中展示的，因此需要特殊处理一下
     *
     * @param e        传递的最顶级的异常
     * @param response response对象
     * @return 返回响应
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WebResponse handle(HttpServletRequest req, Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.OK.value());
        log.error("request url:{},GlobalExceptionHandler中捕获的异常如下:{},response:{}", req.getRequestURL(), e, response);
        if (e instanceof HttpRequestMethodNotSupportedException) {
            response.setStatus(METHOD_NOT_ALLOWED.value());
            return handle(response);
        } else if (e instanceof HttpMessageNotReadableException) {
            response.setStatus(BAD_REQUEST.value());
            return handle(response);
        } else if (e instanceof BaseException) {
            return new WebResponse(((BaseException) e).getStatus(), e.getMessage());
        } else if (e instanceof HttpMediaTypeNotSupportedException) {
            response.setStatus(UNSUPPORTED_MEDIA_TYPE.value());
            return handle(response);
        } else if (e instanceof NullPointerException) {
            return new WebResponse(ReturnCode.NULL_POINT_EXCEPTION);
        } else if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            return new WebResponse(ReturnCode.METHOD_NOT_FOUND);
        } else if (e instanceof DuplicateKeyException) {
            return new WebResponse(ReturnCode.DUP_RECORD);
        } else if (e instanceof ConstraintViolationException) {
            return this.validationErrorHandler((ConstraintViolationException) e);
        } else if (e instanceof MethodArgumentNotValidException) {
            return this.methodArgumentErrorHandler((MethodArgumentNotValidException) e);
        } else if (e instanceof IllegalArgumentException) {
            return new WebResponse(ReturnCode.NO, e.getMessage());
        }
        return new WebResponse(ReturnCode.SYSTEM_ERROR);
    }

    /**
     * <p>
     * 方法说明:本方法处理除了500之外的所有异常,
     * 注意405错误是被ExceptionHandler转发调用的
     */
    public WebResponse handle(HttpServletResponse response) {
        WebResponse baseResponse = null;
        log.error("GlobalExceptionHandler中捕获除了500之外的所有状态码: {}", response.getStatus());
        //根据不同status返回不同消息
        switch (HttpStatus.valueOf(response.getStatus())) {
            //请检查POST/GET方式
            case METHOD_NOT_ALLOWED:
                //请求头错误
            case UNSUPPORTED_MEDIA_TYPE:
                baseResponse = new WebResponse(ReturnCode.METHOD_ERROR);
                break;
            //请检查JSON格式是否规范
            case BAD_REQUEST:
                baseResponse = new WebResponse(ReturnCode.JSON_DATA_ERROR);
                break;
            case NOT_FOUND:
                baseResponse = new WebResponse(ReturnCode.METHOD_NOT_FOUND);
                break;
            default:
                baseResponse = new WebResponse(ReturnCode.SYSTEM_ERROR);
                break;
        }
        //欺骗性质的各种问题均转化为状态置为200
        response.setStatus(HttpStatus.OK.value());
        return baseResponse;
    }


}
