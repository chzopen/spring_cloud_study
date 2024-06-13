package com.chz.myBeanBranch.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
// 被【ImportSelector】指过来的类不需要添加【@Configuration】
public class BranchCommonImportConfiguration {

    public BranchCommonImportConfiguration()
    {
        log.info("chz >>> BranchCommonImportConfiguration.<init>()");
    }

    @Bean
    public BranchCommonImportBean branchImportBean()
    {
        log.info("chz >>> BranchCommonImportConfiguration.branchImportBean()");
        BranchCommonImportBean branchBean = new BranchCommonImportBean();
        branchBean.setId(19);
        branchBean.setName("I am BranchCommonImportConfiguration!!!");
        return branchBean;
    }

}
