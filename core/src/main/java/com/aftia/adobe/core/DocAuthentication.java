package com.aftia.adobe.core;

import com.adobe.pdfservices.operation.auth.Credentials;

public interface DocAuthentication {
    
    public Credentials getCredentials() throws Exception;
}
