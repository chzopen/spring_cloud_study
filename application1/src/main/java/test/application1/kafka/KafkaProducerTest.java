package test.application1.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

public class KafkaProducerTest {

    public static void main(String[] args) {
        // 设置配置属性
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.44.228:9092"); // Kafka集群地址
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());

        // 创建Kafka生产者对象
        Producer<String, String> producer = new KafkaProducer<>(props);

        // 发送消息
        for (int i = 0; i < 100; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", "key-" + i, "message-" + i);
            producer.send(record);
            Future<RecordMetadata> send = producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {

                }
            });
            System.out.println("Message sent: " + record);
        }

        // 关闭生产者，确保消息被发送完毕
        producer.close();
    }
}
