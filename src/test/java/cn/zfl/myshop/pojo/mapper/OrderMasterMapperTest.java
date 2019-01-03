package cn.zfl.myshop.pojo.mapper;

import cn.zfl.myshop.pojo.OrderMaster;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterMapperTest {
    @Autowired
    OrderMasterMapper orderMasterMapper;

    @Test
    public void insert(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("order2");
        orderMaster.setBuyerName("buyer23");
        orderMaster.setBuyerPhone("1232313123");
        orderMaster.setBuyerOpenid("12345");
        orderMaster.setBuyerAddress("dalian");
        orderMaster.setOrderAmount(new BigDecimal(10));
        System.out.println(orderMasterMapper.insertByObject(orderMaster));
    }
    @Test
    public void selectByOpenid() {
        List<OrderMaster> list =orderMasterMapper.selectByOpenid("12345");
        for(OrderMaster orderMaster:list){
            System.out.println(orderMaster);
        }
    }
}