package com.aftia.adobe.doccloud.core.exceptions;

public class DocAuthenticationException extends Exception {

    public DocAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public DocAuthenticationException(String msg) {
        super(msg);
    }
    
}
