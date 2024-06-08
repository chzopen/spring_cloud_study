package com.chz.myMqttV3.demo7;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

@Slf4j
public class MyDemo7MqttMessageListener implements IMqttMessageListener
{
    @Override
    public void messageArrived(String topic, MqttMessage message) {
        log.info("messageArrived: topic={}, message={}", topic, new String(message.getPayload()));
    }
}