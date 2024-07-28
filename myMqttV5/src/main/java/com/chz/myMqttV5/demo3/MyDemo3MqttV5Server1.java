package com.chz.myMqttV5.demo3;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.mqttv5.client.*;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class MyDemo3MqttV5Server1
{

    public static void main(String[] args) throws InterruptedException
    {
        String broker = "tcp://192.168.44.228:1883";
        String clientId = "MyDemo3MqttV5Server1";
        int subQos = 1;
        try {
            MqttClient client = new MqttClient(broker, clientId);
            MqttConnectionOptions options = new MqttConnectionOptions();
            options.setAutomaticReconnect(true);
            client.setCallback(new MyDemo3Server1Callback(clientId));
            client.connect(options);
            client.subscribe("$share/demo3/device/#", subQos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Slf4j
    public static class MyDemo3Server1Callback implements MqttCallback
    {
        private String clientId;

        public MyDemo3Server1Callback(String clientId)
        {
            this.clientId = clientId;
        }

        public void connectComplete(boolean reconnect, String serverURI) {
            log.info("{}::connectComplete, reconnect={}, serverURI={}", clientId, reconnect, serverURI);
        }

        public void disconnected(MqttDisconnectResponse disconnectResponse) {
            log.info("{}::disconnected, disconnectResponse={}", clientId, disconnectResponse.getReasonString());
        }

        public void deliveryComplete(IMqttToken token) {
            log.info("{}::deliveryComplete, disconnectResponse={}", clientId, token.isComplete());
        }

        public void messageArrived(String topic, MqttMessage message) throws Exception {
            log.info("{}::messageArrived, topic={}, qos={}, message={}", clientId, topic, message.getQos(), new String(message.getPayload()));
        }

        public void mqttErrorOccurred(MqttException exception) {
            log.info("{}::mqttErrorOccurred, disconnectResponse={}", clientId, exception.getMessage());
        }

        public void authPacketArrived(int reasonCode, MqttProperties properties) {
            log.info("{}::authPacketArrived, reasonCode={}", clientId, reasonCode);
        }
    }

}
