package com.aftia.adobe.adobesign.core.authentication;

import com.aftia.adobe.adobesign.core.exceptions.AdobeSignServiceException;

public interface AdobeSignAuthentication {

    public String authenticate(String baseUrl, String grantType, String clientId, String clientSecret, String refreshToken) throws AdobeSignServiceException;

    public String authenticate() throws AdobeSignServiceException; 

}