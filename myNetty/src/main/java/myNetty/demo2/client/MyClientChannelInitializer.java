package myNetty.demo2.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import myNetty.demo2.ConnectionsInBoundHandler;

public class MyClientChannelInitializer extends ChannelInitializer<SocketChannel>
{
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new StringDecoder()); // 添加字符串解码器
        socketChannel.pipeline().addLast(new StringEncoder()); // 添加字符串编码器
        socketChannel.pipeline().addLast(new MyClientInBoundHandler()); // 自定义的客户端处理器
    }
}