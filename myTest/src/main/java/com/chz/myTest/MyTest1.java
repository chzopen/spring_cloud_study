package com.chz.myTest;

public class MyTest1 {

    private MyTest1()
    {

    }

    private static class SingletonHolder
    {
        private static final MyTest1 singleton = new MyTest1();
    }

    public static MyTest1 getInstance()
    {
        return SingletonHolder.singleton;
    }

    public static void main(String args[]) {
        MyTest1 singleton = SingletonHolder.singleton;
    }

}

