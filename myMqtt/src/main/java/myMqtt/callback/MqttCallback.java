package myMqtt.callback;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;

import java.util.Arrays;

@Slf4j
public class MqttCallback implements MqttCallbackExtended {

    private MqttClient client;
    private MqttConnectOptions options;

    public MqttCallback(MqttClient client, MqttConnectOptions options)
    {
        this.client = client;
        this.options = options;
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
        MqttMessage message = token.getMessage();
        log.info("deliveryComplete: topic={}, message={}", Arrays.asList(token.getTopics()), new String(message.getPayload()));
    }

    @SneakyThrows
    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        log.info("connectComplete: reconnect={}, serverURI={}", reconnect, serverURI);
        client.subscribe("aaa", 2, new MyIMqttMessageListener());
    }

    public static class MyIMqttMessageListener implements IMqttMessageListener
    {
        @Override
        public void messageArrived(String topic, MqttMessage message) {
            log.info("messageArrived: topic={}, message={}", topic, new String(message.getPayload()));
        }
    }

}