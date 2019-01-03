package cn.zfl.myshop.service;

import cn.zfl.myshop.pojo.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    ProductService productService;
    @Test
    public void getOne() {
        ProductInfo p=productService.getOne("2");
        System.out.println(p);
    }

    @Test
    public void getAll() {
    }

    @Test
    public void getByCategoryTypeList() {
    }

    @Test
    public void save() {
    }
}