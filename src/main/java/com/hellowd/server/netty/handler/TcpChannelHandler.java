package com.hellowd.server.netty.handler;

import com.hellowd.server.api.repository.StoreOwnerRepository;
import com.hellowd.server.monitor.ServerMonitor;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.Date;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2016-01-08
 * Time : 오후 7:11
 * To change this template use File | Settings | File and Code Templates.
 */

@Component
@Qualifier("tcpChannelHandler")
@ChannelHandler.Sharable
public class TcpChannelHandler extends ChannelInboundHandlerAdapter {
    private static Logger logger = Logger.getLogger(HttpChannelHandler.class.getName());

    @Autowired
    private ServerMonitor serverMonitor;

    @Autowired
    private StoreOwnerRepository storeOwnerRepository;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("welcome to this server, your info  "+ InetAddress.getLocalHost().getHostName().toString() );
        ctx.write("welcome to this server (" + ctx.channel().remoteAddress().toString() + ")\r\n");
        ctx.write("it's " + new Date() + "\r\n");
        ctx.flush();
        serverMonitor.incrementAndGetConnections();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
