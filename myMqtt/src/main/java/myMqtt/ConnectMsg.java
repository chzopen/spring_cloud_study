package myMqtt;

import com.alibaba.fastjson2.JSON;
import myMqtt.annotation.Topic;
import myMqtt.encoder.SuperConsumer;
import myMqtt.entity.Connect;
import myMqtt.enums.Pattern;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author jie
 */
@Topic(topic = "$SYS/brokers/+/clients/+/connected", patten = Pattern.SHARE)
public class ConnectMsg extends SuperConsumer<Connect> {

    @Override
    protected void msgHandler(String topic, Connect entity) {
        //接下来就是你自己的操作了
        //TODO 业务操作
    }

    @Override
    public Connect decoder(MqttMessage msg) {
        return JSON.parseObject(new String(msg.getPayload()), Connect.class);
    }

}
