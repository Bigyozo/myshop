package cn.zfl.myshop.ViewObj;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: myshop
 * @description: 商品详情
 * @author: zhangfl
 * @create: 2018-11-11 15:32
 **/
@Data
public class ProductInfoVO implements Serializable {
    private static final long serialVersionUID = 8382849008236265814L;
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
