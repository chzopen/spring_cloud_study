package com.chz.myBeanMain.controller;

import com.chz.myBeanMain.bean.MainBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired(required = false)
    private MainBean mainBean;

    @GetMapping("/notAnnotatedBean")
    public String notAnnotatedBean() {
        return "notAnnotatedBean: " + mainBean.getNotAnnotatedBean().getName();
    }

    @GetMapping("/branchBean")
    public String branchBean() {
        return "branchBean: " + mainBean.getBranchBean().getName();
    }

    @GetMapping("/branchImportBean")
    public String branchImportBean() {
        return "branchImportBean: " + mainBean.getBranchImportBean().getName();
    }

}