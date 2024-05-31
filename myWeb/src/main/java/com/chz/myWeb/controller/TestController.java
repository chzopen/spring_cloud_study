package com.chz.myWeb.controller;

import com.chz.myWeb.helper.ShutdownHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    // 当【applicationContext.close()】被调用的时候这些destroy方法会被调用
    @PreDestroy
    private void destroyBean()
    {
        System.out.println("TestController::destroyBean start");
        try {
            // 如果某个队列里面有很多的数据，我们可以在这里调用队列的方法让线程阻塞至队列数据被全部发出去。
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
        }
        System.out.println("TestController::destroyBean end");
    }

    @GetMapping("/shutdown")
    public String shutdown() {
        // 这里直接调用关闭
        ShutdownHelper.shutdown();
        return "shutdown";
    }
}