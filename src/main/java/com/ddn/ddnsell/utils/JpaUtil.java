package com.ddn.ddnsell.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qincx
 * @date 2019/4/15
 * @description
 */
public class JpaUtil {
    //转换实体类

    /**
     *  实体类 里面 构造函数参数顺序必须要 和 sql  查询出来的字段 顺序一致
     *  注意: 构造函数 里面字段要用 SQL 对应查出来的 数据类型  java.math.BigInteger 时间java.sql.Timestamp
     * @param list
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> createEntityWithConstructor(List<Object[]> list, Class<T> clazz) throws Exception {
        List<T> returnList = new ArrayList<T>();
        Object[] co = list.get(0);
        Class[] c2 = new Class[co.length];

        //确定构造方法
        for (int i = 0; i < co.length; i++) {
            c2[i] = co[i].getClass();
        }

        for (Object[] o : list) {
            Constructor<T> constructor = clazz.getConstructor(c2);
            returnList.add(constructor.newInstance(o));
        }

        return returnList;
    }

    /**
     *
     * @param list
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> createEntityWithField(List<Object[]> list, Class<T> clazz){
        List<T> returnList = new ArrayList<T>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Object obj:list
        ) {
            Object[] aojb = (Object[]) obj;
            Map<String, Object> mapObj = new HashMap<String, Object>();
            if (aojb.length >= declaredFields.length){
               for (int i = 0; i < aojb.length; i++){
                   mapObj.put(declaredFields[i].getName(), aojb[i]);
               }
                try {
                    T tobject = clazz.newInstance();
                    org.apache.commons.beanutils.BeanUtils.populate(tobject, mapObj);
                    returnList.add(tobject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return returnList;
    }
}
