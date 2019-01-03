package cn.zfl.myshop.service.impl;

import cn.zfl.myshop.dto.OrderDTO;
import cn.zfl.myshop.enums.ResultEnum;
import cn.zfl.myshop.exception.SellException;
import cn.zfl.myshop.service.OrderService;
import cn.zfl.myshop.service.PayService;
import cn.zfl.myshop.utils.JsonUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangfl<br       />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/12/2 10:57<br/>
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {
    private static final String ORDER_NAME = "微信点餐订单";
    @Autowired
    private BestPayServiceImpl bestPayService;
    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】response={}", JsonUtil.toJson(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知，payResponse={}", JsonUtil.toJson(payResponse));

        OrderDTO orderDTO = orderService.getOne(payResponse.getOrderId());
        if (orderDTO == null) {
            log.error("【微信支付】异步通知，订单不存在，orderId={}", payResponse.getOrderId());
        }
        if (orderDTO.getOrderAmount().doubleValue() != payResponse.getOrderAmount().doubleValue()) {
            log.error("【微信支付】异步通知，订单金额不一致，orderId={},微信通知金额={}，系统金额={}",
                    payResponse.getOrderId(), payResponse.getOrderAmount(), orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_ERROR);
        }
        //修改订单的支付状态
        orderService.paid(orderDTO);
        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】request={}", refundRequest);
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】response={}", refundResponse);

        return refundResponse;
    }
}
