package myRocketMQ;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class RocketMQConsumerExample {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_group11");
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.setNamesrvAddr("192.168.44.228:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("chz_test", "*");
        consumer.setMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt msg : list) {
                    byte[] body = msg.getBody();
                    String message = new String(body, StandardCharsets.UTF_8);
                    System.out.printf("Received message: %s,%s, %s:%s%n", msg.getStoreHost(), msg.getQueueId(), msg.getKeys(), message);
                }
                return ConsumeOrderlyStatus.SUCCESS;
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
