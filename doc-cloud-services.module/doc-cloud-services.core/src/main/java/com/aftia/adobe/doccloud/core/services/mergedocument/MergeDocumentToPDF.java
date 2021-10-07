package com.aftia.adobe.doccloud.core.services.mergedocument;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.auth.Credentials;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.DocumentMergeOperation;
import com.adobe.pdfservices.operation.pdfops.options.documentmerge.DocumentMergeOptions;
import com.adobe.pdfservices.operation.pdfops.options.documentmerge.OutputFormat;
import com.aftia.adobe.doccloud.core.authentication.DocAuthentication;
import com.aftia.adobe.doccloud.core.exceptions.DocCloudException;

import org.json.JSONObject;

public class MergeDocumentToPDF {

    public static OutputStream mergeDocument(String jsonData, InputStream template, String mimeType, DocAuthentication docAuthentication)
            throws DocCloudException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            Credentials credentials = docAuthentication.getCredentials();
            ExecutionContext executionContext = ExecutionContext.create(credentials);

            JSONObject jsonDataForMerge = new JSONObject(jsonData);

            DocumentMergeOptions documentMergeOptions = new DocumentMergeOptions(jsonDataForMerge, OutputFormat.PDF);
            DocumentMergeOperation documentMergeOperation = DocumentMergeOperation.createNew(documentMergeOptions);

            FileRef documentTemplate = FileRef.createFromStream(template, mimeType);
            documentMergeOperation.setInput(documentTemplate);

            FileRef result = documentMergeOperation.execute(executionContext);
            result.saveAs(outputStream);

            return outputStream;
        } catch (Exception e) {
            throw new DocCloudException(e.getMessage(), e);
        }

    }
}