package com.wuye.common.json;

import net.sf.json.util.PropertyFilter;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

public class HibernatePropertyFilter implements PropertyFilter {

    /**
     * 过滤不需要被序列化的属性，主要是应用于Hibernate的代理和管理。
     *
     * @param object 属性所在的对象
     * @param name   属性名
     * @param value  属性值
     * @return 返回true属性将被忽略，false属性将被保留
     */
    public boolean apply(Object object, String name, Object value) {
        if (value instanceof HibernateProxy) {// hibernate代理对象
            LazyInitializer initializer = ((HibernateProxy) value)
                    .getHibernateLazyInitializer();
            if (initializer.isUninitialized()) {
                return true;
            }
        } else if (value instanceof PersistentCollection) {// 实体关联集合一对多等
            PersistentCollection collection = (PersistentCollection) value;
            if (!collection.wasInitialized()) {
                return true;
            }
            Object val = collection.getValue();
            if (val == null) {
                return true;
            }
        }
        return false;
    }

}