package com.chz.myBeanBranch.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
@Getter
@Setter
// 被【ImportSelector】指过来的类不需要添加【@Component】
public class BranchDeferredBean {

    private String name;

    public BranchDeferredBean()
    {
        log.info("chz >>> BranchDeferredBean.<init>()");
    }

    @PostConstruct
    public void init()
    {
        log.info("chz >>> BranchDeferredBean.init()");
        name = "【BranchDeferredBean put in from @PostConstruct】";
    }

}
