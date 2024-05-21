package org.example.testReducingState;

import org.apache.flink.api.common.functions.ReduceFunction;
import org.example.testAggState.SensorRecord;

public class MyMaxReducingFunction implements ReduceFunction<SensorRecord> {

    @Override
    public SensorRecord reduce(SensorRecord value1, SensorRecord value2) throws Exception {
        return value1.getNumber() >= value2.getNumber() ? value1 : value2;
    }
}