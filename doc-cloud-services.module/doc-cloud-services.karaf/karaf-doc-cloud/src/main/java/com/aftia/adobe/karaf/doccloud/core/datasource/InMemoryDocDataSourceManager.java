package com.aftia.adobe.karaf.doccloud.core.datasource;

import com.aftia.adobe.doccloud.core.exceptions.DocCloudException;
import org.osgi.service.component.annotations.Component;

import java.util.*;

@Component(service = DocDataSourceManager.class, immediate = true)
public class InMemoryDocDataSourceManager implements DocDataSourceManager {

    private final SortedMap<String, DataSource> dataSources = new TreeMap<>();

    @Override
    public void addNewDataSource(DataSource dataSource) throws DocCloudException {
        String name = dataSource.getName();
        if (dataSources.containsKey(name)) {
            throw new DocCloudException(String.format("A Data Source with the name '%s' already exists.", name));
        }
        else{
            saveDataSource(dataSource);
        }
    }

    @Override
    public void saveDataSource(DataSource dataSource) {
        dataSources.put(dataSource.getName(), dataSource);
    }

    @Override
    public List<DataSource> getDataSources() {
        return new ArrayList<>(dataSources.values());
    }
}
