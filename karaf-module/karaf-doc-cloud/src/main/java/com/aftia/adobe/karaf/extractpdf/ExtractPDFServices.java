package com.aftia.adobe.karaf.extractpdf;

import java.io.InputStream;
import java.io.OutputStream;

import com.adobe.pdfservices.operation.pdfops.options.extractpdf.ExtractPDFOptions;
import com.aftia.adobe.core.DocAuthentication;
import com.aftia.adobe.core.exceptions.DocCloudException;
import com.aftia.adobe.karaf.DocService;

public interface ExtractPDFServices extends DocService {

    public OutputStream extractTextFromPDF(InputStream pdf, ExtractPDFOptions extractPDFOptions, DocAuthentication docAuthentication) throws DocCloudException;
    
}
