package org.example.testAggState;

import org.apache.flink.api.common.state.AggregatingState;
import org.apache.flink.api.common.state.AggregatingStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

public class MyKeyedProcessFunction extends KeyedProcessFunction<Long, SensorRecord, Tuple2<Long, Double>>
{
    private transient AggregatingState aggregatingState;

    @Override
    public void open(Configuration parameters) throws Exception
    {
        AggregatingStateDescriptor aggregatingStateDescriptor = new AggregatingStateDescriptor(
                "myAvgAggregateFunction",
                new MyAvgAggregateFunction(),
                TypeInformation.of(new TypeHint<Tuple2<Double, Integer>>(){})
        );
        aggregatingState = getRuntimeContext().getAggregatingState(aggregatingStateDescriptor);
    }

    @Override
    public void processElement(SensorRecord value, Context ctx, Collector<Tuple2<Long, Double>> out) throws Exception
    {
        aggregatingState.add(value);
        out.collect(Tuple2.of(value.getId(), (Double) aggregatingState.get()) );
    }
}