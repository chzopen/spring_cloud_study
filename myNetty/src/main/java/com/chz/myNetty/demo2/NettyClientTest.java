package com.chz.myNetty.demo2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import com.chz.myNetty.demo2.client.MyClientChannelInitializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class NettyClientTest
{
    public static void main(String[] args) throws Exception
    {
        EventLoopGroup workerEventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerEventLoopGroup)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .channel(NioSocketChannel.class)
                    .handler(new MyClientChannelInitializer());

            List<Channel> channels = new ArrayList<>();
            for( int i=0; i<60000; i++ ){
                log.info("count: {}", i);
                channels.add(bootstrap.connect("localhost", 8080).sync().channel());
            }

            schedule(channels);

            for( Channel channel : channels ){
                channel.closeFuture().sync();
            }
        } finally {
            // 6. 关闭工作线程组
            workerEventLoopGroup.shutdownGracefully();
        }
    }

    private static void schedule(List<Channel> channels){
        new Thread(new MyScheduleRunnable(channels)).start();
    }

    private static class MyScheduleRunnable implements Runnable {

        private List<Channel> channels;
        private Random random = new Random();

        public MyScheduleRunnable(List<Channel> channels)
        {
            this.channels = channels;
        }

        @Override
        public void run() {
            int seq = 0;
            while(true){
                try {
                    Thread.sleep(1000L);

                    int index = random.nextInt(channels.size());
                    Channel channel = channels.get(index);
                    channel.pipeline().writeAndFlush(""+(seq++));

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
