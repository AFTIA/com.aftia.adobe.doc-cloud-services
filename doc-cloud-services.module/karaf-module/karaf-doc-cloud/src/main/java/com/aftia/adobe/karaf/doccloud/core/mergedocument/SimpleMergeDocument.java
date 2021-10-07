package com.aftia.adobe.karaf.doccloud.core.mergedocument;

import java.io.InputStream;
import java.io.OutputStream;

import com.aftia.adobe.doccloud.core.authentication.DocAuthentication;
import com.aftia.adobe.doccloud.core.exceptions.DocCloudException;
import com.aftia.adobe.doccloud.core.services.mergedocument.MergeDocumentToPDF;
import com.aftia.adobe.karaf.doccloud.core.DocCloudModules;

import org.osgi.service.component.annotations.Component;

@Component(service = MergeDocument.class, immediate = true)
public class SimpleMergeDocument implements MergeDocument {

    @Override
    public String getModuleName() {
        return DocCloudModules.MergeDocument.name();
    }

    @Override
    public OutputStream mergeDocumentToPDF(String jsonData, InputStream template, String mimeType,
            DocAuthentication docAuthentication) throws DocCloudException {
        return MergeDocumentToPDF.mergeDocument(jsonData, template, mimeType, docAuthentication);
    }
}
