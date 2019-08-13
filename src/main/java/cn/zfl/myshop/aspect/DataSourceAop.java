package cn.zfl.myshop.aspect;

import cn.zfl.myshop.config.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Auther: zhangfanglong
 * @Date: 2019/8/13/013 10:22
 * @Description:
 */
@Aspect
@Component
public class DataSourceAop {
    @Pointcut("@annotation(cn.zfl.myshop.aspect.Slave) " +
            "&& (execution(* cn.zfl.myshop.service.impl.*.get*(..)))")
    public void readPointcut() {
    }

    @Pointcut("!@annotation(cn.zfl.myshop.aspect.Slave) " +
            "&& (execution(* cn.zfl.myshop.service.impl.*.*(..)))")
    public void writePointcut(){
    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }
}
