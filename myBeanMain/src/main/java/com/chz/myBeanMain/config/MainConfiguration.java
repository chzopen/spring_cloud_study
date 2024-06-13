package com.chz.myBeanMain.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MainConfiguration {

    public MainConfiguration()
    {
        log.info("chz >>> MainConfiguration.<init>()");
    }

}
