package com.chz.myWeb.helper;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class ThreadTestComponent {

    private static Thread testThread = null;

    @PostConstruct
    private void init()
    {
        testThread = new Thread(()->{
            while(true){
                try {
                    System.out.println("ThreadTestComponent::startTestThread()");
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("ThreadTestComponent::interrupted1: "+Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    System.out.println("ThreadTestComponent::interrupted2: "+Thread.currentThread().isInterrupted());
                    break;
                }
            }
        });
        testThread.start();
    }

    @PreDestroy
    private void destroy()
    {
        System.out.println("ThreadTestComponent::destroy()");
        // 这种自己创建的线程在【application.close()】被调用时是不会自动关闭的，需要手动关闭。
        testThread.interrupt();
    }
}
