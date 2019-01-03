package cn.zfl.myshop.pojo.mapper;

import cn.zfl.myshop.pojo.ProductInfo;
import cn.zfl.myshop.service.impl.ProductServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoMapperTest {
    @Autowired
    private ProductInfoMapper mapper;
    @Autowired
    private ProductServiceImpl service;
    @Test
    public void insert() {
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("5");
        productInfo.setProductName("米饭");
        productInfo.setProductPrice(new BigDecimal(1));
        productInfo.setProductStock(100);
        productInfo.setProductIcon("http://q1eq.jpg");
        productInfo.setProductDescription("热销单品");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(1);
        int result=mapper.insertByObject(productInfo);
        System.out.println(result);
    }

    @Test
    public void select() {
    List<ProductInfo> list=mapper.selectByProductStatus(0);
    System.out.println(list.get(0));
    }

    @Test
    public void selectOne() {
    System.out.println(mapper.selectOneByProductId("12345"));
    }

    @Test
    public void name() {
        Page<ProductInfo>pageInfo=PageHelper.startPage(1,3);
        List<ProductInfo>list=service.getAll();
        List<ProductInfo>list2=pageInfo.getResult();
        for(ProductInfo p:list){
            System.out.println(p);
        }
        for(ProductInfo p:list2){
            System.out.println(p);
        }
    }
}