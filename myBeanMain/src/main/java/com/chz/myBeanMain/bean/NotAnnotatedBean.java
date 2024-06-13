package com.chz.myBeanMain.bean;

import lombok.Getter;
import lombok.Setter;

// 被【@Import】指过来的bean不需要添加【@Component】
@Getter
@Setter
public class NotAnnotatedBean {

    private String name = "I am " + this.getClass().getSimpleName();

}
