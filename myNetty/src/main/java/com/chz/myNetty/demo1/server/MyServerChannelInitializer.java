package com.chz.myNetty.demo1.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class MyServerChannelInitializer extends ChannelInitializer<SocketChannel>
{
    @Override
    public void initChannel(SocketChannel socketChannel) {
        socketChannel.pipeline().addLast(new StringDecoder());
        socketChannel.pipeline().addLast(new StringEncoder());
        socketChannel.pipeline().addLast(new MyServerChannelInBoundHandler());
    }
}