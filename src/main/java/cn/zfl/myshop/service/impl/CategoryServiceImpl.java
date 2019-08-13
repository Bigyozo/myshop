package cn.zfl.myshop.service.impl;

import cn.zfl.myshop.aspect.Slave;
import cn.zfl.myshop.pojo.ProductCategory;
import cn.zfl.myshop.pojo.mapper.ProductCategoryMapper;
import cn.zfl.myshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCategoryMapper mapper;

    @Override
    public ProductCategory getOne(Integer categoryType) {
        return mapper.selectOneByCategoryType(categoryType);
    }

    @Slave
    @Override
    public List<ProductCategory> getAll() {
        return mapper.selectAll();
    }

    @Override
    public List<ProductCategory> getByCategoryTypeList(List<Integer> categoryTypeList) {
        return mapper.selectByCategoryTypeList(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        mapper.insertByObject(productCategory);
        return productCategory;
    }
}
