package com.aftia.adobe.karaf.doccloud.core.repository;

import com.aftia.adobe.doccloud.core.exceptions.DocCloudException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;

@Component(service = {Repository.class}, immediate = true)
public class PowerAutomateRepository implements Repository {

    private static final String sharePointUrl = "https://aftia.sharepoint.com/sites/AFTIASandbox";
    private static final String getFolderListUrl = "https://prod-17.canadacentral.logic.azure.com:443/workflows/8c6548eaf9f0477f9f4b8dd399baf101/triggers/manual/paths/invoke?api-version=2016-06-01&sp=%2Ftriggers%2Fmanual%2Frun&sv=1.0&sig=Yd5sLVQNNrpx0EJ3eVEmHS21ko5lDW_0z8GNnsZG8cg";
    private static final String getFileListUrl = "https://prod-16.canadacentral.logic.azure.com:443/workflows/9b29b44c08414d599b471c35d3fff8d8/triggers/manual/paths/invoke?api-version=2016-06-01&sp=%2Ftriggers%2Fmanual%2Frun&sv=1.0&sig=E33sXIA9vdZeSFrr0c8ZMj-MjKYP2a5_vKqQaxfli00";
    private static final String getFileContentsUrl = "https://prod-17.canadacentral.logic.azure.com:443/workflows/585a2e0689a74fc1ad89c892f207dbdb/triggers/manual/paths/invoke?api-version=2016-06-01&sp=%2Ftriggers%2Fmanual%2Frun&sv=1.0&sig=wPRy7Vwjf-b59tINkNLHJznXBDtF5gUoR-Qm-iXE4_o";

    public String getFiles(String folderPath, String filter) throws DocCloudException {
        if (folderPath == null) {
            folderPath = "";
        }
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(getFileListUrl);

            String json = "{\n" +
                    "    \"sharepointsite\": \"" + sharePointUrl + "\",\n" +
                    "    \"path\": \"" + folderPath + "\",\n" +
                    "    \"filter\" : \"" + filter + "\"\n" +
                    "}";

            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            ResponseHandler<String> handler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity responseEntity = response.getEntity();
                    if (responseEntity == null) {
                        throw new IOException("No body was found as part of the token refresh");
                    } else {
                        return EntityUtils.toString(responseEntity);
                    }
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };

            return client.execute(httpPost, handler);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DocCloudException("Could not retrieve file list from PowerAutomate.", e);
        }
    }

    public String getFolders(String folderPath) throws DocCloudException {
        if (folderPath == null) {
            folderPath = "";
        }
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(getFolderListUrl);

            String json = "{\n" +
                    "    \"sharepointsite\": \"" + sharePointUrl + "\",\n" +
                    "    \"path\": \"" + folderPath + "\"\n" +
                    "}";
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            ResponseHandler<String> handler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity responseEntity = response.getEntity();
                    if (responseEntity == null) {
                        throw new IOException("No body was found as part of the token refresh");
                    } else {
                        return EntityUtils.toString(responseEntity);
                    }
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };

            return client.execute(httpPost, handler);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DocCloudException("Could not retrieve folder list from PowerAutomate.", e);
        }
    }

    @Override
    public String getFileContents(String folderPath, String fileName) throws DocCloudException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(getFileContentsUrl);

            String json = "{\n" +
                    "    \"sharepointsite\": \"" + sharePointUrl + "\",\n" +
                    "    \"path\": \"" + folderPath + "\",\n" +
                    "    \"filename\": \"" + fileName + "\"\n" +
                    "}";
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            ResponseHandler<String> handler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity responseEntity = response.getEntity();
                    if (responseEntity == null) {
                        throw new IOException("No body was found as part of the token refresh");
                    } else {
                        return EntityUtils.toString(responseEntity);
                    }
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };

            return client.execute(httpPost, handler);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DocCloudException("Could not retrieve file contents from PowerAutomate.", e);
        }
    }
}
