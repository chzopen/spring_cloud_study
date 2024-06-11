package com.chz.myFeignClient.feign;


import com.chz.myFeignClient.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "testBaiduFeignClient",
        contextId = "com.chz.myFeignClient.feign.TestFeignClient",
        url = "http://www.baidu.com",
        configuration = FeignConfiguration.class
)
public interface TestBaiduFeignClient
{
    @GetMapping(value = "/")
    String testGet();
}
