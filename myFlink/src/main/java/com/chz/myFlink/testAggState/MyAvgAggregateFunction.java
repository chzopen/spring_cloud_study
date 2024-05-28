package com.chz.myFlink.testAggState;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.java.tuple.Tuple2;

public class MyAvgAggregateFunction implements AggregateFunction<SensorRecord, Tuple2<Double, Integer>, Double>
{
    @Override
    public Tuple2<Double, Integer> createAccumulator() {
        return Tuple2.of(0.0, 0);
    }

    @Override
    public Tuple2<Double, Integer> add(SensorRecord value, Tuple2<Double, Integer> accumulator) {
        System.out.println(String.format("MyAvgAggregateFunction.add() >>> value[id=%s, number=%s], accumulator[f0=%s, f1=%s]", value.getId(), value.getNumber(), accumulator.f0, accumulator.f1));
        Double currentTotal = accumulator.f0;
        Integer currentCount = accumulator.f1;
        return new Tuple2<>(currentTotal + value.getNumber(), currentCount + 1);
    }

    @Override
    public Double getResult(Tuple2<Double, Integer> accumulator) {
        Double result = accumulator.f0 / accumulator.f1;
        System.out.println(String.format("MyAvgAggregateFunction.getResult() >>>accumulator[f0=%s, f1=%s], result=%s", accumulator.f0, accumulator.f1, result));
        return result;
    }

    @Override
    public Tuple2<Double, Integer> merge(Tuple2<Double, Integer> a, Tuple2<Double, Integer> b) {
        Double total = a.f0 + b.f0;
        Integer count = a.f1 + b.f1;
        Tuple2<Double, Integer> result = new Tuple2<>(total, count);
        System.out.println(String.format("MyAvgAggregateFunction.merge() >>> a[f0=%s, f1=%s], b[f0=%s, f1=%s], result=[f0=%s, f1=%s]",
                a.f0, a.f1,
                b.f0, b.f1,
                result.f0, result.f1));
        return result;
    }
}