package com.thgykj.router.core.executor.impl;

import com.thgykj.router.core.executor.IpRouterExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Description  针对 springboot 的项目自动装配
 * DATA 2023-12-13
 *
 * @Author ttt
 */
public class IpRouterSpringExecutor extends IpRouterExecutor implements ApplicationContextAware, SmartInitializingSingleton, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(IpRouterSpringExecutor.class);

    @Override
    public void destroy() throws Exception {
        // 关于 任务销毁
//        super.destroy();
    }

    @Override
    public void afterSingletonsInstantiated() {

        // super start
        try {
            super.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
