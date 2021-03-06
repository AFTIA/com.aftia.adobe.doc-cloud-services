package com.aftia.adobe.karaf.adobesign.core.agreement;

import com.aftia.adobe.adobesign.core.authentication.AdobeSignAuthentication;
import com.aftia.adobe.adobesign.core.client.AdobeSignApiClient;
import com.aftia.adobe.adobesign.core.exceptions.AdobeSignServiceException;
import com.aftia.adobe.adobesign.core.services.agreement.Agreement;

import io.swagger.client.model.agreements.AgreementViewInfo;
import org.osgi.service.component.annotations.Component;

import io.swagger.client.model.agreements.AgreementCreationInfo;

@Component(service = AgreementService.class, immediate = true)
public class SimpleAgreementService implements AgreementService {

    @Override
    public String createAgreement(AdobeSignApiClient apiClient, AdobeSignAuthentication authentication,
            AgreementCreationInfo agreementInfo, String xApiUser, String xOnBehalfOfUser)
            throws AdobeSignServiceException {
        return Agreement.generateAgreement(apiClient, authentication, agreementInfo, xApiUser, xOnBehalfOfUser);
    }

    @Override
    public String createAgreementView(AdobeSignApiClient apiClient, AdobeSignAuthentication authentication,
            String agreementId, AgreementViewInfo agreementViewInfo, String xApiUser, String xOnBehalfOfUser)
            throws AdobeSignServiceException {
        return Agreement.generateAgreementView(apiClient, authentication, agreementId, agreementViewInfo, xApiUser,
                xOnBehalfOfUser);
    }

}
