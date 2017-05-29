package online.pangge.exam.util;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jie34 on 2017/4/13.
 */
public class RedisCache {
    @Autowired
    private RedisUtil redisUtil;
    public Object cache(JoinPoint joinPoint){
        return  null;
    }
}
