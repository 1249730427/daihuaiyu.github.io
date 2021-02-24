package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 模拟mybatis源码中的注解Select
 *
 * @author daihuaiyu
 * @create: 2021-02-24 13:28
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Select {

    String value() default "";
}
