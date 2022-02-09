package com.aftia.adobe.karaf.doccloud.core.repository;

import com.aftia.adobe.doccloud.core.exceptions.DocCloudException;

public interface Repository {
    String getFolders(String folderPath) throws DocCloudException;
    String getFiles(String folderPath, String filter) throws DocCloudException;
    String getFileContents(String folderPath, String fileName) throws DocCloudException;
}
