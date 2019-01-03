package cn.zfl.myshop.pojo.mapper;

import cn.zfl.myshop.pojo.ProductInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductInfoMapper {
    @Insert("replace into product_info(product_id,product_name,product_price,product_stock,product_description,product_icon,product_status,category_type) " +
            "values(#{productId},#{productName},#{productPrice},#{productStock},#{productDescription},#{productIcon},#{productStatus},#{categoryType})")
    int insertByObject(ProductInfo productInfo);

    @Select("select * from product_info where product_status=#{productStatus}")
    List<ProductInfo> selectByProductStatus(Integer productStatus);

    @Select("select * from product_info where product_id=#{productId}")
    ProductInfo selectOneByProductId(String productId);

    @Select("select * from product_info")
    List<ProductInfo> selectAll();


}
