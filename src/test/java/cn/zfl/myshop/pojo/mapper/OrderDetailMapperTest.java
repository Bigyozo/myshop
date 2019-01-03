package cn.zfl.myshop.pojo.mapper;

import cn.zfl.myshop.pojo.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author zhangfl<br />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/11/17 22:53<br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailMapperTest {
    @Autowired
    OrderDetailMapper orderDetailMapper;
    @Test
    public void selectByOrderId() {
    List<OrderDetail> list=orderDetailMapper.selectByOrderId("order1");
    for (OrderDetail orderDetail:list)
        System.out.println(orderDetail);
    }

    @Test
    public void insertByObject() {
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("de1");
        orderDetail.setOrderId("order1");
        orderDetail.setProductIcon("qwqqqq");
        orderDetail.setProductId("2");
        orderDetail.setProductName("炒饭");
        orderDetail.setProductPrice(new BigDecimal(3.5));
        orderDetail.setProductQuantity(3);
        int result=orderDetailMapper.insertByObject(orderDetail);
        Assert.assertEquals(1,result);
    }
}