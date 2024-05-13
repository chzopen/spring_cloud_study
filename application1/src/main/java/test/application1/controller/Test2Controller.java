package test.application1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Properties;

@Slf4j
@RestController
@RequestMapping("/test2")
public class Test2Controller {

    @Autowired
    private ConfigurableEnvironment configurableEnvironment;

    @Autowired
    private Environment environment;

    @PostConstruct
    public void init()
    {
//        Map<String, Object> systemEnvironment = configurableEnvironment.getSystemEnvironment();
//        Map<String, Object> systemProperties = configurableEnvironment.getSystemProperties();

//        MutablePropertySources mutablePropertySources = configurableEnvironment.getPropertySources();
//        Properties properties = new Properties();
//        properties.put("spring.test.redis.host", "192.168.1.100");
//        properties.put("spring.test.redis.password", "password");
//        properties.put("spring.test.redis.port", "6379");
//        mutablePropertySources.addFirst(new PropertiesPropertySource("defaultProperties", properties));
//
        String property = configurableEnvironment.getProperty("management.health.db.enabled");
        System.out.println(property);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello: "+ configurableEnvironment.getProperty("management.health.db.enabled");
    }

}