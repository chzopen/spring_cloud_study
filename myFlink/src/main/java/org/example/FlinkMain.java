//package org.example;
//
//import org.apache.flink.connector.base.DeliveryGuarantee;
//import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
//import org.apache.flink.connector.kafka.sink.KafkaSink;
//import org.apache.flink.formats.json.JsonSerializationSchema;
//import org.apache.flink.streaming.api.datastream.DataStreamSource;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//import org.example.entity.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class FlinkMain {
//    public static void main(String[] args) throws Exception {
//        //初始化环境
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//
//        Test test = new Test();
//        test.setAa("111");
//        test.setBb("222");
//        test.setCc("333");
//        List<Test> testList = new ArrayList<>();
//        testList.add(test);
//
//        //定义Json序列化结构
//        JsonSerializationSchema<Test> jsonFormat = new JsonSerializationSchema<>();
//        //接收的实体列表作为装入数据源
//        DataStreamSource<Test> stream = env.fromCollection(testList);
//        //构建推送kafka的管道结构
//        KafkaSink<Test> sink = KafkaSink.<Test>builder()
//                .setBootstrapServers("192.168.44.228:9092")
//                .setRecordSerializer(KafkaRecordSerializationSchema.builder()
//                        .setTopic("my-topic")
//                        .setValueSerializationSchema(jsonFormat)
//                        .build()
//                )
//                .setDeliveryGuarantee(DeliveryGuarantee.AT_LEAST_ONCE)
//                .build();
//        System.out.println("推送kafka消息开始");
//        stream.print();
//        //接入推送管道
//        stream.sinkTo(sink);
//        //脚本开始执行
//        env.execute("Kafka Produce Job");
//        System.out.println("推送kafka的消息结束");
//    }
//}