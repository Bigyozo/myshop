package cn.zfl.myshop.service.impl;

import cn.zfl.myshop.aspect.Slave;
import cn.zfl.myshop.pojo.SellerInfo;
import cn.zfl.myshop.pojo.mapper.SellerInfoMapper;
import cn.zfl.myshop.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangfl<br />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/12/25 18:13<br/>
 */
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoMapper sellerInfoMapper;

    @Slave
    @Override
    public SellerInfo getByOpenid(String openid) {
        return sellerInfoMapper.selectByOpenid(openid);
    }
}


