package com.broad.web.framework.utils;

import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *反射工具類
 */
public class FieldUtils {

    /**
     * 获取类以及父类的属性类型
     * @param clazz
     * @return
     */
    public static ArrayList<Class<?>> getAllFieldClass(Class<?> clazz){
        ArrayList<Class<?>> classs = Lists.newArrayList();
        List<Class<?>> tempClasss = null;
        while (!clazz.equals(Object.class)){
            tempClasss = Arrays.asList(clazz.getDeclaredFields()).stream()
                    .map(field -> field.getType()).collect(Collectors.toList());
            classs.addAll(tempClasss);
            clazz = clazz.getSuperclass();
        }
        return classs;
    }


    public static boolean setFieldValue(Object obj, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        if (obj == null)
            return false;
        if (fieldName == null || fieldName.trim().equals(""))
            return false;
        Field field = null;
        Class<?> clazz = obj.getClass();
        while (clazz != null) {
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
            if (field != null) {
                break;
            }
        }

        if (field == null) {
            throw new NoSuchFieldException();
        }

        field.setAccessible(true);
        field.set(obj, value);
        return true;
    }

    /**
     * 获取类里面指定的属性类型，检测到list类型属性会自动获取其泛型
     * @param clazz
     * @param name
     * @return
     */
    public static Class<?> getAllFieldClass(Class<?> clazz,String name){
        Field field = null;
        while (!clazz.equals(Object.class)){
            try {
                field = clazz.getDeclaredField(name);
                break;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return field.getType().equals(List.class)?getListGenericClass(field):field.getType();
    }

    /**
     * 获取类里面指定的属性类型，检测到list类型属性会自动获取其泛型，并且可获取list泛型里面的指定属性
     * @param clazz
     * @param name
     * @return
     */
    public static Class<?> getFieldClass(Class<?> clazz,String name){
        List<String> nameList = Arrays.asList(name.split("\\."));
        for (String tempName:nameList) {
            clazz = getAllFieldClass(clazz,tempName);
        }
        return clazz;
    }

    /**
     * 获取list的泛型类所有属性
     * @param field
     * @return
     */
    public static Class<?> getListGenericClass(Field field){
        ParameterizedType listGenericType  = (ParameterizedType)field.getGenericType();
        Type[] listActualTypeArguments = listGenericType.getActualTypeArguments();
        return  (Class<?>)listActualTypeArguments[0];
    }

    /**
     * 获取list的泛型类指定属性
     * @param clazz
     * @param name
     * @return
     */
    public static Class<?> getListGenericClass(Class<?> clazz,String name) {
        Field listField = null;
        try {
            listField = clazz.getDeclaredField(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ParameterizedType listGenericType = (ParameterizedType) listField.getGenericType();
        Type[] listActualTypeArguments = listGenericType.getActualTypeArguments();
        return (Class<?>) listActualTypeArguments[0];
    }
}
