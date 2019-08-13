package cn.zfl.myshop.config;

import cn.zfl.myshop.enums.DBTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhangfanglong
 * @Date: 2019/8/10/
 * @Description:
 */
@Configuration
public class DataSourceConfig {
    @Autowired
    @Qualifier("masterds")
    DataSource masterds;

    @Autowired
    @Qualifier("slaveds")
    DataSource slaveds;

    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterds() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveds() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource myRoutingDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.MASTER, masterds);
        targetDataSources.put(DBTypeEnum.SLAVE, slaveds);
        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        myRoutingDataSource.setDefaultTargetDataSource(masterds);
        myRoutingDataSource.setTargetDataSources(targetDataSources);
        return myRoutingDataSource;
    }


}
