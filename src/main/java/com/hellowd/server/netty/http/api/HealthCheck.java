package com.hellowd.server.netty.http.api;

import com.hellowd.server.netty.http.RestApiRequestTemplate;
import com.hellowd.server.netty.http.exception.RequestParamException;
import com.hellowd.server.netty.http.exception.ServiceException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2016-01-12
 * Time : 오후 2:55
 * To change this template use File | Settings | File and Code Templates.
 */
@Service("HealthCheck")
@Scope("prototype")
public class HealthCheck extends RestApiRequestTemplate {

    public HealthCheck(Map<String, String> reqData){
        super(reqData);
    }

    @Override
    public void requestParamValidation() throws RequestParamException {
    }

    @Override
    public void service() throws ServiceException {

        this.apiResult.addProperty("resultCode", "200");
    }
}