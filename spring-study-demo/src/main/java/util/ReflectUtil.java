package util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 反射工具类
 *
 * @author daihuaiyu
 * @create: 2021-03-17 16:09
 **/
@Slf4j
public class ReflectUtil {

    public static List<String[]> ObjectToArray(List<Object> data, Map<Integer, String> parameterMap) {
        List<String[]> dataList = new ArrayList<>();
        Map<String, Object> filedMap = new HashMap<>();
        String[] titleArray =  new String[parameterMap.size()];;
        for (int i = 0; i < parameterMap.size(); i++) {
            String parameterDefine = parameterMap.get(i);
            final String toUnderline = StringUtils.camelToUnderline(parameterDefine);
            titleArray[i] = toUnderline;
        }
        dataList.add(titleArray);
        //此处从数据库中查询出来的是对象
        data.stream().forEach(dataObj -> {
            //获取对象的Field信息
            final Field[] fields = dataObj.getClass().getDeclaredFields();
            String[] dataArray = new String[parameterMap.size()];
            for (Field field : fields) {
                String fieldName = field.getName();
                field.setAccessible(true);
                try {
                    Object o = field.get(dataObj);
                    field.setAccessible(false);
                    filedMap.put(fieldName, o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < parameterMap.size(); i++) {
                String parameterDefine = parameterMap.get(i);
                Object o = filedMap.get(parameterDefine);
                dataArray[i] = "";
                if (o != null) {
                    dataArray[i] = o.toString();
                }
            }
            dataList.add(dataArray);
        });
        dataList.stream().forEach(user-> System.out.println(user.length));
        return dataList;
    }

    public static Map<Integer,String>getFiledNameMap(Class o) {
        Map<Integer, String> dataMap = new HashMap<>();
        Field[] declaredFields = o.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            dataMap.put(i, declaredFields[i].getName());
        }
        log.info(dataMap.size()+"");
        return dataMap;
    }
}

