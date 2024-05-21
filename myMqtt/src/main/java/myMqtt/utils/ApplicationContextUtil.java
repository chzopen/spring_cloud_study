package myMqtt.utils;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil {

    private static ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

}
