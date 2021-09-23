package com.aftia.adobe.core;

import com.aftia.adobe.core.exceptions.DocCloudException;

public interface DoCService extends DocAuthentication {

    public <T> T invoke() throws DocCloudException; 
    
}
