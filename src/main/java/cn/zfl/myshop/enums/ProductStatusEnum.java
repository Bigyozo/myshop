package cn.zfl.myshop.enums;

import lombok.Getter;

/**
 * @program: myshop
 * @description: 商品状态
 * @author: zhangfl
 * @create: 2018-11-08 22:32
 **/
@Getter
public enum ProductStatusEnum implements CodeEnum{
    UP(0,"在架"),
    DOWN(1,"下架")
    ;

    private Integer code;
    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
