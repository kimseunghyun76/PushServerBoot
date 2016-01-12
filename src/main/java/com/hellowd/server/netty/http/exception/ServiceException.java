package com.hellowd.server.netty.http.exception;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2016-01-12
 * Time : 오후 2:43
 * To change this template use File | Settings | File and Code Templates.
 */


public class ServiceException extends Exception {

    private static final long serialVersionUID = 7203340217842372939L;

    public ServiceException(){
        super();
    }

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Throwable cause){
        super(message,cause);
    }

    public ServiceException(Throwable cause){
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message,cause,enableSuppression,writableStackTrace);
    }

}
