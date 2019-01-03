package cn.zfl.myshop.service.impl;

import cn.zfl.myshop.dto.OrderDTO;
import cn.zfl.myshop.enums.ResultEnum;
import cn.zfl.myshop.exception.SellException;
import cn.zfl.myshop.service.BuyerService;
import cn.zfl.myshop.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangfl<br               />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/11/26 20:34<br/>
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】查不到订单，orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        //联通测试写死openid
        openid="oTgZpwSvK3N4-TrrbQnb4fB3wj5E";
        OrderDTO orderDTO = orderService.getOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单openid不一致，openid={},orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
