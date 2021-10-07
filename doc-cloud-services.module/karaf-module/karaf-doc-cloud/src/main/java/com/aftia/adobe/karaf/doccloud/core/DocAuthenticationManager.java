package com.aftia.adobe.karaf.doccloud.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Dictionary;

public interface DocAuthenticationManager {
    
    public Dictionary<String, Object> updateAuthentication(String clientId, String clientSecret, String accountId, String orgnizationId, InputStream privateKey) throws IOException;

    public Dictionary<String, Object> getAuthentication() throws IOException;
}
