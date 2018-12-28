package com.panda.netty_1;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
@SuppressWarnings("all")
public class ServerHardler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf= (ByteBuf)msg;
        byte [] bytes=new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);

        String  body=new String(bytes,"UTF-8");
        System.out.println("server :---"+body);

        String respone ="hello  client";
        ctx.writeAndFlush(Unpooled.copiedBuffer(respone.getBytes()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
