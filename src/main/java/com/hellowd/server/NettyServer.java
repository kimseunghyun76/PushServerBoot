package com.hellowd.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2016-01-08
 * Time : 오후 6:59
 * To change this template use File | Settings | File and Code Templates.
 */
@Component
public class NettyServer {

    private static Logger logger = LoggerFactory.getLogger(NettyServer.class);

    @Autowired
    @Qualifier("tcpBootstrap")
    private ServerBootstrap tcpBootstrap;

    @Autowired
    @Qualifier("httpBootstrap")
    private ServerBootstrap httpBootstrap;

    @Autowired
    @Qualifier("tcpSocketAddress")
    private InetSocketAddress tcpPort;

    @Autowired
    @Qualifier("httpSocketAddress")
    private InetSocketAddress httpPort;

    private Channel tcpChannel;
    private Channel httpChannel;


    public void start() throws Exception{
        tcpChannel = tcpBootstrap.bind(tcpPort).sync().channel();
        httpChannel = httpBootstrap.bind(httpPort).sync().channel();

        tcpChannel.closeFuture().sync();
        httpChannel.closeFuture().sync();
    }

    @PreDestroy
    public void stop() throws Exception{
        tcpChannel.close();
        tcpChannel.parent().close();
        httpChannel.close();
        httpChannel.parent().close();
    }



    public ServerBootstrap getTcpBootstrap() {
        return tcpBootstrap;
    }

    public void setTcpBootstrap(ServerBootstrap tcpBootstrap) {
        this.tcpBootstrap = tcpBootstrap;
    }

    public ServerBootstrap getHttpBootstrap() {
        return httpBootstrap;
    }

    public void setHttpBootstrap(ServerBootstrap httpBootstrap) {
        this.httpBootstrap = httpBootstrap;
    }

    public InetSocketAddress getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(InetSocketAddress tcpPort) {
        this.tcpPort = tcpPort;
    }

    public InetSocketAddress getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(InetSocketAddress httpPort) {
        this.httpPort = httpPort;
    }

    public Channel getTcpChannel() {
        return tcpChannel;
    }

    public void setTcpChannel(Channel tcpChannel) {
        this.tcpChannel = tcpChannel;
    }

    public Channel getHttpChannel() {
        return httpChannel;
    }

    public void setHttpChannel(Channel httpChannel) {
        this.httpChannel = httpChannel;
    }
}
