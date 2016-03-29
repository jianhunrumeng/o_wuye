package com.wuye.common.annotation;

import java.lang.annotation.*;


@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Invisible {
    public String[] value();
}
