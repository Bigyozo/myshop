package cn.zfl.myshop.enums;

import lombok.Getter;

/**
 * @author zhangfl<br   />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/11/20 21:03<br/>
 */
@Getter
public enum ResultEnum {
    SUCCESS(0,"成功"),

    PARAM_ERROR(1, "参数不正确"),

    CART_EMPTY(2, "购物车为空"),

    PRODUCT_NOT_EXIST(10, "商品不存在"),

    PRODUCT_STOCK_ERROR(11, "库存出错"),

    ORDER_NOT_EXIST(12, "订单不存在"),

    ORDERDETAIL_NOT_EXIST(13, "订单明细不存在"),

    ORDER_STATUS_ERROR(14, "订单状态不正确"),

    ORDER_PAY_STATUS_ERROR(15, "订单支付状态不正确"),

    ORDER_SQL_ERROR(16, "订单数据库修改失败"),

    ORDERDETAIL_SQL_ERROR(17, "订单详情数据库修改失败"),

    ORDER_OWNER_ERROR(18,"订单与当前用户不匹配"),

    WECHAT_MP_ERROR(19,"微信公众账号错误"),

    PRODUCT_STATUS_ERROR(20,"商品状态修改错误"),

    WXPAY_NOTIFY_ERROR(21,"微信支付异步通知校验失败"),

    LOGIN_FAIL(22,"登陆失败"),

    ORDER_CANCEL_SUCCESS(101,"订单取消成功"),

    ORDER_FINISH_SUCCESS(102,"订单完成成功"),

    LOGOUT_SUCCESS(103,"登出成功");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
