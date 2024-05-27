package myNetty.demo1.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyServerChannelOutBoundHandler extends ChannelOutboundHandlerAdapter
{
    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        log.info("MyChannelOutBoundHandler.read[" + ctx.channel().remoteAddress() + "]: ");
        ctx.writeAndFlush("read: thanks for connected");
        super.read(ctx);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("MyChannelOutBoundHandler.channelInactive[" + ctx.channel().remoteAddress() + "]: ");
        super.write(ctx, msg, promise);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        log.info("MyChannelOutBoundHandler.flush[" + ctx.channel().remoteAddress() + "]: ");
        super.flush(ctx);
    }
}