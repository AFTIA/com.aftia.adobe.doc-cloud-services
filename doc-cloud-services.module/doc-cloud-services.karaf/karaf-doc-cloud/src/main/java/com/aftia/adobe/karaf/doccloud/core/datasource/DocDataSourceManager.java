package com.aftia.adobe.karaf.doccloud.core.datasource;

import com.aftia.adobe.doccloud.core.exceptions.DocCloudException;

import java.io.IOException;
import java.util.List;

public interface DocDataSourceManager {
    
    void addNewDataSource(DataSource dataSource) throws DocCloudException;
    void saveDataSource(DataSource dataSource);
    List<DataSource> getDataSources() throws IOException;
}
