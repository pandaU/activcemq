package com.panda.netty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

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
                //分隔符
                //channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,Unpooled.copiedBuffer("@".getBytes())));
               //定长消息
                channel.pipeline().addLast(new FixedLengthFrameDecoder(5));
                channel.pipeline().addLast(new StringDecoder());
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
