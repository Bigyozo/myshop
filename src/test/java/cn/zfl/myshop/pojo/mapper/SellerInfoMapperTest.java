package cn.zfl.myshop.pojo.mapper;

import cn.zfl.myshop.pojo.SellerInfo;
import cn.zfl.myshop.utils.KeyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author zhangfl<br />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/12/24 22:13<br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoMapperTest {
    @Autowired
    private SellerInfoMapper sellerInfoMapper;
    @Test
    public void selectByOpenid() {
        SellerInfo sellerInfo=sellerInfoMapper.selectByOpenid("abc");
        System.out.println(sellerInfo);
    }

    @Test
    public void insertByObject() {
        SellerInfo sellerInfo=new SellerInfo();
        sellerInfo.setSellerId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");
        int result=sellerInfoMapper.insertByObject(sellerInfo);

    }
}