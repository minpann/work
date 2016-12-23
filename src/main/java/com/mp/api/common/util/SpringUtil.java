package com.mp.api.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by panmin on 16-12-23.
 */
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    @SuppressWarnings("all")
    public static <T> T getBean(String beanName) {
        return (T) (context == null ? null : context.getBean(beanName));
    }

    @SuppressWarnings("all")
    public static <T> T getBean(Class<T> clz) {
        return context == null ? null : context.getBean(clz);
    }

    public static String[] getBeanDefinitionNames() {
        return context.getBeanDefinitionNames();
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringUtil.context = context;
    }
}
