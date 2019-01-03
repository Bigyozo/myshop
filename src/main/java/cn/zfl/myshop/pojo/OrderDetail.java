package cn.zfl.myshop.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: myshop
 * @description:
 * @author: zhangfl
 * @create: 2018-11-12 20:25
 **/
@Data
public class OrderDetail {
    private String detailId;
    private String orderId;
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productQuantity;
    private String productIcon;
}
