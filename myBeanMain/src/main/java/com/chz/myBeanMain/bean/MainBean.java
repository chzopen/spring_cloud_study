package com.chz.myBeanMain.bean;

import com.chz.myBeanBranch.bean.BranchDeferredBean;
import com.chz.myBeanBranch.bean.BranchDeferredImportBean;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Getter
@Setter
@Component
public class MainBean {

    @Autowired(required = false)
    private NotAnnotatedBean notAnnotatedBean;

    @Autowired(required = false)
    private BranchDeferredBean branchBean;

    @Autowired(required = false)
    private BranchDeferredImportBean branchImportBean;

    public MainBean()
    {
        log.info("chz >>> MainBean.<init>()");
    }

    @PostConstruct
    public void init()
    {
        log.info("chz >>> MainBean.init()");
    }

}
