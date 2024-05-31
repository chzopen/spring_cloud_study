package com.chz.myFlink;

import lombok.AllArgsConstructor;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class FlinkMain {

    @AllArgsConstructor
    public static class MyTreeSet
    {
        private Integer max;
        private TreeSet<Integer> treeSet;
    }


    public static void main(String[] args) throws Exception
    {
        //初始化环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(4);
        intList.add(3);
        intList.add(5);

        //接收的实体列表作为装入数据源
        DataStreamSource<Integer> intStream = env.fromCollection(intList);
        intStream
                .keyBy((KeySelector<Integer, Integer>) value -> 1)
                .process(new KeyedProcessFunction<Integer, Integer, Integer>()
                {
                    private ValueState<TreeSet<Integer>> recentIntListValueState;

                    @Override
                    public void open(Configuration parameters)
                    {
                        ValueStateDescriptor<TreeSet<Integer>> lastTempDescriptor = new ValueStateDescriptor<>("recentIntList", TypeInformation.of(new TypeHint<>(){}));
                        recentIntListValueState = getRuntimeContext().getState(lastTempDescriptor);
                    }

                    @Override
                    public void processElement(Integer value, KeyedProcessFunction<Integer, Integer, Integer>.Context ctx, Collector<Integer> out) throws Exception
                    {
                        System.out.println("current: " + value);
                        TreeSet<Integer> recentIntList = recentIntListValueState.value();
                        if( recentIntList==null ){
                            recentIntList = new TreeSet<>();
                            recentIntListValueState.update(recentIntList);
                        }
                        Integer previousMax = recentIntList.size()>0 ? recentIntList.last() : Integer.MIN_VALUE;
                        recentIntList.add(value);
                        if( recentIntList.size()>5 ){
                            recentIntList.pollFirst();
                        }
                        Integer newMax = recentIntList.last();
                        if( newMax.intValue() > previousMax.intValue() ) {
                            System.out.println("max : " + newMax);
                            out.collect(newMax);
                        }
                    }
                })
                .print();
        //脚本开始执行
        env.execute("Kafka Produce Job");
    }



}