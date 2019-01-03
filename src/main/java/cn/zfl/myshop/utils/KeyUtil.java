package cn.zfl.myshop.utils;

import java.util.Random;

/**
 * @author zhangfl<br   />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/11/20 21:40<br/>
 */
public class KeyUtil {
    /**
     * 生成唯一主键
     * 格式：时间+随机数
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 1000000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
