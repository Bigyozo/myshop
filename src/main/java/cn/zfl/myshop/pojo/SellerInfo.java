package cn.zfl.myshop.pojo;

import lombok.Data;

/**
 * @author zhangfl<br />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/12/24 22:03<br/>
 */
@Data
public class SellerInfo {
    private String sellerId;
    private String username;
    private String password;
    private String openid;
}
