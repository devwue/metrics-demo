package com.rprakashg.sb.samples.exceptions;

/**
 * Created by rprakashg on 6/29/17.
 */
public class BackendServiceException extends Exception {
    /**
     */
    private static final long serialVersionUID = 5614774191196478481L;
    private int code;

    public int getCode(){
        return code;
    }

    public BackendServiceException(Throwable cause){
        super(cause);
    }
    public BackendServiceException(int code, String message){
        super(message);
        this.code = code;
    }
    public BackendServiceException(int code, String message, Exception e){
        super(message, e);
        this.code = code;
    }
}
