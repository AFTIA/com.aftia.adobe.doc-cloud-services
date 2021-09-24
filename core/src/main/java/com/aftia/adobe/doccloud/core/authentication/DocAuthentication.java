package com.aftia.adobe.doccloud.core.authentication;

import com.adobe.pdfservices.operation.auth.Credentials;
import com.aftia.adobe.doccloud.core.exceptions.DocAuthenticationException;

public interface DocAuthentication {
    
    public Credentials getCredentials() throws DocAuthenticationException;
}
