package com.chz.myTest;

import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class MyTestMain {

    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        System.out.println(treeSet.last());
        treeSet.add(2);
        treeSet.add(3);
        treeSet.add(1);
        treeSet.add(4);
        treeSet.add(10);
        System.out.println(treeSet);
        treeSet.pollFirst();
        System.out.println(treeSet);
        treeSet.add(7);
        System.out.println(treeSet);
        System.out.println(treeSet.last());
    }
}
