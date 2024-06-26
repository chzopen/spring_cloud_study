package com.chz.myMqttV3.demo6;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MyDemo6MqttClient1Test
{
    public static void main(String[] args) throws  MqttException {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("admin");
        options.setPassword("public".toCharArray());
        options.setCleanSession(true);
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(20);
        options.setKeepAliveInterval(10);

        MqttClient client = new MqttClient("tcp://192.168.44.228:1883", "MyDemo6MqttClient1Test", new MemoryPersistence());
        client.setCallback(new MyDemo6MqttCallback(client, options, new String[]{"device/#"}));
        client.connect(options);
    }
}
