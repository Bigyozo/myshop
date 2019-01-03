package cn.zfl.myshop.pojo.mapper;

import cn.zfl.myshop.pojo.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderDetailMapper {
    @Select("select * from order_detail where order_id = #{orderId}")
    List<OrderDetail> selectByOrderId(String orderId);

    @Insert("insert into order_detail(detail_id,order_id,product_id,product_name,product_price,product_quantity," +
            "product_icon) values(#{detailId},#{orderId},#{productId},#{productName},#{productPrice},#{productQuantity}" +
            ",#{productIcon})")
    int insertByObject(OrderDetail orderDetail);
}
