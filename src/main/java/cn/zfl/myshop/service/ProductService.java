package cn.zfl.myshop.service;

import cn.zfl.myshop.dto.CartDTO;
import cn.zfl.myshop.pojo.ProductInfo;
import org.springframework.stereotype.Service;


import java.util.List;

public interface ProductService {
     ProductInfo getOne(String productId);
    /*
    * 查询所有上架商品
    * */
     List<ProductInfo> getUpAll();

     List<ProductInfo> getAll();

     ProductInfo save(ProductInfo productInfo);

     void increaseStock(List<CartDTO> cartDTOList);

     void decreaseStock(List<CartDTO> cartDTOList);

     ProductInfo onSale(String productId);

     ProductInfo offSale(String productId);
}
