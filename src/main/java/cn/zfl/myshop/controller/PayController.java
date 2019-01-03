package cn.zfl.myshop.controller;

import cn.zfl.myshop.dto.OrderDTO;
import cn.zfl.myshop.enums.ResultEnum;
import cn.zfl.myshop.exception.SellException;
import cn.zfl.myshop.service.OrderService;
import cn.zfl.myshop.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * @author zhangfl<br   />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/12/2 10:48<br/>
 */
@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map) {
        //查询订单
        OrderDTO orderDTO = orderService.getOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse", payResponse);
        try{
            String decodeUrl= URLDecoder.decode(returnUrl,"UTF-8");
            map.put("returnUrl", decodeUrl);
        }
         catch (UnsupportedEncodingException e) {
            log.error("【支付订单】返回地址解析错误，returnUrl={}",returnUrl);
        }
        return new ModelAndView("pay/create", map);
    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);
        //回传微信处理结果
        return new ModelAndView("pay/success");
    }
}
