package online.pangge.exam.util;

import online.pangge.exam.domain.Student;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by jie34 on 2017/4/13.
 */
public class MethodCacheInterceptor implements MethodInterceptor {
    private Logger logger = Logger.getLogger(MethodCacheInterceptor.class);
    private RedisUtil redisUtil;
    private List<String> targetNamesList; // 不加入缓存的service名称
    private List<String> methodNamesList; // 不加入缓存的方法名称
    private Long defaultCacheExpireTime; // 缓存默认的过期时间
    private Long xxxRecordManagerTime; //
    private Long xxxSetRecordManagerTime; //

    public MethodCacheInterceptor() {
        System.out.println("redis缓存的构造方法");
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("redis.properties");
            Properties p = new Properties();
            p.load(in);
            // 分割字符串
            String[] targetNames = p.getProperty("targetNames").split(",");
            String[] methodNames = p.getProperty("methodNames").split(",");

            // 加载过期时间设置
            defaultCacheExpireTime = Long.valueOf(p.getProperty("defaultCacheExpireTime"));
            xxxRecordManagerTime = Long.valueOf(p.getProperty("com.service.impl.xxxRecordManager"));
            xxxSetRecordManagerTime = Long.valueOf(p.getProperty("com.service.impl.xxxSetRecordManager"));
            // 创建list
            targetNamesList = new ArrayList<String>(targetNames.length);
            methodNamesList = new ArrayList<String>(methodNames.length);
            Integer maxLen = targetNames.length > methodNames.length ? targetNames.length : methodNames.length;
            // 将不需要缓存的类名和方法名添加到list中
            for (int i = 0; i < maxLen; i++) {
                if (i < targetNames.length) {
                    targetNamesList.add(targetNames[i]);
                }
                if (i < methodNames.length) {
                    methodNamesList.add(methodNames[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object value = null;
        System.out.println("进入拦截的方法");
        String targetName = invocation.getThis().getClass().getName();
        String methodName = invocation.getMethod().getName();
        System.out.println("targetName="+targetName+",methname="+methodName);
        // 不需要缓存的内容
        //if (!isAddCache(StringUtil.subStrForLastDot(targetName), methodName)) {
        if (!isAddCache(targetName, methodName)) {
            System.out.println("此方法不需要缓存");
            // 执行方法返回结果
            return invocation.proceed();
        }
        Object[] arguments = invocation.getArguments();
        String key = getCacheKey(targetName, methodName, arguments);
        System.out.println("key="+key);

        try {
            // 判断是否有缓存
            if (redisUtil.exists(key)) {
                System.out.println("有缓存，此时查询缓存的值");
                Object i  = redisUtil.get(key);
                System.out.println(i);
                System.out.println(i instanceof Student);
                return SerializeUtil.unSerialize((byte[])i);
            }
            System.out.println("开始执行被拦截的方法");
            // 写入缓存
            value = invocation.proceed();
            System.out.println(value==null);
            System.out.println("无缓存，此时写入缓存，值为："+value);
            if (value != null) {
                final String tkey = key;
                final Object tvalue = value;
                System.out.println("开始写入缓存");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (tkey.startsWith("com.service.impl.xxxRecordManager")) {
                            System.out.println("开启线程1，写入xxxRecordManager，key="+tkey);
                            redisUtil.set(tkey, SerializeUtil.serialize(tvalue), xxxRecordManagerTime);
                        } else if (tkey.startsWith("com.service.impl.xxxSetRecordManager")) {
                            System.out.println("开启线程2，xxxSetRecordManager，key="+tkey);
                            redisUtil.set(tkey, SerializeUtil.serialize(tvalue), xxxSetRecordManagerTime);
                        } else {
                            System.out.println("开启线程3，写入默认缓存，key="+tkey);
                            redisUtil.set(tkey, SerializeUtil.serialize(tvalue), defaultCacheExpireTime);
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (value == null) {
                return invocation.proceed();
            }
        }
        return value;
    }

    /**
     * 是否加入缓存
     *
     * @return
     */
    private boolean isAddCache(String targetName, String methodName) {
        boolean flag = true;
        if (targetNamesList.contains(targetName)
                || methodNamesList.contains(methodName)) {
            flag = false;
        }
        return flag;
    }

    /**
     * 创建缓存key
     *
     * @param targetName
     * @param methodName
     * @param arguments
     */
    private String getCacheKey(String targetName, String methodName, Object[] arguments) {
        StringBuffer sbu = new StringBuffer();
        sbu.append(targetName).append("_").append(methodName);
        if ((arguments != null) && (arguments.length != 0)) {
            for (int i = 0; i < arguments.length; i++) {
                sbu.append("_").append(arguments[i]);
            }
        }
        return sbu.toString();
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
}