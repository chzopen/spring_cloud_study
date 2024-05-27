package myNetty.demo2.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import myNetty.demo2.ConnectionsInBoundHandler;

public class MyServerChannelInitializer extends ChannelInitializer<SocketChannel>
{
    @Override
    public void initChannel(SocketChannel socketChannel) {
        socketChannel.pipeline().addLast(new ConnectionsInBoundHandler());
        socketChannel.pipeline().addLast(new MyServerChannelInBoundHandler1());
    }
}