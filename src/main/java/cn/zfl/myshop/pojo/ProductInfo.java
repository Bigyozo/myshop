package cn.zfl.myshop.pojo;

import cn.zfl.myshop.enums.PayStatusEnum;
import cn.zfl.myshop.enums.ProductStatusEnum;
import cn.zfl.myshop.utils.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: myshop
 * @description:
 * @author: zhangfl
 * @create: 2018-11-10
 **/
@Data
public class ProductInfo {
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    /*状态，0正常1下架*/
    private Integer productStatus= ProductStatusEnum.UP.getCode();
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
