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
        return new String[]{
                BranchImportConfiguration.class.getName(),
                BranchController.class.getName(),
                BranchBean.class.getName()
        };
    }
}
