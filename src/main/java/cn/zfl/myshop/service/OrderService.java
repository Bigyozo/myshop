package cn.zfl.myshop.service;

import cn.zfl.myshop.dto.OrderDTO;
import cn.zfl.myshop.pojo.OrderMaster;

import java.util.List;

/**
 * @author zhangfl<br />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/11/20 19:56<br/>
 */
public interface OrderService {
    OrderDTO create(OrderDTO orderMaster);

    OrderDTO getOne(String orderId);

    List<OrderDTO> getList(String buyerOpenid);

    OrderDTO cancel(OrderDTO orderDTO);

    OrderDTO finish(OrderDTO orderDTO);

    OrderDTO paid(OrderDTO orderDTO);

    List<OrderDTO> getList();
}
