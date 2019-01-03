package cn.zfl.myshop.pojo.mapper;

import cn.zfl.myshop.pojo.OrderMaster;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMasterMapper {
    @Select("select * from order_master")
    List<OrderMaster> selectAll();

    @Select("select * from order_master where buyer_openid = #{buyerOpenid}")
    List<OrderMaster> selectByOpenid(String buyerOpenid);

    @Select("select * from order_master where order_id = #{OrderId}")
    OrderMaster selectByOrderId(String OrderId);

    @Insert("replace into order_master(order_id,buyer_name,buyer_phone,buyer_address,buyer_openid," +
            "order_amount,order_status,pay_status) values(#{orderId},#{buyerName},#{buyerPhone},#{buyerAddress},#{buyerOpenid}" +
            ",#{orderAmount},#{orderStatus},#{payStatus})")
    int insertByObject(OrderMaster orderMaster);
}
