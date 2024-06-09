package com.chz.myMqttV5.demo1;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;

@Slf4j
public class MyDemo1MqttV5Client {

    private static String clientId = MyDemo1MqttV5Client.class.getSimpleName();

    public static void main(String[] args) throws InterruptedException {
        String broker = "tcp://192.168.44.228:1883";
        int subQos = 1;
        int pubQos = 1;
        String msg;

        try {
            MqttClient client = new MqttClient(broker, clientId);
            MqttConnectionOptions options = new MqttConnectionOptions();
            client.setCallback(new MyDemo1MqttCallback(clientId));
            client.connect(options);
            client.subscribe("device/#", subQos);

            for(int i=0; i<100; i++){
                msg = "I am "+clientId+":" + i;
                MqttMessage message = new MqttMessage(msg.getBytes());
                message.setQos(pubQos);
                client.publish("device/1", message);
                Thread.sleep(3000L);
            }

            client.disconnect();
            client.close();

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


}
