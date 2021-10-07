package com.aftia.adobe.karaf.adobesign.core;

import com.aftia.adobe.adobesign.core.authentication.AdobeSignAuthentication;
import com.aftia.adobe.adobesign.core.client.AdobeSignApiClient;
import com.aftia.adobe.adobesign.core.exceptions.AdobeSignServiceException;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import io.swagger.client.api.BaseUrisApi;
import io.swagger.client.model.ApiClient;
import io.swagger.client.model.ApiException;
import io.swagger.client.model.baseUris.BaseUriInfo;

@Component(immediate = true, service = AdobeSignApiClient.class, configurationPid = AdobeSignConstants.CLIENT_PID)
public class SimpleAdobeSignApiClient implements AdobeSignApiClient {

    private ComponentContext context;

    @Reference
    public AdobeSignAuthentication adobeSignAuthentication;

    @Override
    public ApiClient buildClient(String baseUrl, String endpointUrl) throws AdobeSignServiceException {
        ApiClient apiClient = new ApiClient();

        try {
            apiClient.setBasePath(baseUrl + endpointUrl);

            // Get the baseUris for the user and set it in apiClient.
            BaseUrisApi baseUrisApi = new BaseUrisApi(apiClient);
            BaseUriInfo baseUriInfo = baseUrisApi.getBaseUris(this.adobeSignAuthentication.authenticate());
            apiClient.setBasePath(baseUriInfo.getApiAccessPoint() + endpointUrl);
        } catch (ApiException e) {
            throw new AdobeSignServiceException(e.getMessage(), e);
        }

        return apiClient;
    }

    @Override
    public ApiClient buildClient() throws AdobeSignServiceException {
        ApiClient apiClient = new ApiClient();
       
        try {
            String baseUrl = this.context.getProperties().get(AdobeSignConstants.API_BASE_URL).toString();
            String endpointUrl = this.context.getProperties().get(AdobeSignConstants.API_ENDPOINT_URL).toString();
            
            apiClient.setBasePath(baseUrl + endpointUrl);
            
            // Get the baseUris for the user and set it in apiClient.
            BaseUrisApi baseUrisApi = new BaseUrisApi(apiClient);
            BaseUriInfo baseUriInfo = baseUrisApi.getBaseUris(this.adobeSignAuthentication.authenticate());
            apiClient.setBasePath(baseUriInfo.getApiAccessPoint() + endpointUrl);
        } catch (ApiException e) {
            throw new AdobeSignServiceException(e.getMessage(), e);
        }

        return apiClient;
    }

    @Activate
    public void activate(ComponentContext context) {
        this.context = context;
    }

    @Modified
    public void modified(ComponentContext context) {
        this.context = context;
    }

    @Deactivate
    public void deactivate(ComponentContext context) {
        this.context = null;
    }

}
