package com.hellowd.server.netty;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2016-01-08
 * Time : 오후 7:06
 * To change this template use File | Settings | File and Code Templates.
 */

@Component
@Qualifier("httpChannelInitializer")
public class HttpChannelInitializer extends ChannelInitializer<SocketChannel> {

    static Logger logger = LoggerFactory.getLogger(HttpChannelInitializer.class);


    @Autowired
    @Qualifier("httpChannelHandler")
    private ChannelInboundHandlerAdapter httpChannelHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        p.addLast(new HttpRequestDecoder());
        p.addLast(new HttpObjectAggregator(65536));
        p.addLast(new HttpResponseEncoder());
        p.addLast(new HttpContentCompressor());
        p.addLast(httpChannelHandler);
    }
}
