package myNetty.demo1.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyServerChannelInBoundHandler extends SimpleChannelInboundHandler<String>
{
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("MyChannelInBoundHandler.channelActive[" + ctx.channel().remoteAddress() + "]: ");
        ctx.writeAndFlush("I'm server, thanks for connected!\r\n");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("MyChannelInBoundHandler.channelReadComplete[" + ctx.channel().remoteAddress() + "]");
        super.channelReadComplete(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        log.info("MyChannelInBoundHandler.channelRead0[" + ctx.channel().remoteAddress() + "]:" + msg);
        ctx.writeAndFlush(msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("MyChannelInBoundHandler.channelInactive[" + ctx.channel().remoteAddress() + "]: ");
    }
}