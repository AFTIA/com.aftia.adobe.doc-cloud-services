package com.aftia.adobe.karaf.adobesign.core.agreement;

import com.aftia.adobe.adobesign.core.authentication.AdobeSignAuthentication;
import com.aftia.adobe.adobesign.core.client.AdobeSignApiClient;
import com.aftia.adobe.adobesign.core.exceptions.AdobeSignServiceException;

import io.swagger.client.model.agreements.AgreementCreationInfo;

public interface AgreementService {

    public String createAgreement(AdobeSignApiClient apiClient, AdobeSignAuthentication authentication,
            AgreementCreationInfo agreementInfo, String xApiUser, String xOnBehalfOfUser)
            throws AdobeSignServiceException;
}
