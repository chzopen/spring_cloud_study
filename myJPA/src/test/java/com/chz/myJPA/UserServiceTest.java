package com.chz.myJPA;

import com.chz.myJPA.component.MyPropertiesBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(MyJPATest.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private MyPropertiesBean myPropertiesBean;

    @Test
    public void testSomeMethod() {
        // 在这里编写测试逻辑
        myPropertiesBean.printMap();
    }

}