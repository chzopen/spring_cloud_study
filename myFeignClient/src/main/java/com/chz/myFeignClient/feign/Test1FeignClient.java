package com.chz.myFeignClient.feign;


import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        contextId = "com.chz.myFeignClient.feign.Test1FeignClient",
        name = "myEurekaClient1",
        path = "/test",
        fallbackFactory = Test1FeignClientFallback.class
)
public interface Test1FeignClient
{
    @GetMapping(value = "/hello")
    String hello();
}

@Slf4j
@Component
class Test1FeignClientFallback implements FallbackFactory<Test1FeignClient>
{
    @Override
    public Test1FeignClient create(Throwable cause) {
        return new Test1FeignClient() {
            @Override
            public String hello() {
                log.error("chz >>> Test1FeignClientFallback.hello() -> fallback", cause);
                return "fallback hello";
            }
        };
    }
}
