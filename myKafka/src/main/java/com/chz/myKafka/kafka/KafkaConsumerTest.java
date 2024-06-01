package com.chz.myKafka.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerTest {
    public static void main(String[] args) {
        // 配置消费者属性
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.44.228:9092"); // Kafka集群地址
        props.put("group.id", "test-group"); // 消费者组ID
        props.put("enable.auto.commit", "true"); // 自动提交偏移量
        props.put("auto.commit.interval.ms", "1000"); // 自动提交间隔
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // 创建Kafka消费者实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // 订阅主题
        consumer.subscribe(Arrays.asList("my-topic"));

        // 消费消息循环
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100)); // 每次拉取消息等待时间
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                    // 在这里处理消息逻辑
                }
            }
        } finally {
            consumer.close(); // 关闭消费者
        }
    }
}
