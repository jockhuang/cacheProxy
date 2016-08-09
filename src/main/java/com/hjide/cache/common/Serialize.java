package com.hjide.cache.common;

import java.lang.annotation.*;

/**
 * author: yanghua.yi
 * date: 2015/1/9
 * time: 16:56
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Serialize {
    String name() default "";
}