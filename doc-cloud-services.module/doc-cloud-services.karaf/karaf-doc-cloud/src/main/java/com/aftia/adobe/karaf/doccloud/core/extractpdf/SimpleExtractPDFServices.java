package com.aftia.adobe.karaf.doccloud.core.extractpdf;

import java.io.InputStream;
import java.io.OutputStream;

import com.adobe.pdfservices.operation.pdfops.options.extractpdf.ExtractPDFOptions;
import com.aftia.adobe.doccloud.core.authentication.DocAuthentication;
import com.aftia.adobe.doccloud.core.exceptions.DocCloudException;
import com.aftia.adobe.doccloud.core.services.extractpdf.ExtractTextInfoFromPDF;
import com.aftia.adobe.karaf.doccloud.core.DocCloudModules;

import org.osgi.service.component.annotations.Component;

@Component(service = ExtractPDFServices.class, immediate = true)
public class SimpleExtractPDFServices implements ExtractPDFServices {

    @Override
    public String getModuleName() {
        return DocCloudModules.ExtractPDF.name();
    }

    @Override
    public OutputStream extractTextFromPDF(InputStream pdf, ExtractPDFOptions extractPDFOptions,
            DocAuthentication docAuthentication) throws DocCloudException {
        return ExtractTextInfoFromPDF.extractTextInfoFromPDF(pdf, extractPDFOptions, docAuthentication);
    }

    
}
