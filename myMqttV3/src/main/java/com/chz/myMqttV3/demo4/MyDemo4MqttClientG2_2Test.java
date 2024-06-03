package com.chz.myMqttV3.demo4;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.net.UnknownHostException;

public class MyDemo4MqttClientG2_2Test
{
    public static void main(String[] args) throws UnknownHostException, MqttException, InterruptedException {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("admin");
        options.setPassword("public".toCharArray());
        options.setCleanSession(true);
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(20);
        options.setKeepAliveInterval(10);

        MqttClient client = new MqttClient("tcp://192.168.44.228:1883", "MyDemo4MqttClientG2_2Test", new MemoryPersistence());
        client.setCallback(new MyDemo4MqttCallback(client, options, new String[]{"$share/g2/device/#"}));
        client.connect(options);
    }
}
