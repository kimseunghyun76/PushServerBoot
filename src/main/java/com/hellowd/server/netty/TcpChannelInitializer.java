package com.hellowd.server.netty;

import com.hellowd.server.netty.handler.TcpChannelHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
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
@Qualifier("tcpChannelInitializer")
public class TcpChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    @Qualifier("tcpChannelHandler")
    private ChannelInboundHandlerAdapter tcpChannelHandler;


    private static final StringDecoder DECODER = new StringDecoder();
    private static final StringEncoder ENCODER = new StringEncoder();
/*    private ZookeeperResponse zookeeperResponse;

    public TcpServerInitializer(ZookeeperResponse zookeeperResponse){
        this.zookeeperResponse = zookeeperResponse;
    }*/

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        //DelimiterBasedFrameDecoder는 구분자 기반의 패킷을 처리
        p.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        p.addLast(DECODER);
        p.addLast(ENCODER);
        p.addLast(tcpChannelHandler);  //zookeeperResponse
    }
}
