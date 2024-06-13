package com.chz.myBeanMain.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

// 被【@Import】指过来的bean不需要添加【@Component】
@Slf4j
@Getter
@Setter
public class NotAnnotatedBean {

    private String name = "I am " + this.getClass().getSimpleName();

    public NotAnnotatedBean()
    {
        log.info("chz >>> NotAnnotatedBean.<init>()");
    }

}
