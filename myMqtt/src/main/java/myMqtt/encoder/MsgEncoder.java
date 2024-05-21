package myMqtt.encoder;

public interface MsgEncoder<T> {

    String encoder(T t);

}