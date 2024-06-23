package com.chz.myFeignClient.interceptor;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;

public class HystrixRequestContextUtils {

    public static final HystrixRequestVariableDefault<String> TOKEN = new HystrixRequestVariableDefault<>();

    public static void init()
    {
        if (!HystrixRequestContext.isCurrentThreadInitialized()) {
            HystrixRequestContext.initializeContext();
        }
    }

    public static void shutdown()
    {
        if (HystrixRequestContext.isCurrentThreadInitialized()) {
            HystrixRequestContext.getContextForCurrentThread().shutdown();
        }
    }

}
