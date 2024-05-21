package myMqtt;

import myMqtt.annotation.Topic;
import myMqtt.encoder.SuperConsumer;
import myMqtt.enums.Pattern;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author jie
 */
@Topic(topic = "device/+/test",patten = Pattern.SHARE)
public class MqttTest extends SuperConsumer<String> {

    @Override
    protected void msgHandler(String topic, String entity) {

    }

    @Override
    public String decoder(MqttMessage msg) {
        return "";
    }

}

