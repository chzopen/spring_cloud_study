package com.chz.myFlink.test3ReducingState;

import org.apache.flink.api.common.functions.ReduceFunction;

public class MyMaxReducingFunction implements ReduceFunction<SensorRecord> {

    @Override
    public SensorRecord reduce(SensorRecord value1, SensorRecord value2) throws Exception {
        return value1.getNumber() >= value2.getNumber() ? value1 : value2;
    }
}