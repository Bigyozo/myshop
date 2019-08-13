package cn.zfl.myshop.aspect;


import cn.zfl.myshop.constant.CookieConstant;
import cn.zfl.myshop.constant.RedisConstant;
import cn.zfl.myshop.exception.SellerAuthorizeException;
import cn.zfl.myshop.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangfl<br               />
 * @program:myshop
 * @Description: 卖家端登陆验证切面类<br/>
 * @create: 2018/12/27 21:30<br/>
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * cn.zfl.myshop.controller.Seller*.*(..))" +
            "&& !execution(public * cn.zfl.myshop.controller.SellerUserController.*(..))")
    public void verify() {
    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登陆校验】Cookie中无token");
            throw new SellerAuthorizeException();
        }
        //redis查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登陆校验】Redis中无token");
            throw new SellerAuthorizeException();
        }
    }
}
