package com.aftia.adobe.adobesign.core.services.agreement;

import com.aftia.adobe.adobesign.core.authentication.AdobeSignAuthentication;
import com.aftia.adobe.adobesign.core.client.AdobeSignApiClient;
import com.aftia.adobe.adobesign.core.exceptions.AdobeSignServiceException;

import io.swagger.client.api.AgreementsApi;
import io.swagger.client.model.ApiException;
import io.swagger.client.model.agreements.AgreementCreationInfo;
import io.swagger.client.model.agreements.AgreementCreationResponse;

public class Agreement {

    public static String generateAgreement(AdobeSignApiClient apiClient, AdobeSignAuthentication authentication,
            AgreementCreationInfo agreementInfo, String xApiUser, String xOnBehalfOfUser)
            throws AdobeSignServiceException {
        try {
            AgreementsApi agreementsApi = new AgreementsApi(apiClient.buildClient());
            AgreementCreationResponse agreementCreationResponse = agreementsApi.createAgreement(
                    String.format("Bearer %s", authentication.authenticate()), agreementInfo, xApiUser,
                    xOnBehalfOfUser);
            return agreementCreationResponse.getId();
        } catch (ApiException e) {
            throw new AdobeSignServiceException(e.getMessage(), e);
        }

    }

}
