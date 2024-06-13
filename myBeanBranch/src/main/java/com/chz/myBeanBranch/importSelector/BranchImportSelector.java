package com.chz.myBeanBranch.importSelector;

import com.chz.myBeanBranch.bean.BranchBean;
import com.chz.myBeanBranch.controller.BranchController;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class BranchImportSelector implements ImportSelector
{
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata)
    {
        // 这里想让哪个类变成bean就在返回值里面返回，所以ImportSelector可以用来做为动态添加bean的一个方法，
        // 比如扫描某一个自定义注解，然后返回类名，变成bean
        return new String[]{
                BranchImportConfiguration.class.getName(),
                BranchController.class.getName(),
                BranchBean.class.getName()
        };
    }
}
