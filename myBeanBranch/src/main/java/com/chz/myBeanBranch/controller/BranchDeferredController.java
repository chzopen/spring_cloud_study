package com.chz.myBeanBranch.controller;

import com.chz.myBeanBranch.bean.BranchCommonBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


// 被【ImportSelector】指过来的类不需要添加【@RestController】，但要加【@ResponseBody】
@Slf4j
@ResponseBody
@RequestMapping("/branch/deferred")
public class BranchDeferredController {

    @Autowired
    private BranchCommonBean branchBean;

    public BranchDeferredController()
    {
        log.info("chz >>> BranchDeferredController.<init>()");
    }

    @GetMapping("/test1")
    public String test1() {
        return "test1: " + branchBean.getName();
    }

}