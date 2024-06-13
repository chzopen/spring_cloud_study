package com.chz.myBeanBranch;

import com.chz.myBeanBranch.importSelector.BranchDeferredImportSelector;
import com.chz.myBeanBranch.importSelector.BranchCommonImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Import({
        BranchCommonImportSelector.class,                 // 这里使用了@ImportSelector
        BranchDeferredImportSelector.class          // 这里使用了@DeferredImportSelector
})
public @interface EnableBranchImportSelector {
}
