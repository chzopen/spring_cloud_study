package com.chz.mySpringBootTest;

import com.chz.mySpringBootTest.controller.TestController;
import com.chz.mySpringBootTest.service.TestService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class MySpringBootTestTest2 {

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

    @InjectMocks
    private TestController testController;

    @Mock
    private TestService testService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    //-------------------------------

    @Test
    public void test1()
    {
        System.out.println("test1------------------");
        String test = testController.test2();
        Assert.isTrue(StringUtils.equals(test, null), "test1 failed");
    }

    @Test
    public void test2()
    {
        System.out.println("test2------------------");

        // 模拟 userService 的行为
        when(testService.test()).thenReturn("12345");

        String test = testController.test2();
        Assert.isTrue(StringUtils.equals(test, "12345"), "test1 failed");
    }



}
