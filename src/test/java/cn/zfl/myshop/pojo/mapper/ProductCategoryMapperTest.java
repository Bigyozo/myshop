package cn.zfl.myshop.pojo.mapper;

import cn.zfl.myshop.pojo.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryMapper mapper;
    @Test
    public void insertByMap() {
        Map<String,Object> map=new HashMap<>();
        map.put("categoryName","favor");
        map.put("categoryType",131);
        int result=mapper.insertByMap(map);
        Assert.assertEquals(1,result);
    }
    @Test
    public void insertByObject(){
        ProductCategory pc =new ProductCategory();
        pc.setCategoryName("饭类");
        pc.setCategoryType(1);
        int result=mapper.insertByObject(pc);
        System.out.println(pc);
        Assert.assertEquals(1,result);
    }
    @Test
    public void test(){
        ProductCategory pc=mapper.selectOneByCategoryType(22);
        System.out.println(pc);
    }
    @Test
    public void selectAll(){
        List<Integer> listin= Arrays.asList(22,23,101);
        List<ProductCategory> list=mapper.selectByCategoryTypeList(listin);
        for(ProductCategory p:list){
            System.out.println(p);
        }
    }

}