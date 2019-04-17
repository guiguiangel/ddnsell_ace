package com.ddn.ddnsell.utils;

import com.ddn.ddnsell.enums.CodeEnum;

/**
 * @author qincx
 * @date 2019/3/26
 * @description
 */
public class EnumsUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
