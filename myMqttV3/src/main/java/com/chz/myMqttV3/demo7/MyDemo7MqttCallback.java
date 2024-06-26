package com.chz.myMqttV3.demo7;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;

import java.util.Arrays;

@Slf4j
public class MyDemo7MqttCallback implements MqttCallbackExtended {

    private MqttClient client;
    private MqttConnectOptions options;
    private String[] topics;

    public MyDemo7MqttCallback(MqttClient client, MqttConnectOptions options, String[] topics)
    {
        this.client = client;
        this.options = options;
        this.topics = topics;
    }

    @SneakyThrows
    @Override
    public void connectionLost(Throwable throwable) {
        log.error("connectionLost", throwable);
        while (!client.isConnected()) {
            log.info("emqx重新连接....................................................");
            client.connect(options);
            Thread.sleep(1000);
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        log.info("messageArrived: topic={}, message={}", topic, new String(message.getPayload()));
    }

    @SneakyThrows
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        if( token!=null ){
            MqttMessage message = token.getMessage();
            String topic = token.getTopics()==null ? null : Arrays.asList(token.getTopics()).toString();
            String str = message==null ? null : new String(message.getPayload());
            log.info("deliveryComplete: topic={}, message={}", topic, str);
        } else {
            log.info("deliveryComplete: null");
        }
    }

    @SneakyThrows
    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        log.info("connectComplete: reconnect={}, serverURI={}", reconnect, serverURI);

        if( topics.length > 0 ){
            int[] qosArr = new int[topics.length];
            Arrays.fill(qosArr, 2);

            MyDemo7MqttMessageListener[] listeners = new MyDemo7MqttMessageListener[topics.length];
            Arrays.fill(listeners, new MyDemo7MqttMessageListener());

            client.subscribe(topics, qosArr, listeners);
        }
    }

}