package org.example.test1;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.apache.flink.api.common.accumulators.Accumulator;
import org.apache.flink.api.common.accumulators.IntCounter;
import org.apache.flink.api.common.functions.*;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.state.AggregatingState;
import org.apache.flink.api.common.state.AggregatingStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.*;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.testAggState.MyAvgAggregateFunction;

import java.io.Serializable;
import java.util.Properties;

public class FlinkFromKafka {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(10);

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.44.228:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group-20240514-2");

        // 从kafka中读取数据
        DataStreamSource<String> dataStreamSource = env.addSource(new FlinkKafkaConsumer<String>("my-topic2", new SimpleStringSchema(), properties));
        SingleOutputStreamOperator<JSONObject> singleOutputStreamOperator = dataStreamSource
                .map(new MapFunction<String, JSONObject>() {
                    @Override
                    public JSONObject map(String value) throws Exception {
                        System.out.println("map: " + value);
                        JSONObject jsonObject = JSON.parseObject(value);
                        return jsonObject;
                    }
                });
        KeyedStream<JSONObject, Integer> keyedStream = singleOutputStreamOperator.keyBy(new KeySelector<JSONObject, Integer>() {
            @Override
            public Integer getKey(JSONObject value) throws Exception {
                return value.getIntValue("group");
            }
        });

        keyedStream.flatMap(new RichFlatMapFunction<JSONObject, JSONObject>() {

            AggregatingState aggregatingState = null;

            @Override
            public void open(Configuration parameters) throws Exception {
                AggregatingStateDescriptor aggregatingStateDescriptor = new AggregatingStateDescriptor(
                        "avg-temp",
                        new MyAvgAggregateFunction(),
                        TypeInformation.of(new TypeHint<Tuple2<Double, Integer>>(){})
                );
                aggregatingState = getRuntimeContext().getAggregatingState(aggregatingStateDescriptor);
            }

            @Override
            public void flatMap(JSONObject value, Collector<JSONObject> out) throws Exception {

            }
        });

        SingleOutputStreamOperator<Object> group = keyedStream.process(new KeyedProcessFunction<Integer, JSONObject, Object>() {
            @Override
            public void processElement(JSONObject value,
                                       KeyedProcessFunction<Integer, JSONObject, Object>.Context ctx,
                                       Collector<Object> out) throws Exception {
                String group = value.getString("group");
                Accumulator<Object, Serializable> accumulator = getRuntimeContext().getAccumulator(group);
                if (accumulator == null) {
                    getRuntimeContext().addAccumulator(group, new IntCounter());
                    accumulator = getRuntimeContext().getAccumulator(group);
                }
                accumulator.add(1);
                out.collect(new Tuple2<>(group, accumulator.getLocalValue()));
            }
        });

        group.print("chz<flink>::");
//        stream.addSink(new FlinkKafkaProducer<String>("my-topic-sink", new SimpleStringSchema(), properties));
        env.execute();

    }
}
