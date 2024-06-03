package com.chz.myMqttV3.demo2;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.nio.charset.StandardCharsets;

public class MyDemo2MqttSenderTest {

        public static void main(String[] args) throws MqttException, InterruptedException
        {
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName("admin");
            options.setPassword("public".toCharArray());
            options.setCleanSession(true);
            options.setAutomaticReconnect(true);
            options.setConnectionTimeout(20);
        options.setKeepAliveInterval(10);

        MqttClient client = new MqttClient("tcp://192.168.44.230:1883", "MyDemo2MqttSenderTest", new MemoryPersistence());
        client.setCallback(new MyDemo2MqttCallback(client, options, new String[]{"device/#"}));
        client.connect(options);

        for( int i=0; ; i++ ){
            String topic = "device/1";
            MqttMessage mqttMessage = new MqttMessage();
            String msg = "I am MyMqttClient3Test, at node [192.168.44.230:1883]: " + i;
            mqttMessage.setPayload(msg.getBytes(StandardCharsets.UTF_8));
            client.publish(topic, mqttMessage);
            Thread.sleep(10000L);
        }
    }
}
