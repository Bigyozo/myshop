package cn.zfl.myshop.service.impl;

import cn.zfl.myshop.dto.OrderDTO;
import cn.zfl.myshop.service.OrderService;
import cn.zfl.myshop.service.PayService;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
 * @create: 2018/12/3 19:07<br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {
    @Autowired
    private PayService payService;
    @Autowired
    private OrderService orderService;
    @Test
    public void create() {
        OrderDTO orderDTO= orderService.getOne("15431471970501122744");
        payService.create(orderDTO);
    }
}