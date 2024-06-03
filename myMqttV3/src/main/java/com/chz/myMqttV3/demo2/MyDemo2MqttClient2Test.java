package com.chz.myMqttV3.demo2;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MyDemo2MqttClient2Test {

    public static void main(String[] args) throws MqttException, InterruptedException
    {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("admin");
        options.setPassword("public".toCharArray());
        options.setCleanSession(true);
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(20);
        options.setKeepAliveInterval(10);

        MqttClient client = new MqttClient("tcp://192.168.44.229:1883", "MyDemo2MqttClient2Test", new MemoryPersistence());
        client.setCallback(new MyDemo2MqttCallback(client, options, new String[]{"device/#"}));
        client.connect(options);

    }
}
