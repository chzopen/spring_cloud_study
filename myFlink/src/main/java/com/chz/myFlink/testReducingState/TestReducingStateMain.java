//package com.chz.myFlink.testReducingState;
//
//import com.alibaba.fastjson2.JSONObject;
//import org.apache.flink.api.common.functions.MapFunction;
//import org.apache.flink.api.common.serialization.SimpleStringSchema;
//import org.apache.flink.api.common.state.ReducingState;
//import org.apache.flink.api.common.state.ReducingStateDescriptor;
//import org.apache.flink.configuration.Configuration;
//import org.apache.flink.streaming.api.datastream.DataStreamSource;
//import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
//import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
//import org.apache.flink.util.Collector;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//
//import java.util.Properties;
//
//public class TestReducingStateMain
//{
//
//    public static void main(String[] args) throws Exception
//    {
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        env.setParallelism(10);
//
//        Properties properties = new Properties();
//        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.44.228:9092");
//        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group-20240514-2");
//
//        DataStreamSource<String> dataStreamSource = env.addSource(new FlinkKafkaConsumer<>("my-topic2", new SimpleStringSchema(), properties));
//        SingleOutputStreamOperator<SensorRecord> dataStream = dataStreamSource
//                .map(new MapFunction<String, SensorRecord>() {
//                    @Override
//                    public SensorRecord map(String value) throws Exception {
//                        JSONObject json = new JSONObject();
//                        SensorRecord sensor = new SensorRecord();
//                        sensor.setId(System.currentTimeMillis()%2);
//                        sensor.setNumber(2D);
//                        return sensor;
//                    }
//                });
//
//        dataStream
//                .keyBy(SensorRecord::getId)
//                .process(new KeyedProcessFunction<Long, SensorRecord, SensorRecord>() {
//
//                    private ReducingState<SensorRecord> reducingState;
//
//                    @Override
//                    public void open(Configuration parameters) throws Exception {
//                        ReducingStateDescriptor<SensorRecord> reducingStateDescriptor = new ReducingStateDescriptor<>(
//                                "myMaxReducingFunction",
//                                new MyMaxReducingFunction(),
//                                SensorRecord.class);
//                        reducingState = getRuntimeContext().getReducingState(reducingStateDescriptor);
//                    }
//
//                    @Override
//                    public void processElement(SensorRecord value, KeyedProcessFunction<Long, SensorRecord, SensorRecord>.Context ctx, Collector<SensorRecord> out) throws Exception
//                    {
//                        reducingState.add(value);
//                        out.collect(reducingState.get());
//                    }
//                })
//                .print();
//
//        env.execute();
//    }
//}