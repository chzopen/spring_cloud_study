package com.chz.myBeanBranch.bean;

import lombok.Getter;
import lombok.Setter;

// 被【@Bean】指过来的类不需要添加【@Component】
@Getter
@Setter
public class BranchDeferredImportBean {

    private Integer id = 1;
    private String name = "BranchDeferredImportBean";

}
