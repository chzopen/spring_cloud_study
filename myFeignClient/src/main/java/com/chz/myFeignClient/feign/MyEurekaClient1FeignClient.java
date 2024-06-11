package com.chz.myFeignClient.feign;


import com.chz.myFeignClient.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "myEurekaClient1",
        contextId = "com.chz.myFeignClient.feign.MyEurekaClient1TestFeignClient",
        path = "/test",
        configuration = FeignConfiguration.class
)
public interface MyEurekaClient1FeignClient
{
    @GetMapping(value = "/hello")
    String hello();
}
