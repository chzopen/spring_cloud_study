package myNetty.demo2.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyServerChannelOutBoundHandler1 extends ChannelOutboundHandlerAdapter
{
    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        log.info("MyServerChannelOutBoundHandler1.read[" + ctx.channel().remoteAddress() + "]: ");
        super.read(ctx);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("MyServerChannelOutBoundHandler1.write[{}]: {}", ctx.channel().remoteAddress(), msg);
        super.write(ctx, msg, promise);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        log.info("MyServerChannelOutBoundHandler1.flush[" + ctx.channel().remoteAddress() + "]: ");
        super.flush(ctx);
    }
}