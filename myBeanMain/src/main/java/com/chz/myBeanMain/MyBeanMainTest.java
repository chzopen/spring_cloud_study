package com.chz.myBeanMain;

import com.chz.myBeanBranch.EnableBranchImportSelector;
import com.chz.myBeanMain.bean.NotAnnotatedBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(NotAnnotatedBean.class)         // 这里使用了@Import
@EnableBranchImportSelector             // 这里使用了@ImportSelector
@SpringBootApplication
public class MyBeanMainTest {

    public static void main(String[] args) {
        SpringApplication.run(MyBeanMainTest.class, args);
    }

}