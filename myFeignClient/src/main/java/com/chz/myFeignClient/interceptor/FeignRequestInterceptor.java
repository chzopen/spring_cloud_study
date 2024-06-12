package com.chz.myFeignClient.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor
{
    @Override
    public void apply(RequestTemplate template)
    {
        // 这里给每一个FeignClient的调用增加一个header
        log.info("FeignRequestInterceptor::apply: tid:{}", Thread.currentThread().getId());
        template.header("chz-new-header", "just-for-test");
    }
}