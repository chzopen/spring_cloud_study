package com.chz.myFeignClient.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignRequestInterceptor implements RequestInterceptor
{
    @Override
    public void apply(RequestTemplate template)
    {
        // 这里给每一个FeignClient的调用增加一个header
        template.header("chz-new-header", "just-for-test");
    }
}