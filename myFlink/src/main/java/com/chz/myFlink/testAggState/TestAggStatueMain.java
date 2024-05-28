package com.chz.myFlink.testAggState;

import com.alibaba.fastjson2.JSONObject;
import org.apache.flink.api.common.functions.*;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.*;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

public class TestAggStatueMain
{
    public static void main(String[] args) throws Exception
    {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(10);

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.44.228:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group-20240514-2");

        // 从kafka中读取数据
        DataStreamSource<String> dataStreamSource = env.addSource(new FlinkKafkaConsumer<>("my-topic2", new SimpleStringSchema(), properties));
        dataStreamSource
                .map(new MapFunction<String, SensorRecord>() {
                    @Override
                    public SensorRecord map(String value) throws Exception {
                        JSONObject json = new JSONObject();
                        SensorRecord sensor = new SensorRecord();
                        sensor.setId(System.currentTimeMillis()%2);
                        sensor.setNumber(2D);
                        return sensor;
                    }
                })
                .keyBy(SensorRecord::getId)
                .process(new MyKeyedProcessFunction())
                .print();

        env.execute();
    }
}
