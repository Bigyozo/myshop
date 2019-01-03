package cn.zfl.myshop.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhangfl<br   />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/12/17 20:44<br/>
 */
@Data
public class ProductForm {
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productIcon;
    private Integer categoryType;
}
