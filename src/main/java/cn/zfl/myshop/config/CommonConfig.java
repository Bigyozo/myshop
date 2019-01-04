package cn.zfl.myshop.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @program: myshop
 * @description: MyBatis分页插件pageHelper配置
 * @author: zhangfl
 * @create: 2018-11-11 13:44
 **/
@Configuration
public class CommonConfig {
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("dialect", "mysql");
        p.setProperty("supportMethodsArguments", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("params","count=countSql");
        pageHelper.setProperties(p);

        return pageHelper;
    }
}
