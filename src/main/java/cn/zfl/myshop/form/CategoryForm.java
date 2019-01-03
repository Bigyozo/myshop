package cn.zfl.myshop.form;

import lombok.Data;


/**
 * @author zhangfl<br />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/12/20 21:43<br/>
 */
@Data
public class CategoryForm {

    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;
}
