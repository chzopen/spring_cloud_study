package myMqtt.test;

import myMqtt.callback.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class MyMqttReceiverTest
{
    public static void main(String[] args) throws UnknownHostException, MqttException, InterruptedException {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("admin");
        options.setPassword("Chz12313213123".toCharArray());
        options.setCleanSession(true);
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(20);
        options.setKeepAliveInterval(10);

        MqttClient client = new MqttClient("tcp://192.168.44.228:1883", "MyMqttReceiverTest", new MemoryPersistence());
        client.setCallback(new MyMqttCallback(client, options, "aaa"));
        client.connect(options);

        for (int i = 0; ; i++) {
            String topic = "bbb";
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setPayload("this is sent by MyMqttReceiverTest".getBytes(StandardCharsets.UTF_8));
            client.publish(topic, mqttMessage);
            Thread.sleep(1000L);
        }
    }
}
