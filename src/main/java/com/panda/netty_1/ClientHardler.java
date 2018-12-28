package com.panda.netty_1;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
@SuppressWarnings("all")
public class ClientHardler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf  buf=(ByteBuf)msg;
        byte [] bytes=new byte[buf.readableBytes()];
        buf.readBytes(bytes);

        String req=new String(bytes,"UTF-8");
        System.out.println("Client:--"+req);
        //无写操作时  需要进行释放msg资源
        ReferenceCountUtil.release(msg);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
