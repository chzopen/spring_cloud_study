package com.chz.myNetty.demo2.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

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
        try {
            CharSequence charSequence1 = msg.readCharSequence(msg.readableBytes(), StandardCharsets.UTF_8);
            log.info("MyServerChannelInBoundHandler1.channelRead0[" + ctx.channel().remoteAddress() + "]:" + charSequence1);

            AtomicLong tid1 = new AtomicLong(Thread.currentThread().getId());
            AtomicLong tid2 = new AtomicLong();

            ctx.channel().eventLoop().execute(new Runnable() {
                @Override
                public void run() {
                    ByteBuf wbuf = Unpooled.copiedBuffer(charSequence1, StandardCharsets.UTF_8);
                    try {
                        tid2.set(Thread.currentThread().getId());
                        if( Objects.equals(tid1.get(), tid2.get()) ){
                            log.info("equals(tid1, tid2)");
                        } else {
                            log.info("not equals(tid1, tid2)");
                        }
                        log.info("channelRead0, runnable 1: start, tid2={}", tid2.get());
                        Thread.sleep(1000L);
                        ctx.writeAndFlush(wbuf);
                        log.info("channelRead0, runnable 1: end, tid2={}", tid2.get());
                    } catch (InterruptedException e) {
                    } finally {
                        ReferenceCountUtil.release(wbuf);
                    }
                }
            });
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("MyServerChannelInBoundHandler1.channelInactive[" + ctx.channel().remoteAddress() + "]: ");
        super.channelInactive(ctx);
    }

}