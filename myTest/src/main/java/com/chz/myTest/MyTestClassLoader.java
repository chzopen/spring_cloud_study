package com.chz.myTest;

public class MyTestClassLoader {

    public static void main(String[] args) {
        ClassLoader classLoader = MyTestClassLoader.class.getClassLoader();
        while(true){
            if( classLoader==null ){
                break;
            }
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        }
    }

}
