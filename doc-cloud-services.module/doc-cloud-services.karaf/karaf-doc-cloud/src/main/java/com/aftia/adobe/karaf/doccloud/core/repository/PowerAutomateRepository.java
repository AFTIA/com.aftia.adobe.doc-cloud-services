package com.aftia.adobe.karaf.doccloud.core.repository;

import com.aftia.adobe.doccloud.core.exceptions.DocCloudException;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;

@Component(service = {Repository.class},immediate = true)
public class PowerAutomateRepository implements Repository {

    private static final String sharePointUrl = "https://aftia.sharepoint.com/sites/AFTIASandbox";
    private static final String getFolderListUrl = "https://prod-17.canadacentral.logic.azure.com:443/workflows/8c6548eaf9f0477f9f4b8dd399baf101/triggers/manual/paths/invoke?api-version=2016-06-01&sp=%2Ftriggers%2Fmanual%2Frun&sv=1.0&sig=Yd5sLVQNNrpx0EJ3eVEmHS21ko5lDW_0z8GNnsZG8cg";
    private static final String getFileListUrl = "https://prod-16.canadacentral.logic.azure.com:443/workflows/9b29b44c08414d599b471c35d3fff8d8/triggers/manual/paths/invoke?api-version=2016-06-01&sp=%2Ftriggers%2Fmanual%2Frun&sv=1.0&sig=E33sXIA9vdZeSFrr0c8ZMj-MjKYP2a5_vKqQaxfli00";

    @Override
    public RepoFolderContents getFolderContents(String folderPath) throws DocCloudException {
        Folders folders = getFolders(folderPath);
        Files files = getFiles(folderPath, ".JSON");
        return new RepoFolderContents(folders, files);
    }

    private Files getFiles(String folderPath, String filter) throws DocCloudException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(getFileListUrl);

            String json = "{\n" +
                    "    \"sharepointsite\": " + this.sharePointUrl + ",\n" +
                    "    \"path\":" + folderPath + "\n" +
                    "    \"filter\":" + filter + "\n" +
                    "}";
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            ResponseHandler<Files> handler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity responseEntity = response.getEntity();
                    if (responseEntity == null) {
                        throw new IOException("No body was found as part of the token refresh");
                    } else {
                        return new Gson().fromJson(json, Files.class);
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

    private Folders getFolders(String folderPath) throws DocCloudException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(getFolderListUrl);

            String json = "{\n" +
                    "    \"sharepointsite\": \"https://aftia.sharepoint.com/sites/AFTIASandbox\",\n" +
                    "    \"path\": \"\"\n" +
                    "}";
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            ResponseHandler<Folders> handler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity responseEntity = response.getEntity();
                    if (responseEntity == null) {
                        throw new IOException("No body was found as part of the token refresh");
                    } else {
                        return new Gson().fromJson(json, Folders.class);
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
    public String getFileContents(String folderPath, String fileName) {
        return null;
    }
}
