package test.application1.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name="StaticEurekaClient1",
        url="http://localhost:8001/"
)
public interface StaticEurekaClient1 {

    @GetMapping("/test/hello")
    String hello();

}