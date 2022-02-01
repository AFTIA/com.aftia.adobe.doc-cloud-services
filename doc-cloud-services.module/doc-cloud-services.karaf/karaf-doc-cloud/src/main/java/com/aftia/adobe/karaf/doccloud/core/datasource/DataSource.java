package com.aftia.adobe.karaf.doccloud.core.datasource;

public class DataSource {
    private final String name;
    private final String url;
    private final String requestBody;

    public DataSource(String name, String url, String requestBody) {
        this.name = name;
        this.url = url;
        this.requestBody = requestBody;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getRequestBody() {
        return requestBody;
    }
}
