package cn.zfl.myshop.service;

import cn.zfl.myshop.dto.OrderDTO;

/**
 * @author zhangfl<br />
 * @program:myshop
 * @Description:微信模板消息发送 <br/>
 * @create: 2018/12/28 21:23<br/>
 */
public interface PushMessageService {
    void orderStatus(OrderDTO orderDTO);
}
