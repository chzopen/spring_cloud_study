package com.chz.myBeanBranch.importSelector;

import org.springframework.context.annotation.Bean;

// 被【ImportSelector】指过来的类不需要添加【@Configuration】
public class BranchImportConfiguration {

    @Bean
    public BranchImportBean branchImportBean() {
        BranchImportBean branchBean = new BranchImportBean();
        branchBean.setId(19);
        branchBean.setName("I am branchImportBean!!!");
        return branchBean;
    }

}
