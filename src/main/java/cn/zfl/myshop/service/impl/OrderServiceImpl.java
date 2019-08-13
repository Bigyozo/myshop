package cn.zfl.myshop.service.impl;

import cn.zfl.myshop.aspect.Slave;
import cn.zfl.myshop.converter.OrderMaster2OrderDTO;
import cn.zfl.myshop.dto.CartDTO;
import cn.zfl.myshop.dto.OrderDTO;
import cn.zfl.myshop.enums.OrderStatusEnum;
import cn.zfl.myshop.enums.PayStatusEnum;
import cn.zfl.myshop.enums.ResultEnum;
import cn.zfl.myshop.exception.SellException;
import cn.zfl.myshop.pojo.OrderDetail;
import cn.zfl.myshop.pojo.OrderMaster;
import cn.zfl.myshop.pojo.ProductInfo;
import cn.zfl.myshop.pojo.mapper.OrderDetailMapper;
import cn.zfl.myshop.pojo.mapper.OrderMasterMapper;
import cn.zfl.myshop.service.*;
import cn.zfl.myshop.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangfl<br                               />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/11/20 20:46<br/>
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private OrderMasterMapper orderMasterMapper;
    @Autowired
    private PayService payService;
    @Autowired
    private PushMessageService pushMessageService;
    @Autowired
    private WebSocket webSocket;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.getOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //计算订单总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //写入订单详情
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            Integer detailResult = orderDetailMapper.insertByObject(orderDetail);
            if (detailResult == null) {
                log.error("【支付订单】，数据库修改失败，orderDetail={}", orderDetail);
                throw new SellException(ResultEnum.ORDERDETAIL_SQL_ERROR);
            }
        }
        //写入订单主体
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        Integer result = orderMasterMapper.insertByObject(orderMaster);
        if (result == null) {
            log.error("【支付订单】，数据库修改失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_SQL_ERROR);
        }
        //扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        //发送websocket消息
        webSocket.sendMessage("有新的订单");
        return orderDTO;
    }

    @Override
    public OrderDTO getOne(String orderId) {
        OrderMaster orderMaster = orderMasterMapper.selectByOrderId(orderId);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public List<OrderDTO> getList(String buyerOpenid) {
        List<OrderMaster> orderMasterList = orderMasterMapper.selectByOpenid(buyerOpenid);
        return OrderMaster2OrderDTO.convert(orderMasterList);
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);

        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确,orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        Integer result = orderMasterMapper.insertByObject(orderMaster);
        if (result == null) {
            log.error("【支付订单】，数据库修改失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_SQL_ERROR);
        }

        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情，orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(
                e -> new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //已支付订单退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            payService.refund(orderDTO);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】，订单状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        Integer result = orderMasterMapper.insertByObject(orderMaster);
        if (result == null) {
            log.error("【完结订单】，数据库修改失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_SQL_ERROR);
        }
        //推送微信模板消息
        pushMessageService.orderStatus(orderDTO);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【支付订单】，订单状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【支付订单】，订单支付状态不正确，orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        Integer result = orderMasterMapper.insertByObject(orderMaster);
        if (result == null) {
            log.error("【支付订单】，数据库修改失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_SQL_ERROR);
        }
        return orderDTO;
    }

    @Slave
    @Override
    public List<OrderDTO> getList() {
        List<OrderMaster>orderMasterList=orderMasterMapper.selectAll();
        return OrderMaster2OrderDTO.convert(orderMasterList);
    }
}
