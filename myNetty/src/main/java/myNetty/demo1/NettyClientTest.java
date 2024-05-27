package myNetty.demo1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import myNetty.demo1.client.MyClientChannelInitializer;

@Slf4j
public class NettyClientTest
{
    public static void main(String[] args) throws Exception
    {
        EventLoopGroup workerEventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerEventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new MyClientChannelInitializer());
            ChannelFuture future = bootstrap.connect("localhost", 8080).sync();
            future.channel().closeFuture().sync();
        } finally {
            // 6. 关闭工作线程组
            workerEventLoopGroup.shutdownGracefully();
        }
    }
}
