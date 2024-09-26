package com.chz.myJPA.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Data
@Component
@PropertySource("classpath:a.properties")
@ConfigurationProperties(prefix = "my")
public class MyPropertiesBean {

    private final Map<String, String> test;

    @PostConstruct
    public void init()
    {
        printMap();
    }

    public void printMap() {
        for (Map.Entry<String, String> entry : test.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}