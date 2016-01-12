package com.hellowd.server.netty.http.exception;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2016-01-12
 * Time : 오후 2:43
 * To change this template use File | Settings | File and Code Templates.
 */

public class RequestParamException  extends Exception{

    private static final long serialVersionUID = -4658653266645716158L;

    public RequestParamException(){
        super();
    }

    public RequestParamException(String message) {
        super(message);
    }

    public RequestParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestParamException(Throwable cause) {
        super(cause);
    }

    protected RequestParamException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
