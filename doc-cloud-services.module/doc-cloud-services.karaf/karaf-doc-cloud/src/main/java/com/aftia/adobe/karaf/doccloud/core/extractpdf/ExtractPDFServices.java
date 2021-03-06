package com.aftia.adobe.karaf.doccloud.core.extractpdf;

import java.io.InputStream;
import java.io.OutputStream;

import com.adobe.pdfservices.operation.pdfops.options.extractpdf.ExtractPDFOptions;
import com.aftia.adobe.doccloud.core.authentication.DocAuthentication;
import com.aftia.adobe.doccloud.core.exceptions.DocCloudException;
import com.aftia.adobe.karaf.doccloud.core.DocService;

public interface ExtractPDFServices extends DocService {

    public OutputStream extractTextFromPDF(InputStream pdf, ExtractPDFOptions extractPDFOptions, DocAuthentication docAuthentication) throws DocCloudException;
    
}
