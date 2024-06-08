package com.chz.myMqttV5.demo1;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.mqttv5.client.*;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;

@Slf4j
public class MyDemo1MqttV5Sender {

    public static void main(String[] args) throws InterruptedException {
        String broker = "tcp://192.168.44.228:1883";
        String clientId = "MyDemo1MqttV5Sender";
        int subQos = 1;
        int pubQos = 1;
        String msg;

        try {
            MqttClient client = new MqttClient(broker, clientId);
            MqttConnectionOptions options = new MqttConnectionOptions();
            client.setCallback(new MyMqttCallback(clientId));
            client.connect(options);
            client.subscribe("device/#", subQos);

            for(int i=0; i<100; i++){
                msg = "I am "+clientId+":" + i;
                MqttMessage message = new MqttMessage(msg.getBytes());
                message.setQos(pubQos);
                client.publish("device/2", message);
                Thread.sleep(3000L);
            }

            client.disconnect();
            client.close();

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
