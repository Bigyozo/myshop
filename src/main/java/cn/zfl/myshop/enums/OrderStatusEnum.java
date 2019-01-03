package cn.zfl.myshop.enums;

import lombok.Getter;

/**
 * @program: myshop
 * @description: 订单状态
 * @author: zhangfl
 * @create: 2018-11-12 22:12
 **/
@Getter
public enum OrderStatusEnum implements CodeEnum{
    NEW(0, "新订单"),
    FINISH(1, "完结"),
    CANCEL(2, "已取消");

    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
