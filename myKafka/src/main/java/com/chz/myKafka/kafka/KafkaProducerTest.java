package com.chz.myKafka.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import javax.security.auth.callback.Callback;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaProducerTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 设置配置属性
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.44.228:9092"); // Kafka集群地址
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());

        // 创建Kafka生产者对象
        Producer<String, String> producer = new KafkaProducer<>(props);

        try {
            // 发送消息
            for (int i = 0; i<10; i++) {
                ProducerRecord<String, String> record = new ProducerRecord<>("upload_from_filebeat", "key-" + i, "message-" + i);
                Future<RecordMetadata> future = producer.send(record);
                future.get();
                System.out.println("Message sent: " + record);
                Thread.sleep(1000L);
            }
        } finally {
            // 关闭生产者，确保消息被发送完毕
            producer.close();
        }
    }
}
