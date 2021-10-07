package com.aftia.adobe.adobesign.core.services.transientdoc;

import java.io.InputStream;

import com.aftia.adobe.adobesign.core.authentication.AdobeSignAuthentication;
import com.aftia.adobe.adobesign.core.client.AdobeSignApiClient;
import com.aftia.adobe.adobesign.core.exceptions.AdobeSignServiceException;

import io.swagger.client.api.TransientDocumentsApi;
import io.swagger.client.model.ApiException;
import io.swagger.client.model.transientDocuments.TransientDocumentResponse;

public class TransientDocument {

    public static String createTransientDocument(AdobeSignApiClient apiClient, AdobeSignAuthentication authentication,
            InputStream file, String fileName, String xApiUser, String xOnBehalfOfUser, String mimeType) throws AdobeSignServiceException {
        try {
            TransientDocumentsApi transientDocumentsApi = new TransientDocumentsApi(apiClient.buildClient());
            TransientDocumentResponse response = transientDocumentsApi.createTransientDocument(
                    authentication.authenticate(), file, xApiUser, xOnBehalfOfUser, fileName, mimeType);
            return response.getTransientDocumentId();
        } catch (ApiException e) {
           throw new AdobeSignServiceException(e.getMessage(), e);
        }

    }
}
