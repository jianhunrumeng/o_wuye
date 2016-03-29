package com.wuye.common.annotation;

import java.lang.annotation.*;


@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyCopy {
    public String[] value();
}