package cn.zfl.myshop.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangfl<br   />
 * @program:myshop
 * @Description: 平台账户配置<br/>
 * @create: 2018/11/29 16:41<br/>
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    //公众平台
    private String mpAppId;

    private String mpAppSecret;
    //开放平台
    private String openAppId;

    private String openAppSecret;
    //商户号
    private String mchId;
    //商户密钥
    private String mchKey;
    //商户证书路径
    private String keyPath;
    //微信支付异步通知地址
    private String notifyUrl;
}
