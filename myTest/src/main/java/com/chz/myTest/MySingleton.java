package com.chz.myTest;

public class MySingleton {

    private MySingleton(){
        System.out.println("MySingleton.<init>()");
    }

    public static void print(){
        System.out.println("MySingleton.print");
    }

    public static class MySingletonHolder
    {
        private static MySingleton singleton = new MySingleton();
    }

    public static MySingleton singleton(){
        return MySingletonHolder.singleton;
    }

    public void instancePrint()
    {
        System.out.println("MySingleton.instancePrint");
    }

}
