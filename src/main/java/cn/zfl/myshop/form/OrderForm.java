package cn.zfl.myshop.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author zhangfl<br   />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/11/25 15:58<br/>
 */
@Data
public class OrderForm {
    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "手机号必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    //@NotEmpty(message = "openid必填")
    private String openid="oTgZpwSvK3N4-TrrbQnb4fB3wj5E";

    @NotEmpty(message = "购物车不能为空")
    private String items;
}
