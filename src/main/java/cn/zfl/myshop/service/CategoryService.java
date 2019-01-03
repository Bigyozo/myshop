package cn.zfl.myshop.service;

import cn.zfl.myshop.pojo.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    ProductCategory getOne(Integer categoryType);

    List<ProductCategory> getAll();

    List<ProductCategory> getByCategoryTypeList(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
