package com.chz.myBeanMain.bean;

import com.chz.myBeanBranch.bean.BranchBean;
import com.chz.myBeanBranch.importSelector.BranchImportBean;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class MainBean {

    @Autowired
    private NotAnnotatedBean notAnnotatedBean;

    @Autowired
    private BranchBean branchBean;

    @Autowired
    private BranchImportBean branchImportBean;

}
