package cn.zfl.myshop.service.impl;

import cn.zfl.myshop.aspect.Slave;
import cn.zfl.myshop.dto.CartDTO;
import cn.zfl.myshop.enums.ProductStatusEnum;
import cn.zfl.myshop.enums.ResultEnum;
import cn.zfl.myshop.exception.SellException;
import cn.zfl.myshop.pojo.ProductInfo;
import cn.zfl.myshop.pojo.mapper.ProductInfoMapper;
import cn.zfl.myshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoMapper mapper;

    @Override
    public ProductInfo getOne(String productId) {
        return mapper.selectOneByProductId(productId);
    }

    @Override
    public List<ProductInfo> getUpAll() {
        return mapper.selectByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Slave
    @Override
    public List<ProductInfo> getAll() {
        return mapper.selectAll();
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        mapper.insertByObject(productInfo);
        return productInfo;
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = mapper.selectOneByProductId(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            mapper.insertByObject(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = mapper.selectOneByProductId(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            mapper.insertByObject(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = mapper.selectOneByProductId(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新状态
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        mapper.insertByObject(productInfo);
        return productInfo;
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = mapper.selectOneByProductId(productId);
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新状态
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        mapper.insertByObject(productInfo);
        return productInfo;
    }
}
