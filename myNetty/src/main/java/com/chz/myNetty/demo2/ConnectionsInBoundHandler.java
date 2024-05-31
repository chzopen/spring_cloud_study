package com.chz.myNetty.demo2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionsInBoundHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelActive[" + ctx.channel().remoteAddress() + "]");
        ConnectionInfos.put(ctx.channel(), new ConnectionInfo());
        log.info("channelActive: size={}", ConnectionInfos.size());
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelInactive[" + ctx.channel().remoteAddress() + "]");
        ConnectionInfos.remove(ctx.channel());
        log.info("channelInactive: size={}", ConnectionInfos.size());
        super.channelInactive(ctx);
    }

}