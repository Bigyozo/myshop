package cn.zfl.myshop.controller;

import cn.zfl.myshop.ViewObj.ProductInfoVO;
import cn.zfl.myshop.ViewObj.ProductVO;
import cn.zfl.myshop.ViewObj.ResultVO;
import cn.zfl.myshop.pojo.ProductCategory;
import cn.zfl.myshop.pojo.ProductInfo;
import cn.zfl.myshop.service.CategoryService;
import cn.zfl.myshop.service.ProductService;
import cn.zfl.myshop.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: myshop
 * @description:
 * @author: zhangfl
 * @create: 2018-11-11 14:47
 **/
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product", key = "123", unless = "#result.getCode() != 0")
    public ResultVO list() {
        //查询上架商品及其对应类目
        List<ProductInfo> productInfoList = productService.getUpAll();
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.getByCategoryTypeList(categoryTypeList);
        //数据封装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }
}
