package com.chz.myMqttV3.demo7;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class MyDemo7MqttSenderTest
{
    public static void main(String[] args) throws UnknownHostException, MqttException, InterruptedException {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("admin");
        options.setPassword("public".toCharArray());
        options.setCleanSession(true);
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(20);
        options.setKeepAliveInterval(10);
        // 这里设置遗嘱消息
        options.setWill("device/1", "I am MyDemo7MqttSenderTest, I am dead!!!".getBytes(), 1, false);

        MqttClient client = new MqttClient("tcp://192.168.44.228:1883", "MyDemo7MqttSenderTest", new MemoryPersistence());
        client.setCallback(new MyDemo7MqttCallback(client, options, new String[]{}));
        client.connect(options);

        for( int i=0; true; i++ ){
            String topic = "device/1";
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setRetained(true);
            String msg = "I am MyDemo7MqttSenderTest, at node [192.168.44.228:1883]:" + i;
            mqttMessage.setPayload(msg.getBytes(StandardCharsets.UTF_8));
            client.publish(topic, mqttMessage);
            System.out.println("send: " + msg);
            Thread.sleep(1000L);
        }
    }
}
