package com.broad.web.framework.utils;

import lombok.extern.slf4j.Slf4j;
import com.broad.web.framework.annotation.SysLog;
import com.broad.web.framework.annotation.TaskPoint;
import org.aspectj.lang.JoinPoint;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

/**
 * @author broad
 * @date 20200217
 **/
@Slf4j
public class AnnotationUtils {

    private AnnotationUtils(){

    }


    /***
     * 获取操作信息
     * @param point
     * @return
     */
    public static String getTaskPointAnnotationValue(JoinPoint point) {
        try {
            // 获取连接点签名的方法名
            String methodName = point.getSignature().getName();
            //获取连接点参数
            Object[] args = point.getArgs();
            // 获取连接点目标类名
            String description = null;
            for (Method method : getClassMethods(point)) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == args.length) {
                        description = method.getAnnotation(TaskPoint.class).taskName();
                        break;
                    }
                }
            }
            return description;
        } catch (Exception e) {
            log.error("获取注解相关信息发生异常：{}", e);
            return null;
        }
    }

    public static String getSysLogAnnotationValue(JoinPoint point) {
        try {
            // 获取连接点签名的方法名
            String methodName = point.getSignature().getName();
            //获取连接点参数
            Object[] args = point.getArgs();
            // 获取连接点目标类名
            String description = null;
            for (Method method : getClassMethods(point)) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == args.length) {
                        description = method.getAnnotation(SysLog.class).value();
                        break;
                    }
                }
            }
            return description;
        } catch (Exception e) {
            log.error("获取注解相关信息发生异常：{}", e);
            return null;
        }
    }


    public static Method[] getClassMethods(JoinPoint point) throws ClassNotFoundException{
        String targetName = point.getTarget().getClass().getName();
        //根据连接点类的名字获取指定类
        Class targetClass = Class.forName(targetName);
        //获取类里面的方法
        Method[] methods = targetClass.getMethods();
        return methods;
    }


    /**
     * 获取堆栈信息
     *
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }



}
