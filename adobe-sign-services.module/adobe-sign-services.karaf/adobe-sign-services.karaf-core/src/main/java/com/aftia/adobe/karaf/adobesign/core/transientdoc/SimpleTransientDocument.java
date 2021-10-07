package com.aftia.adobe.karaf.adobesign.core.transientdoc;

import java.io.InputStream;

import com.aftia.adobe.adobesign.core.authentication.AdobeSignAuthentication;
import com.aftia.adobe.adobesign.core.client.AdobeSignApiClient;
import com.aftia.adobe.karaf.adobesign.core.AdobeSignModules;

import org.osgi.service.component.annotations.Component;

@Component(service = TransientDocument.class, immediate = true)
public class SimpleTransientDocument implements TransientDocument {

    @Override
    public String getModuleName() {
        return AdobeSignModules.TRANSIENT_DOCUMENT.name();
    }

    @Override
    public String createTransientDocument(AdobeSignApiClient apiClient, AdobeSignAuthentication authentication,
            InputStream file, String fileName, String xApiUser, String xOnBehalfOfUser, String mimeType) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
