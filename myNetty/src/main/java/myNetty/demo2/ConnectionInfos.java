package myNetty.demo2;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

public class ConnectionInfos
{
    private static ConcurrentHashMap<Channel, ConnectionInfo> connections = new ConcurrentHashMap<>();

    public static ConnectionInfo put(Channel channel, ConnectionInfo connectionInfo) {
        return connections.put(channel, connectionInfo);
    }

    public static ConnectionInfo get(Channel channel) {
        return connections.get(channel);
    }

    public static ConnectionInfo remove(Channel channel) {
        return connections.remove(channel);
    }

    public static int size() {
        return connections.size();
    }
}
