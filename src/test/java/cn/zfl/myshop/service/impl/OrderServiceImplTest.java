package cn.zfl.myshop.service.impl;

import cn.zfl.myshop.LoggerTest;
import cn.zfl.myshop.dto.OrderDTO;
import cn.zfl.myshop.enums.OrderStatusEnum;
import cn.zfl.myshop.pojo.OrderDetail;
import cn.zfl.myshop.pojo.OrderMaster;
import cn.zfl.myshop.pojo.ProductInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author zhangfl<br />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/11/21 21:46<br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    private static final String ORDER_ID = "15428109405171095717";
    private final Logger logger= LoggerFactory.getLogger(LoggerTest.class);
    private static final String BUYER_OPENID ="1232131" ;
    @Autowired
    private  OrderServiceImpl orderService;

    @Test
    public void create() {
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("买家p");
        orderDTO.setBuyerAddress("dalian");
        orderDTO.setBuyerPhone("1234902313");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        List<OrderDetail>orderDetailList=new ArrayList<>();
        OrderDetail o1=new OrderDetail();
        o1.setProductId("2");
        o1.setProductQuantity(3);
        OrderDetail o2=new OrderDetail();
        o2.setProductId("3");
        o2.setProductQuantity(5);
        orderDetailList.add(o1);
        orderDetailList.add(o2);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result =orderService.create(orderDTO);
        logger.info("创建订单 result={}",result);
    }

    @Test
    public void getOne() {
        OrderDTO orderDTO=orderService.getOne(ORDER_ID);
        logger.info("查询订单result={}",orderDTO);
        Assert.assertEquals(ORDER_ID,orderDTO.getOrderId());
    }

    @Test
    public void getList() {
        Page<ProductInfo> pageInfo= PageHelper.startPage(1,2);
        List<OrderDTO>orderDTOList=orderService.getList(BUYER_OPENID);
        Assert.assertNotEquals(0,orderDTOList.size());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO=orderService.getOne(ORDER_ID);
        OrderDTO result=orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO=orderService.getOne(ORDER_ID);
        OrderDTO result=orderService.finish(orderDTO);

    }

    @Test
    public void paid() {
        OrderDTO orderDTO=orderService.getOne(ORDER_ID);
        OrderDTO result=orderService.paid(orderDTO);

    }

    @Test
    public void list(){
        List<OrderDTO>orderDTOList=orderService.getList();

    }
}