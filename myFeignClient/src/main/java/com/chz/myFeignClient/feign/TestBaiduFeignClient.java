package com.chz.myFeignClient.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "testBaiduFeignClient",
        contextId = "com.chz.myFeignClient.feign.TestBaiduFeignClient",
        url = "http://www.baidu.com"
)
public interface TestBaiduFeignClient
{
    @GetMapping(value = "/")
    String testGet();
}
