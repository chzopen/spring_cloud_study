package myNetty.demo2.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import myNetty.demo2.ConnectionInfo;
import myNetty.demo2.ConnectionInfos;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class MyServerChannelInBoundHandler2 extends SimpleChannelInboundHandler<String>
{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("MyServerChannelInBoundHandler2.channelActive[" + ctx.channel().remoteAddress() + "]: ");
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        log.info("MyServerChannelInBoundHandler2.channelRead0[" + ctx.channel().remoteAddress() + "]:" + msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("MyServerChannelInBoundHandler1.channelInactive[" + ctx.channel().remoteAddress() + "]: ");
        super.channelInactive(ctx);
    }

}