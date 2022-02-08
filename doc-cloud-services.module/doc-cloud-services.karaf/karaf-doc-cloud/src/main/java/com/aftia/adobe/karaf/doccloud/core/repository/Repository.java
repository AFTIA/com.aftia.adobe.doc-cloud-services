package com.aftia.adobe.karaf.doccloud.core.repository;

import com.aftia.adobe.doccloud.core.exceptions.DocCloudException;

public interface Repository {
    RepoFolderContents getFolderContents(String folderPath) throws DocCloudException;
    String getFileContents(String folderPath, String fileName);
}
