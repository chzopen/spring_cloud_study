package myMqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.StandardCharsets;

public class MyMqttSenderTest {

    public static void main(String[] args) throws MqttException, InterruptedException
    {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("admin");
        options.setPassword("Chz12313213123".toCharArray());
        options.setCleanSession(true);
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(20);
        options.setKeepAliveInterval(10);

        MqttClient client = new MqttClient("tcp://192.168.44.228:1883", "MyMqttSenderTest", new MemoryPersistence());
        client.setCallback(new MyMqttCallback(client, options, "bbb"));
        client.connect(options);

        for( int i=0; ; i++ ){
            String topic = "aaa";
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setPayload("this is sent by MyMqttReceiverTest".getBytes(StandardCharsets.UTF_8));
            client.publish(topic, mqttMessage);
            Thread.sleep(1000L);
        }
    }
}
