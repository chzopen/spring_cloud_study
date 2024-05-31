package com.chz.myWeb.helper;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ShutdownHelper implements ApplicationContextAware {

    @Autowired
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static void shutdown() {
        new Thread(()->{
            if( applicationContext instanceof ConfigurableApplicationContext){
                // 这里调用了applicationContext.close之后spring boot的接口就不会再接收请求了
                ((ConfigurableApplicationContext) applicationContext).close();
            }
        }).start();
    }
}
