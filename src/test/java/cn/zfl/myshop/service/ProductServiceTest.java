package cn.zfl.myshop.service;

import cn.zfl.myshop.pojo.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author zhangfl<br />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/12/14 20:00<br/>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    ProductService productService;
    @Test
    public void getAll() {
        List<ProductInfo> list=productService.getAll();
        System.out.println(list);
    }

    @Test
    public void save() {
    }

    @Test
    public void onSale() {
    }

    @Test
    public void offSale() {
    }
}