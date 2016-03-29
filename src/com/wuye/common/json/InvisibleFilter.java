package com.wuye.common.json;

import com.wuye.common.annotation.Invisible;
import com.wuye.common.exception.RtManagerException;
import com.wuye.common.vo.PageInfo;
import org.apache.log4j.Logger;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class InvisibleFilter extends AbstractFilter {
    private Logger log = Logger.getLogger(InvisibleFilter.class);
    // 过滤条件，标注中有符合这个条件的property将被过滤掉
    private String[] _sGUIID;
    private boolean isHibernateFilter = false;

    public InvisibleFilter(boolean isHibernateFilter, final String... guiid) {
        _sGUIID = guiid;
        this.isHibernateFilter = isHibernateFilter;
    }

    public boolean apply(final Object source, final String name, final Object value) {
        if (source instanceof Map || source instanceof PageInfo) {
            return false;
        }
        if (this.isHibernateFilter) {
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
        }
        if (_sGUIID == null || _sGUIID.length == 0) {
            return false;                                         // 表示不做限制
        }
        return this.applyField(source, name, value) /*|| this.applyMethod(source, name, value)*/;
    }

    private boolean applyMethod(final Object source, final String name, final Object value) {
        String propName = name.substring(0, 1).toUpperCase() + name.substring(1);
        Class clz = source.getClass();
        String methodName = "get" + propName;
        Method method = null;
        Field field = null;
        try {
            method = clz.getMethod(methodName, (Class[]) null);   // 寻找属性的get方法
        } catch (NoSuchMethodException nsme) {
            String methodName2 = "is" + propName;                // 也许是个is方法
            try {
                method = clz.getMethod(methodName2, (Class[]) null);
            } catch (NoSuchMethodException ne) {
                // 没有找到属性的get或者is方法，打印错误，返回true
                log.info("No such methods: "
                        + methodName + " or " + methodName2);
                return true;
            }
        }
        if (method.isAnnotationPresent(Invisible.class)) {
            Invisible anno = method.getAnnotation(Invisible.class);
            String[] annoValue = anno.value();
            for (int i = 0; i < annoValue.length; i++) {
                for (String __sGUIID : _sGUIID) {
                    if (__sGUIID.equals(annoValue[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean applyField(final Object source, final String name, final Object value) {
        Field field = null;
        Class clz = source.getClass();
        try {
            for (; clz != Object.class; clz = clz.getSuperclass()) {
                try {
                    field = clz.getDeclaredField(name);
                    if (field != null) {
                        break;
                    }

                } catch (Exception e) {
                    //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。  
                    //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了  

                }
            }

            if (field != null && field.isAnnotationPresent(Invisible.class)) {
                Invisible anno = field.getAnnotation(Invisible.class);
                String[] annoValue = anno.value();
                for (int i = 0; i < annoValue.length; i++) {
                    for (String __sGUIID : _sGUIID) {
                        if (__sGUIID.equals(annoValue[i])) {
                            return false;
                        }
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.info(e.getMessage());
            throw new RtManagerException("json过滤判断失败");
        }
        return true;
    }
}
