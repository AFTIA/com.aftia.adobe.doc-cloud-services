package com.aftia.adobe.sling.impl;

import java.io.OutputStream;

import org.osgi.service.component.annotations.Component;

import com.aftia.adobe.core.exceptions.DocCloudException;
import com.aftia.adobe.core.merge.MergeDocumentToPDF;
import com.aftia.adobe.sling.DocCloudServices;

@Component(service = DocCloudServices.class, immediate = true)
public class SimpleDocCloudServices implements DocCloudServices {

    @Override
    public OutputStream mergeDocument() throws DocCloudException {
        return new MergeDocumentToPDF().invoke();
    }
}
