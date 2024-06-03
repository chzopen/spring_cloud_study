package com.chz.myMqttV3.demo4;

import com.chz.myMqttV3.demo3.MyDemo3MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MyDemo4MqttClientG1_1Test
{
    public static void main(String[] args) throws  MqttException {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("admin");
        options.setPassword("public".toCharArray());
        options.setCleanSession(true);
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(20);
        options.setKeepAliveInterval(10);

        MqttClient client = new MqttClient("tcp://192.168.44.228:1883", "MyDemo4MqttClientG1", new MemoryPersistence());
        client.setCallback(new MyDemo4MqttCallback(client, options, new String[]{"$share/g1/device/#"}));
        client.connect(options);
    }
}
