package com.daihuaiyu.springframework.beans.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * 传递属性信息类
 *
 * @Author: daihuaiyu
 * @Date: 2021/6/25 15:22
 * @Description:
 */
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    /**
     * 向集合中添加属性信息
     * @param propertyValue
     */
    public void addPropertyValue(PropertyValue propertyValue){
        this.propertyValueList.add(propertyValue);
    }

    public PropertyValue [] getPropertyValues(){
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName){
        for (PropertyValue propertyValue:propertyValueList){
            if(propertyName.equals(propertyValue.getName())){
                return propertyValue;
            }
        }
        return null;
    }

}
