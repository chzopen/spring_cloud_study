package com.chz.myMqttV3.demo2;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.net.UnknownHostException;

public class MyDemo2MqttClient1Test
{
    public static void main(String[] args) throws UnknownHostException, MqttException, InterruptedException {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("admin");
        options.setPassword("public".toCharArray());
        options.setCleanSession(true);
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(20);
        options.setKeepAliveInterval(10);

        MqttClient client = new MqttClient("tcp://192.168.44.228:1883", "MyMqttClient1Test", new MemoryPersistence());
        client.setCallback(new MyDemo2MqttCallback(client, options, new String[]{"device/#"}));
        client.connect(options);

//        for (int i = 0; ; i++) {
//            String topic = "device1";
//            MqttMessage mqttMessage = new MqttMessage();
//            mqttMessage.setPayload("I am MyMqttClient1Test, at node [192.168.44.228:1883]".getBytes(StandardCharsets.UTF_8));
//            client.publish(topic, mqttMessage);
//            Thread.sleep(1000L);
//        }
    }
}