package cn.zfl.myshop.pojo.mapper;

import cn.zfl.myshop.pojo.SellerInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhangfl<br />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/12/24 22:04<br/>
 */
public interface SellerInfoMapper {
    @Select("select * from seller_info where openid=#{openid}")
    SellerInfo selectByOpenid(String openid);

    @Insert("insert into seller_info(seller_id,username,password,openid) values(" +
            "#{sellerId},#{username},#{password},#{openid})")
    int insertByObject(SellerInfo sellerInfo);
}
