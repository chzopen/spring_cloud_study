package myMqtt.encoder;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface MsgDecoder<T> {

    T decoder(MqttMessage msg);

}



