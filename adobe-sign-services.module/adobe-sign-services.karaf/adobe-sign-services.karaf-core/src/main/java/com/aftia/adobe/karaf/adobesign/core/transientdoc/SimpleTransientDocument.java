package com.aftia.adobe.karaf.adobesign.core.transientdoc;

import java.io.InputStream;

import com.aftia.adobe.adobesign.core.authentication.AdobeSignAuthentication;
import com.aftia.adobe.adobesign.core.client.AdobeSignApiClient;
import com.aftia.adobe.adobesign.core.exceptions.AdobeSignServiceException;
import com.aftia.adobe.adobesign.core.services.transientdoc.TransientDocument;
import com.aftia.adobe.karaf.adobesign.core.AdobeSignModules;

import org.osgi.service.component.annotations.Component;

@Component(service = TransientDocumentService.class, immediate = true)
public class SimpleTransientDocument implements TransientDocumentService {

    @Override
    public String getModuleName() {
        return AdobeSignModules.TRANSIENT_DOCUMENT.name();
    }

    @Override
    public String createTransientDocument(AdobeSignApiClient apiClient, AdobeSignAuthentication authentication,
            InputStream file, String fileName, String xApiUser, String xOnBehalfOfUser, String mimeType) throws AdobeSignServiceException {
        return TransientDocument.createTransientDocument(apiClient, authentication, file, fileName, xApiUser, xOnBehalfOfUser, mimeType);
    }
    
}
