package myMqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.StandardCharsets;

public class MyMqttClient3Test {

    public static void main(String[] args) throws MqttException, InterruptedException
    {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("admin");
        options.setPassword("public".toCharArray());
        options.setCleanSession(true);
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(20);
        options.setKeepAliveInterval(10);

        MqttClient client = new MqttClient("tcp://192.168.44.230:1883", "MyMqttClient3Test", new MemoryPersistence());
        client.setCallback(new MyMqttCallback(client, options, new String[]{"aaa", "bbb", "ccc"}));
        client.connect(options);

        for( int i=0; ; i++ ){
            String topic = "ccc";
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setPayload("I am MyMqttClient3Test, at node [192.168.44.230:1883]".getBytes(StandardCharsets.UTF_8));
            client.publish(topic, mqttMessage);
            Thread.sleep(1000L);
        }
    }
}
