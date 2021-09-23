package com.aftia.adobe.sling;

import java.io.OutputStream;

import com.aftia.adobe.core.exceptions.DocCloudException;

public interface DocCloudServices {

    public OutputStream mergeDocument() throws DocCloudException;
    
}
