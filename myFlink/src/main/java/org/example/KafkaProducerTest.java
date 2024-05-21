package org.example;

import com.alibaba.fastjson2.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

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
            for (long i = System.currentTimeMillis(); ; i++) {
                JSONObject json = new JSONObject();
                json.put("id", i);
                json.put("group", i%5);

                ProducerRecord<String, String> record = new ProducerRecord<>("my-topic2", "key-" + i, json.toJSONString());
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
