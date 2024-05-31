package com.chz.myApplication1.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RocketMQConsumerExample2 {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_group11");
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.setPullBatchSize(1);
        consumer.setNamesrvAddr("192.168.44.228:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("%RETRY%example_group11", "*");
        consumer.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt msg : list) {
                    byte[] body = msg.getBody();
                    String message = new String(body, StandardCharsets.UTF_8);
                    System.out.println(String.format("Received message[%s]: topic=%s, 当前时间:%s, 【%s】",
                            msg.getReconsumeTimes(),
                            msg.getTopic(),
                            dateTimeFormatter.format(LocalDateTime.now()), message));
                }
                System.out.println("CONSUME_SUCCESS");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // 启动Consumer
        consumer.start();
        System.out.println("Consumer started and ready to consume messages.");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            consumer.shutdown();
            System.out.println("Consumer has been shutdown.");
        }));
    }
}
