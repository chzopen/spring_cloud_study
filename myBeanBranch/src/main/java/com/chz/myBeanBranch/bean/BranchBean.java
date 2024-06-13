package com.chz.myBeanBranch.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
@Getter
@Setter
// 被【ImportSelector】指过来的类不需要添加【@Component】
public class BranchBean {

    private String name;

    @PostConstruct
    public void init()
    {
        name = "BranchBeanName put in from @PostConstruct";
    }

}
