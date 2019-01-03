package cn.zfl.myshop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author zhangfl<br       />
 * @program:myshop
 * @Description:分布式锁 <br/>
 * @create: 2019/1/1 19:12<br/>
 */
@Component
@Slf4j
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean lock(String key, String value) {
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        //过期置换新锁
        String currentValue = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(currentValue) &&
                Long.parseLong(currentValue) < System.currentTimeMillis()){
            String oldValue=redisTemplate.opsForValue().getAndSet(key,value);
            if(!StringUtils.isEmpty(oldValue) &&oldValue.equals(currentValue)){
                return true;
            }
        }
        return false;
    }

    public void unlock(String key, String value){
        try {
            String currentValue=redisTemplate.opsForValue().get(key);
            if(!StringUtils.isEmpty(currentValue) && currentValue.equals(value)){
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            log.error("【redis分布式锁】解锁异常，{}",e);
        }
    }

}
