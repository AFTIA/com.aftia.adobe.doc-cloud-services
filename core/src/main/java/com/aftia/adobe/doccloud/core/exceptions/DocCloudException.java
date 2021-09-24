package com.aftia.adobe.doccloud.core.exceptions;

public class DocCloudException extends Exception {

    public DocCloudException(String msg, Throwable t) {
        super(msg, t);
    }

    public DocCloudException(String msg) {
        super(msg);
    }
    
}
