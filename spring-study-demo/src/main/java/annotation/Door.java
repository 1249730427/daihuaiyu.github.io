package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * door
 *
 * @author daihuaiyu
 * @create: 2021-02-23 17:30
 **/
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Door {

    String keyValue() default "";

    String returnJson() default "白名单拦截";
}
