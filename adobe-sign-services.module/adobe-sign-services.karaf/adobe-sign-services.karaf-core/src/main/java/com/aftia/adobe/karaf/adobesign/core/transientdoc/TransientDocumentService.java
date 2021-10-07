package com.aftia.adobe.karaf.adobesign.core.transientdoc;

import java.io.InputStream;

import com.aftia.adobe.adobesign.core.authentication.AdobeSignAuthentication;
import com.aftia.adobe.adobesign.core.client.AdobeSignApiClient;
import com.aftia.adobe.adobesign.core.exceptions.AdobeSignServiceException;
import com.aftia.adobe.karaf.adobesign.core.AdobeSignService;

public interface TransientDocumentService extends AdobeSignService {

    public String createTransientDocument(AdobeSignApiClient apiClient, AdobeSignAuthentication authentication,
            InputStream file, String fileName, String xApiUser, String xOnBehalfOfUser, String mimeType) throws AdobeSignServiceException;
}
