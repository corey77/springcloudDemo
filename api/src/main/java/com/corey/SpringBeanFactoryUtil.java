package com.corey;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * <p/>
 * <li>Description:spring创建实体工具类</li>
 * <li>@author: Corey </li>
 * <li>Date: 2016/11/9 </li>
 * <li>@version: 1.0.0 </li>
 */
@Component
public class SpringBeanFactoryUtil implements BeanFactoryAware {
    
    /**
     * <li>beanFactory</li>
     */
    private static BeanFactory beanFactory;
    
    /**
     * 根据beanName名字取得bean
     *
     * @param <T>      创建的bean实体
     * @param beanName 需要创建的bean实体名称
     *
     * @return 创建的bean实体
     * @author Corey
     */
    public static <T> T getBean(String beanName) {

        if (null != beanFactory) {
            return (T) beanFactory.getBean(beanName);
        }
        return null;
    }
    
    /**
     * 根据beanName名字取得bean
     *
     * @param <T>       创建的bean实体
     * @param beanClass 需要创建的bean class
     *
     * @return 创建的bean实体
     * @author Corey
     */
    public static <T> T getBean(Class<T> beanClass) {

        if (null != beanFactory) {
            return (T) beanFactory.getBean(beanClass);
        }
        return null;
    }
    
    /**
     * Callback that supplies the owning factory to a bean instance.
     * <p>Invoked after the population of normal bean properties
     * but before an initialization callback such as
     * {@link InitializingBean#afterPropertiesSet()} or a custom init-method.
     *
     * @param beanFactory owning BeanFactory (never {@code null}).
     *                    The bean can immediately call methods on the factory.
     *
     * @throws BeansException
     * @author Corey
     * @see BeanInitializationException
     */
    @Override
    public void setBeanFactory( BeanFactory beanFactory) throws BeansException {

        this.beanFactory = beanFactory;
    }
}
