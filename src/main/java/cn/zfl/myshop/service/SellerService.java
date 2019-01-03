package cn.zfl.myshop.service;

import cn.zfl.myshop.pojo.SellerInfo;

/**
 * @author zhangfl<br />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/12/25 18:13<br/>
 */
public interface SellerService {
    SellerInfo getByOpenid(String openid);
}
