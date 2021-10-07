package com.aftia.adobe.adobesign.core.exceptions;

public class AdobeSignServiceException extends Exception {

    public AdobeSignServiceException(String msg, Throwable t) {
        super(msg, t);
    }

    public AdobeSignServiceException(String msg) {
        super(msg);
    }
    
}
