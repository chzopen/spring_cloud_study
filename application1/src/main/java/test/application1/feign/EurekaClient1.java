package test.application1.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name="eurekaClient1",
        path = "/test",
        fallbackFactory = EurekaClient1Fallback.class
)
public interface EurekaClient1 {

    @GetMapping("/hello")
    String hello();

    @GetMapping("/hello2")
    String hello2();

    @GetMapping("/hello3")
    String hello3();
}

@Slf4j
@Component
class EurekaClient1Fallback implements FallbackFactory<EurekaClient1>
{

    @Override
    public EurekaClient1 create(Throwable cause) {
        return new EurekaClient1() {
            @Override
            public String hello() {
                log.error("err", cause);
                return "EurekaClient1Fallback.hello()";
            }

            @Override
            public String hello2() {
                log.error("err", cause);
                return "EurekaClient1Fallback.hello2()";
            }

            @Override
            public String hello3() {
                log.error("err", cause);
                return "EurekaClient1Fallback.hello3()";
            }
        };
    }

}