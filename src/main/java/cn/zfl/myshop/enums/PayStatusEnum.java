package cn.zfl.myshop.enums;

import lombok.Getter;

/**
 * @program: myshop
 * @description: 支付状态
 * @author: zhangfl
 * @create: 2018-11-12 20:22
 **/
@Getter
public enum PayStatusEnum implements CodeEnum{
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");

    private Integer code;

    private String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
