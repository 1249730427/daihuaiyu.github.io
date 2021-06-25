package com.daihuaiyu.springframework.beans.factory;

/**
 * 属性信息
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/25 15:21
 * @Description:
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }
    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

}
