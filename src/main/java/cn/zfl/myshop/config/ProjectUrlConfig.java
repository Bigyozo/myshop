package cn.zfl.myshop.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangfl<br   />
 * @program:myshop
 * @Description: <br/>
 * @create: 2018/12/25 22:50<br/>
 */
@Data
@ConfigurationProperties(prefix = "projectUrl")
@Component
public class ProjectUrlConfig {
    //公众平台url
    public String wechatMpAuthorize;
    //开放平台url
    public String wechatOpenAuthorize;
    //主机url
    public String sell;
}
