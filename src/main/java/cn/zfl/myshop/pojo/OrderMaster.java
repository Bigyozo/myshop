package cn.zfl.myshop.pojo;

import cn.zfl.myshop.enums.OrderStatusEnum;
import cn.zfl.myshop.enums.PayStatusEnum;
import lombok.Data;

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: myshop
 * @description:
 * @author: zhangfl
 * @create: 2018-11-12 20:15
 **/
@Data
public class OrderMaster {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    /*买家微信openid*/
    private String buyerOpenid;
    /*订单总金额*/
    private BigDecimal orderAmount;
    /*订单状态,默认为0新下单*/
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /*支付状态，默认为0未支付*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    private Date createTime;
    private Date updateTime;
}
