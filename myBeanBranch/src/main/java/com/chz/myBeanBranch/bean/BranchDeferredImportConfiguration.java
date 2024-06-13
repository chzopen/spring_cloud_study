package com.chz.myBeanBranch.bean;

import com.chz.myBeanBranch.bean.BranchDeferredImportBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
// 被【ImportSelector】指过来的类不需要添加【@Configuration】
public class BranchDeferredImportConfiguration {

    public BranchDeferredImportConfiguration()
    {
        log.info("chz >>> BranchDeferredImportConfiguration.<init>()");
    }

    @Bean
    public BranchDeferredImportBean branchDeferredImportBean()
    {
        log.info("chz >>> BranchDeferredImportConfiguration.branchImportBean()");
        BranchDeferredImportBean branchDeferredImportBean = new BranchDeferredImportBean();
        branchDeferredImportBean.setId(19);
        branchDeferredImportBean.setName("I am BranchDeferredImportBean");
        return branchDeferredImportBean;
    }

}
