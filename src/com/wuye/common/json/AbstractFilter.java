package com.wuye.common.json;

import net.sf.json.util.PropertyFilter;

import java.util.Map;

// 先实现一个abstract类，将读取Bean属性的Method找到并传递给子类处理
public abstract class AbstractFilter implements PropertyFilter {
    // 这个方法留给子类实现，以便适应不同的过滤需求
    //public abstract boolean apply(final Method method);

    public boolean apply(final Object source, final String name, final Object value) {
        if (source instanceof Map) {
            return false;
        }
        return true;
    }
} // END: AbstractMethodFilter