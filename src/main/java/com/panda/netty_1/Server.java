package com.panda.netty_1;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

@SuppressWarnings("all")
public class Server {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup aloop=new NioEventLoopGroup();
        EventLoopGroup bloop=new NioEventLoopGroup();
        ServerBootstrap bootstrap=new ServerBootstrap();
        bootstrap.group(aloop,bloop)
        .channel(NioServerSocketChannel.class)
        .option(ChannelOption.SO_BACKLOG,1024)
        .option(ChannelOption.SO_SNDBUF,32*1024)
        .option(ChannelOption.SO_RCVBUF,32*1024)
        .option(ChannelOption.SO_KEEPALIVE,true)
        .childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel channel) throws Exception {
                channel.pipeline().addLast(new ServerHardler());
            }
        });
        //server  用绑定
        ChannelFuture future = bootstrap.bind(9999).sync();

       // Thread.sleep(Integer.MAX_VALUE);

        future.channel().closeFuture().sync();

        aloop.shutdownGracefully();
        bloop.shutdownGracefully();

    }
}
