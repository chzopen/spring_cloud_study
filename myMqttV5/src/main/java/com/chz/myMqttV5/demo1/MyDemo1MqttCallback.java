package com.chz.myMqttV5.demo1;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttCallback;
import org.eclipse.paho.mqttv5.client.MqttDisconnectResponse;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;

@Slf4j
public class MyDemo1MqttCallback implements MqttCallback
{
    private String clientId;

    public MyDemo1MqttCallback(String clientId)
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