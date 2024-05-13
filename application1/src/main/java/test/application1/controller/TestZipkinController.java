//package test.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import myZipkin.zipkin.ZipkinHelper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import test.feign.EurekaClient1;
//
//@Slf4j
//@RestController
//@RequestMapping("/zipkin")
//public class TestZipkinController {
//
//    @Autowired
//    private EurekaClient1 eurekaClient1;
//
//    @Autowired
//    @Qualifier("restTemplate")
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private ZipkinHelper zipkinHelper;
//
//    @GetMapping("/hello1")
//    public ResultDTO<String> hello2() {
//
//        Runnable runnable = zipkinHelper.wrap(() -> {
//            try {
//                Thread.sleep(2000L);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        runnable.run();
//
//        String result = eurekaClient1.hello2();
//        return new ResultDTO<>("Hello2: "+ result);
//    }
//
//    @GetMapping("/hello2")
//    public ResultDTO<String> hello4() {
//        String result = restTemplate.getForObject("http://eurekaClient1/test/hello", String.class);
//        return new ResultDTO<>("Hello3: "+ result);
//    }
//
//}