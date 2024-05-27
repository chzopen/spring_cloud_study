package myNetty.demo2.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
public class MyServerChannelInBoundHandler1 extends SimpleChannelInboundHandler<ByteBuf>
{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("MyServerChannelInBoundHandler1.channelActive[" + ctx.channel().remoteAddress() + "]: ");
        ctx.writeAndFlush("MyServerChannelInBoundHandler1: I'm server, thanks for connected!\r\n");
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        CharSequence charSequence1 = msg.readCharSequence(msg.readableBytes(), StandardCharsets.UTF_8);
        log.info("MyServerChannelInBoundHandler1.channelRead0[" + ctx.channel().remoteAddress() + "]:" + charSequence1);
        ByteBuf wbuf = Unpooled.copiedBuffer(charSequence1, StandardCharsets.UTF_8);
        ctx.writeAndFlush(wbuf);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("MyServerChannelInBoundHandler1.channelInactive[" + ctx.channel().remoteAddress() + "]: ");
        super.channelInactive(ctx);
    }

    public static void main(String[] args) {
        ByteBuf heapBuf = Unpooled.buffer(10);
        if (heapBuf.hasArray()) {
            byte[] array = heapBuf.array();
            int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
            int length = heapBuf.readableBytes();
            //0,0
            log.info("offset:{},length:{}", offset, length);
        }
    }
}