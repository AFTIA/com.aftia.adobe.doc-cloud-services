package com.aftia.adobe.doccloud.core.services.extractpdf;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.auth.Credentials;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.ExtractPDFOperation;
import com.adobe.pdfservices.operation.pdfops.options.extractpdf.ExtractElementType;
import com.adobe.pdfservices.operation.pdfops.options.extractpdf.ExtractPDFOptions;
import com.aftia.adobe.doccloud.core.authentication.DocAuthentication;
import com.aftia.adobe.doccloud.core.exceptions.DocCloudException;

public class ExtractTextInfoFromPDF {

    public static OutputStream extractTextInfoFromPDF(InputStream inPdf, ExtractPDFOptions extractPDFOptions,
            DocAuthentication docAuthentication) throws DocCloudException {
        try {
            OutputStream out = new ByteArrayOutputStream();

            // Create an ExecutionContext using credentials.
            ExecutionContext executionContext = ExecutionContext.create(docAuthentication.getCredentials());

            ExtractPDFOperation extractPDFOperation = ExtractPDFOperation.createNew();

            FileRef fileRef = FileRef.createFromStream(inPdf, "application/pdf");
            extractPDFOperation.setInputFile(fileRef);

            // Build ExtractPDF options and set them into the operation
            extractPDFOperation.setOptions(extractPDFOptions);

            // Execute the operation
            FileRef result = extractPDFOperation.execute(executionContext);

            result.saveAs(out);

            return out;
        } catch (Exception e) {
            throw new DocCloudException(e.getMessage(), e);
        }

    }

}
