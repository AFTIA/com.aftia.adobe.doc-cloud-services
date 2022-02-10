package com.aftia.adobe.karaf.doccloud.core.repository;

import com.aftia.adobe.doccloud.core.exceptions.DocCloudException;

public interface Repository {
    Folders getFolders(String folderPath) throws DocCloudException;
    Files getFiles(String folderPath, String filter) throws DocCloudException;
    String getFileContents(String folderPath, String fileName) throws DocCloudException;
}
