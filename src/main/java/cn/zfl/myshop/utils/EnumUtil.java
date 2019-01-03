package cn.zfl.myshop.utils;

import cn.zfl.myshop.enums.CodeEnum;

/**
 * @author zhangfl<br   />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/12/7 17:16<br/>
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for(T each:enumClass.getEnumConstants()){
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
