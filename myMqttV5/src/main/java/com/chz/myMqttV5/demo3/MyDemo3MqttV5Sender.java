package com.chz.myMqttV5.demo3;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.mqttv5.client.*;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;

@Slf4j
public class MyDemo3MqttV5Sender {

    private static String clientId = MyDemo3MqttV5Sender.class.getSimpleName();

    public static void main(String[] args) throws InterruptedException {
        String broker = "tcp://192.168.44.228:1883";
        int subQos = 1;
        int pubQos = 1;
        String msg;

        try {
            MqttClient client = new MqttClient(broker, clientId);
            MqttConnectionOptions options = new MqttConnectionOptions();
            client.setCallback(new MyDemo3SenderCallback(clientId));
            client.connect(options);
            client.subscribe("device/#", subQos);

            for(int i=0; i<200; i++){
                int id = i;
                msg = id+"";
                MqttMessage message = new MqttMessage(msg.getBytes());
                message.setId(id);
                message.setQos(pubQos);
                client.publish("device/1", message);
                Thread.sleep(1L);
            }

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Slf4j
    public static class MyDemo3SenderCallback implements MqttCallback
    {
        private String clientId;

        public MyDemo3SenderCallback(String clientId)
        {
            this.clientId = clientId;
        }

        public void connectComplete(boolean reconnect, String serverURI) {
            log.info("{}::MyMqttCallback, reconnect={}, serverURI={}", clientId, reconnect, serverURI);
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
