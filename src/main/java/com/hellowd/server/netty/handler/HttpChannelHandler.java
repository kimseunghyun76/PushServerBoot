package com.hellowd.server.netty.handler;

import com.google.gson.JsonObject;
import com.hellowd.server.netty.http.RestApiRequest;
import com.hellowd.server.netty.http.ServiceDispatcher;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2016-01-08
 * Time : 오후 7:11
 * To change this template use File | Settings | File and Code Templates.
 */
@Component
@Qualifier("httpChannelHandler")
@ChannelHandler.Sharable
public class HttpChannelHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = Logger.getLogger(HttpChannelHandler.class);


    private HttpRequest request;

    private JsonObject apiResult;

    private Map<String,String> reqData = new HashMap<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //Request Header
        if(msg instanceof HttpRequest){

            this.request = (HttpRequest)msg;
            reqData.put("REQUEST_URI",request.getUri());
            reqData.put("REQUEST_METHOD",request.getMethod().name());
            reqData.put("REQUEST_ALL", request.toString());
            RestApiRequest restApiRequest = ServiceDispatcher.dispatch(reqData);
            try{
                restApiRequest.executeService();
                apiResult = restApiRequest.getApiResult();
            }finally {
                reqData.clear();
            }
        }


        if(!writeResponse(ctx,(HttpRequest)msg)){
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                    .addListener(ChannelFutureListener.CLOSE);
        }
        reset();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }


    private boolean writeResponse(ChannelHandlerContext ctx, HttpObject currentObj) {

        boolean keepAlive = HttpHeaders.isKeepAlive(request);

        // Build the response object.
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                currentObj.getDecoderResult().isSuccess() ? OK : BAD_REQUEST,
                Unpooled.copiedBuffer(apiResult.toString(), CharsetUtil.UTF_8));

        response.headers().set(CONTENT_TYPE, "application/json; charset=UTF-8");

        if (keepAlive) {
            // Add 'Content-Length' header only for a keep-alive connection.
            response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
            // Add keep alive header as per:
            // -
            // http://www.w3.org/Protocols/HTTP/1.1/draft-ietf-http-v11-spec-01.html#Connection
            response.headers().set(CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        }

        // Write the response.
        ctx.write(response);

        return keepAlive;
    }

    private void reset() {
        request = null;
    }
}
