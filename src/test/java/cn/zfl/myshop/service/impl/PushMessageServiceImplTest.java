package cn.zfl.myshop.service.impl;

import cn.zfl.myshop.dto.OrderDTO;
import cn.zfl.myshop.service.OrderService;
import cn.zfl.myshop.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangfl<br />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/12/28 21:33<br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PushMessageServiceImplTest {
    @Autowired
    private PushMessageService pushMessageService;
    @Autowired
    private OrderService orderService;
    @Test
    public void orderStatus() {
        OrderDTO orderDTO=orderService.getOne("15428109405171095717");
        pushMessageService.orderStatus(orderDTO);
    }
}