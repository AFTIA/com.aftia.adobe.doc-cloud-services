package com.aftia.adobe.karaf.doccloud.core.mergedocument;

import java.io.InputStream;
import java.io.OutputStream;

import com.aftia.adobe.doccloud.core.authentication.DocAuthentication;
import com.aftia.adobe.doccloud.core.exceptions.DocCloudException;
import com.aftia.adobe.karaf.doccloud.core.DocService;

public interface MergeDocument extends DocService {

    public OutputStream mergeDocumentToPDF(String jsonData, InputStream template, String mimeType, DocAuthentication docAuthentication) throws DocCloudException;
    
}
