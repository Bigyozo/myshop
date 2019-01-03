package cn.zfl.myshop.dto;


import cn.zfl.myshop.enums.OrderStatusEnum;
import cn.zfl.myshop.enums.PayStatusEnum;
import cn.zfl.myshop.pojo.OrderDetail;
import cn.zfl.myshop.utils.EnumUtil;
import cn.zfl.myshop.utils.serializer.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhangfl<br       />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/11/20 20:41<br/>
 */
@Data
public class OrderDTO {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    /*买家微信openid*/
    private String buyerOpenid;
    /*订单总金额*/
    private BigDecimal orderAmount;
    /*订单状态,默认为0新下单*/
    private Integer orderStatus;
    /*支付状态，默认为0未支付*/
    private Integer payStatus;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
