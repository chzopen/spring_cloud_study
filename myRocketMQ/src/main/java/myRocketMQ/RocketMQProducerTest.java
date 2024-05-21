package myRocketMQ;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RocketMQProducerTest {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException {
        // 实例化一个生产者，并设置生产组名称
        DefaultMQProducer producer = new DefaultMQProducer("my-producer-group");
        producer.setSendMsgTimeout(15000);
        producer.setNamesrvAddr("192.168.44.228:9876");
        producer.start();

        for (int i = 0; ; i++) {

            JSONObject json = new JSONObject();
            json.put("index", i);
            json.put("sendTime", dateTimeFormatter.format(LocalDateTime.now()));

            // 创建一条消息，指定Topic、Tag和消息体
            Message msg = new Message("chz_test",
                    "tagA",
                    "key1",
                    json.toJSONString().getBytes(RemotingHelper.DEFAULT_CHARSET)
            );

            // 发送消息，并获取发送结果
            SendResult sendResult = producer.send(msg);
            System.out.println(String.format("%s, %s", new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET), sendResult));

            Thread.sleep(200L);
        }

        // 如果不再发送消息，关闭生产者
//        producer.shutdown();
    }
}