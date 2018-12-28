package com.panda.netty_1;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
@SuppressWarnings("all")
public class Client {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup loopGroup=new NioEventLoopGroup();
        Bootstrap bootstrap=new Bootstrap();
        bootstrap.group(loopGroup);
        bootstrap.channel(NioSocketChannel.class).option(ChannelOption.SO_BACKLOG,1024)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.SO_RCVBUF,32*1024)
                .option(ChannelOption.SO_SNDBUF,32*1023)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ClientHardler());
                    }
                });
        //client 用 连接
        ChannelFuture future = bootstrap.connect("127.0.0.1", 9999).sync();
        future.channel().writeAndFlush(Unpooled.copiedBuffer("hello 1 Server".getBytes()));
        Thread.sleep(1000);
        future.channel().writeAndFlush(Unpooled.copiedBuffer("hello 2 Server".getBytes()));
        Thread.sleep(1000);
        future.channel().writeAndFlush(Unpooled.copiedBuffer("hello 3 Server".getBytes()));

        future.channel().closeFuture().sync();

        loopGroup.shutdownGracefully();
    }
}
