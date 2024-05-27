package myNetty.demo2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import myNetty.demo2.server.MyServerChannelInitializer;

@Slf4j
public class NettyServerTest
{
    public static void main(String[] args) throws Exception
    {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 创建服务器端的启动对象，配置参数
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyServerChannelInitializer());

            // 绑定端口，同步等待成功
            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();

            // 等待服务端监听端口关闭
            channelFuture.channel().closeFuture().sync();
            log.info("end!!!!!!!!!!!!!!!!!!!!!!");
        } finally {
            // 优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
