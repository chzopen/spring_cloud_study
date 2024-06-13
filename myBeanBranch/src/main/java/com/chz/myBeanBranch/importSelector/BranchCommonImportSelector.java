package com.chz.myBeanBranch.importSelector;

import com.chz.myBeanBranch.bean.BranchCommonBean;
import com.chz.myBeanBranch.bean.BranchCommonImportConfiguration;
import com.chz.myBeanBranch.controller.BranchCommonController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

@Slf4j
public class BranchCommonImportSelector implements ImportSelector
{
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata)
    {
        log.info("chz >>> BranchCommonImportSelector.selectImports()");
        // 这里想让哪个类变成bean就在返回值里面返回，所以ImportSelector可以用来做为动态添加bean的一个方法，
        // 比如扫描某一个自定义注解，然后返回类名，变成bean
        return new String[]{
                BranchCommonBean.class.getName(),
                BranchCommonController.class.getName(),
                BranchCommonImportConfiguration.class.getName(),
        };
    }
}
