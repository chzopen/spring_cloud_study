package com.chz.mySpringBootTest;

import com.chz.mySpringBootTest.controller.TestController;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class MySpringBootTestTest {

    @BeforeAll
    public void beforeAll()
    {
        System.out.println("beforeAll------------------");
    }

    @BeforeEach
    public void beforeEach()
    {
        System.out.println("beforeEach------------------");
    }

    @AfterAll
    public void afterAll()
    {
        System.out.println("afterAll------------------");
    }

    @AfterEach
    public void afterEach()
    {
        System.out.println("afterEach------------------");
    }

    //----------------------------

    @Autowired
    private TestController testController;

    //-------------------------------

    @Test
    public void test1()
    {
        System.out.println("test1------------------");
        String test = testController.test();
        Assert.isTrue(StringUtils.equals(test, "TestController:test()"), "test1 failed");
    }

    @Test
    public void test2()
    {
        System.out.println("test2------------------");
        String test = testController.test2();
        Assert.isTrue(StringUtils.equals(test, "TestServiceImpl:test()"), "test2 failed");
    }

}
