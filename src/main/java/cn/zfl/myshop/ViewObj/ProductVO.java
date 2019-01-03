package cn.zfl.myshop.ViewObj;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: myshop
 * @description: 商品含类目
 * @author: zhangfl
 * @create: 2018-11-11 15:26
 **/
@Data
public class ProductVO implements Serializable {
    private static final long serialVersionUID = 1705543599752782489L;
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
