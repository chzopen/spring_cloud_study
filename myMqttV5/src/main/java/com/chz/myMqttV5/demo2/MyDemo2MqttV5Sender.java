package com.chz.myMqttV5.demo2;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;

@Slf4j
public class MyDemo2MqttV5Sender {

    private static String clientId = MyDemo2MqttV5Sender.class.getSimpleName();

    public static void main(String[] args) throws InterruptedException {
        String broker = "tcp://192.168.44.228:1883";
        int subQos = 1;
        int pubQos = 1;
        String msg;

        try {
            MqttClient client = new MqttClient(broker, clientId);
            MqttConnectionOptions options = new MqttConnectionOptions();
            client.setCallback(new MyDemo2MqttCallback(clientId));
            client.connect(options);
            client.subscribe("device/#", subQos);

            for(int i=0; i<10000; i++){
                msg = "I am "+clientId+":" + i + "\r\n";
                MqttMessage message = new MqttMessage(msg.getBytes());
                message.setQos(pubQos);
                client.publish("server/1", message);
                Thread.sleep(3000L);
            }

            client.disconnect();
            client.close();

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
