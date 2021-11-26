package com.aftia.adobe.karaf.adobesign.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.aftia.adobe.adobesign.core.authentication.AdobeSignAuthentication;
import com.aftia.adobe.adobesign.core.exceptions.AdobeSignServiceException;
import com.google.gson.JsonParser;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

@Component(service = AdobeSignAuthentication.class, immediate = true, configurationPid = AdobeSignConstants.AUTH_PID)
public class SimpleAdobeSignAuthentication implements AdobeSignAuthentication {

    private ComponentContext componentContext;

    @Override
    public String authenticate(String baseUrl, String grantType, String clientId, String clientSecret,
            String refreshToken) throws AdobeSignServiceException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(String.format("%s" + AdobeSignConstants.OAUTH_REFRESH_CONTEXT, baseUrl));

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("grant_type", grantType));
            params.add(new BasicNameValuePair("client_id", clientId));
            params.add(new BasicNameValuePair("client_secret", clientSecret));
            params.add(new BasicNameValuePair("refresh_token", refreshToken));
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            ResponseHandler<String> handler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    if (entity == null) {
                        throw new IOException("No body was found as part of the token refresh");
                    } else {
                        return JsonParser.parseString(EntityUtils.toString(entity)).getAsJsonObject()
                                .get("access_token").getAsString();
                    }
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };

            return client.execute(httpPost, handler);
        } catch (ClientProtocolException e) {
            throw new AdobeSignServiceException(e.getMessage(), e);
        } catch (IOException e) {
            throw new AdobeSignServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String authenticate() throws AdobeSignServiceException {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(String.format("%s" + AdobeSignConstants.OAUTH_REFRESH_CONTEXT, this.componentContext.getProperties().get(AdobeSignConstants.AUTH_BASE_URL).toString()));

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("grant_type", this.componentContext.getProperties().get(AdobeSignConstants.AUTH_GRANT_TYPE).toString()));
            params.add(new BasicNameValuePair("client_id", this.componentContext.getProperties().get(AdobeSignConstants.AUTH_CLIENT_ID).toString()));
            params.add(new BasicNameValuePair("client_secret", this.componentContext.getProperties().get(AdobeSignConstants.AUTH_CLIENT_SECRET).toString()));
            params.add(new BasicNameValuePair("refresh_token", this.componentContext.getProperties().get(AdobeSignConstants.AUTH_REFRESH_TOKEN).toString()));
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            ResponseHandler<String> handler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    if (entity == null) {
                        throw new IOException("No body was found as part of the token refresh");
                    } else {
                        return JsonParser.parseString(EntityUtils.toString(entity)).getAsJsonObject()
                                .get("access_token").getAsString();
                    }
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };

            return client.execute(httpPost, handler);
        } catch (ClientProtocolException e) {
            throw new AdobeSignServiceException(e.getMessage(), e);
        } catch (IOException e) {
            throw new AdobeSignServiceException(e.getMessage(), e);
        }
    }

    @Activate
    public void activate(ComponentContext componentContext) {
        this.componentContext = componentContext;
    }

    @Modified
    public void modified(ComponentContext componentContext) {
        this.componentContext = componentContext;
    }

    @Deactivate
    public void deactivate() {
        this.componentContext = null;
    }

}
