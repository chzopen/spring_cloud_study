package com.chz.myMqttV3.demo5;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MyDemo5MqttClient1Test
{
    public static void main(String[] args) throws  MqttException {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("admin");
        options.setPassword("public".toCharArray());
        options.setCleanSession(true);
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(20);
        options.setKeepAliveInterval(10);

        MqttClient client = new MqttClient("tcp://192.168.44.228:1883", "MyDemo5MqttClient1Test", new MemoryPersistence());
        client.setCallback(new MyDemo5MqttCallback(client, options, new String[]{"device/1"}));
        client.connect(options);
    }
}
