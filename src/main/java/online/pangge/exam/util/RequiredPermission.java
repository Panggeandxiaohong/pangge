package online.pangge.exam.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//定义在方法上
@Retention(RetentionPolicy.RUNTIME)//加载在JVM中
public @interface RequiredPermission {
    String value();//权限的中文名称
}
