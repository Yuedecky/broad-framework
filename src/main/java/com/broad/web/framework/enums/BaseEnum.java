package com.broad.web.framework.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.broad.web.framework.tool.MapHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;
import java.util.Map;

/**
 * @author: broad
 * @email: yuezhiyong916@gmail.com
 * @Date: 下午2:59-2020/5/16
 * @Last modified by:
 */
public interface BaseEnum extends IEnum<String> {

    /**
     * 将制定的枚举集合转成 map
     * key -> name
     * value -> desc
     *
     * @param list
     * @return
     */
    static Map<String, String> getMap(BaseEnum[] list) {
        return MapHelper.uniqueIndex(Arrays.asList(list), BaseEnum::getCode, BaseEnum::getDesc);
    }


    /**
     * 编码重写
     *
     * @return
     */
    default String getCode() {
        return toString();
    }

    /**
     * 描述
     *
     * @return
     */
    String getDesc();

    /**
     * 枚举值
     *
     * @return
     */
    @Override
    @JsonIgnore
    default String getValue() {
        return getCode();
    }
}
