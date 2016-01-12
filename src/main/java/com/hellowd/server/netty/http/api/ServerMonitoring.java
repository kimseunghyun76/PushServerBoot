package com.hellowd.server.netty.http.api;

import com.hellowd.server.monitor.ServerMonitor;
import com.hellowd.server.netty.http.RestApiRequestTemplate;
import com.hellowd.server.netty.http.exception.RequestParamException;
import com.hellowd.server.netty.http.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2016-01-12
 * Time : 오후 3:21
 * To change this template use File | Settings | File and Code Templates.
 */
@Service("ServerMonitoring")
@Scope("prototype")
public class ServerMonitoring  extends RestApiRequestTemplate {

    @Autowired
    private ServerMonitor serverMonitor;

    public ServerMonitoring(Map<String,String> reqData){
        super(reqData);
    }

    @Override
    public void requestParamValidation() throws RequestParamException {

        //TODO: 1.owners 가 포함된 파라미터가 있다면, 아래와 같이 하는데? owners를 사용하는가?
        /*if(StringUtils.isEmpty(this.reqData.get("owners"))){
            long[] ownerSeqs = pushServer.getOwnerSeqs();

            if( ownerSeqs != null && ownerSeqs.length > 0 ) {
                StringBuffer sb = new StringBuffer();
                for(int i=0; i<ownerSeqs.length; i++) {
                    sb.append(ownerSeqs[i]);
                    if( i < ownerSeqs.length-1 )
                        sb.append(",");
                }
                owners = sb.toString();
            }

            if( StringUtils.isNotEmpty(ownerSeqs) ) {
                sb.append(",\"owners\":\"");
                sb.append(ownerSeqs);
                sb.append("\"");
            }
        }*/

        // TODO : cidWacths가 포함된 파라미터가 있다면, 아래와 같이 하는데? cidWacths를 사용하는가?
       /* if(StringUtils.isEmpty(this.reqData.get("cidWacths"))){
            cidWatchNumbers = pushServer.getCidWatchNumbers();

            if( StringUtils.isNotEmpty(cidWatchNumbers) ) {
                sb.append(",\"cidWatchNumbers\":\"");
                sb.append(cidWatchNumbers);
                sb.append("\"");
            }
        }*/
    }

    @Override
    public void service() throws ServiceException {

        this.apiResult.add("os", serverMonitor.getOsInfo());
        this.apiResult.add("memory", serverMonitor.getMemoryInfo());
        this.apiResult.addProperty("owners", serverMonitor.getOwners());
        this.apiResult.addProperty("cidWatchNumbers", serverMonitor.getCidWatchNumbers());
        this.apiResult.addProperty("uptime", serverMonitor.getUptime());
        this.apiResult.addProperty("requests", serverMonitor.getRequest());
        this.apiResult.addProperty("recvedBytes", serverMonitor.getReceivedBytes());
        this.apiResult.addProperty("sendBytes", serverMonitor.getSendBytes());
        this.apiResult.addProperty("errors", serverMonitor.getErrorCount());
        this.apiResult.addProperty("connections", serverMonitor.getConnectionCount());
        this.apiResult.addProperty("currentProcessTime", serverMonitor.getCurrentProcessTime());
        this.apiResult.addProperty("averageProcessTime", serverMonitor.getAverageProcessTime());
        this.apiResult.addProperty("cidWatcherLastSendHeartbeat", serverMonitor.getCidWatcherLastSendHeartbeatFormat());
        this.apiResult.addProperty("cidWatcherLastRecvHeartbeat", serverMonitor.getCidWatcherLastRecvHeartbeatFormat());

    }
}
