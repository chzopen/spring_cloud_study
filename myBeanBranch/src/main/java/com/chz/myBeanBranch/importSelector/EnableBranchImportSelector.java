package com.chz.myBeanBranch.importSelector;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Import(BranchImportSelector.class)
public @interface EnableBranchImportSelector {
}
