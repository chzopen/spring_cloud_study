package test.application1.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

public class RocketMQProducerTest {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException {
        // 实例化一个生产者，并设置生产组名称
        DefaultMQProducer producer = new DefaultMQProducer("my-producer-group");
        producer.setSendMsgTimeout(5000);
        producer.setNamesrvAddr("192.168.44.228:9876");
        producer.start();

        for (int i = 0; i < 100; i++) {
            // 创建一条消息，指定Topic、Tag和消息体
            Message msg = new Message("chz_test",  "tagA", "key1", ("Hello RocketMQ " + 2).getBytes(RemotingHelper.DEFAULT_CHARSET)); // 消息内容

            // 发送消息，并获取发送结果
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }

        // 如果不再发送消息，关闭生产者
        producer.shutdown();
    }
}