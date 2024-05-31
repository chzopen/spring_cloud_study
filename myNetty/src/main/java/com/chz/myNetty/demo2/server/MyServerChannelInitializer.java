package com.chz.myNetty.demo2.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import com.chz.myNetty.demo2.ConnectionsInBoundHandler;

public class MyServerChannelInitializer extends ChannelInitializer<SocketChannel>
{
    @Override
    public void initChannel(SocketChannel socketChannel) {
        socketChannel.pipeline().addLast(new ConnectionsInBoundHandler());
        socketChannel.pipeline().addLast(new MyServerChannelInBoundHandler1());
    }
}