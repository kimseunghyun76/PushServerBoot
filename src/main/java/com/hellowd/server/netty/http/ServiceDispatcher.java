package com.hellowd.server.netty.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2016-01-12
 * Time : 오후 2:51
 * To change this template use File | Settings | File and Code Templates.
 */
@Component
public class ServiceDispatcher {

    static Logger logger = LoggerFactory.getLogger(ServiceDispatcher.class);

    private static ApplicationContext springContext;

    @Autowired
    public void init(ApplicationContext springContext){
        ServiceDispatcher.springContext = springContext;
    }

    public static RestApiRequest dispatch(Map<String,String> requestMap){
        String serviceUri = requestMap.get("REQUEST_URI");
        String httpMethod = requestMap.get("REQUEST_METHOD");
        String beanName;


        if(serviceUri == null || !httpMethod.equals("GET")){
            beanName = "BadRequest";
        }else{
            if(serviceUri.startsWith("/manager/status")){
                beanName = "ServerMonitoring";
            }else if(serviceUri.startsWith("/manager/healthcheck")){
                beanName = "HealthCheck";
            }else{
                beanName = "NotFoundRequest";
            }
        }

        RestApiRequest restApiRequest;
        try{
            restApiRequest = (RestApiRequest)springContext.getBean(beanName,requestMap);
        }catch (Exception e){
            logger.error(e.toString(),e);
            restApiRequest = (RestApiRequest)springContext.getBean("BadRequest",requestMap);
        }
        return restApiRequest;
    }
}
