package com.aftia.adobe.adobesign.core.client;

import com.aftia.adobe.adobesign.core.exceptions.AdobeSignServiceException;

import io.swagger.client.model.ApiClient;

public interface AdobeSignApiClient {

    public ApiClient buildClient(String baseUrl, String endpointUrl) throws AdobeSignServiceException;

    public ApiClient buildClient() throws AdobeSignServiceException;

}