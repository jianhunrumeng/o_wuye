package com.wuye.common.util;

import com.wuye.common.dao.hibernate.BaseDaoHibernate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext context; // 声明一个静态变量保存

    public void setApplicationContext(final ApplicationContext contex) {
        SpringUtil.context = contex;
    }

    public static ApplicationContext getContext() {
        return SpringUtil.context;
    }

    public static <T> T getBean(final String beanId) {
        return (T) SpringUtil.context.getBean(beanId);
    }

    public static boolean containsBean(final String beanId) {
        return SpringUtil.context.containsBean(beanId);
    }

    public static <T> T getBean(final String beanId, Object... args) {
        return (T) SpringUtil.context.getBean(beanId, args);
    }

    public static Object getBeanRmiLoc(final String beanRmiId,
                                       final String beanLocId) {
        if (true) {
            return SpringUtil.context.getBean(beanRmiId);
        }
        return SpringUtil.context.getBean(beanLocId);
    }

    public static BaseDaoHibernate getDao() {
        return getBean("dao");
    }
}
