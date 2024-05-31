package com.chz.myApplication1.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RocketMQConsumerExample {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_group11");
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.setNamesrvAddr("192.168.44.228:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setPullBatchSize(1);
        consumer.subscribe("chz_test", "*");
        consumer.setMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                System.out.println(String.format("[%s]:list.size(): ", Thread.currentThread().getId(), list.size()));
                for (MessageExt msg : list) {
                    byte[] body = msg.getBody();
                    String message = new String(body, StandardCharsets.UTF_8);
                    System.out.println(String.format("[%s]:Received message[%s]: topic=%s, queue=%s, 当前时间:%s, 【%s】",
                            Thread.currentThread().getId(),
                            msg.getReconsumeTimes(),
                            msg.getTopic(),
                            msg.getQueueId(),
                            dateTimeFormatter.format(LocalDateTime.now()), message));
                    if( System.currentTimeMillis() % 5==0 ){
                        System.out.println(String.format("[%s]:SUCCESS", Thread.currentThread().getId()));
                        return ConsumeOrderlyStatus.SUCCESS;
                    } else {
                        System.out.println(String.format("[%s]:SUSPEND_CURRENT_QUEUE_A_MOMENT", Thread.currentThread().getId()));
                        return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                    }
                }

                if( System.currentTimeMillis() % 5==0 ){
                    System.out.println(String.format("[%s]:SUCCESS", Thread.currentThread().getId()));
                    return ConsumeOrderlyStatus.SUCCESS;
                } else {
                    System.out.println(String.format("[%s]:SUSPEND_CURRENT_QUEUE_A_MOMENT", Thread.currentThread().getId()));
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
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
