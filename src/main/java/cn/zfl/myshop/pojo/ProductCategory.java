package cn.zfl.myshop.pojo;


import lombok.Data;

import java.util.Date;
/**
 * @program: myshop
 * @description:
 * @author: zhangfl
 * @create: 2018-11-10
 **/
@Data
public class ProductCategory {

    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

}
