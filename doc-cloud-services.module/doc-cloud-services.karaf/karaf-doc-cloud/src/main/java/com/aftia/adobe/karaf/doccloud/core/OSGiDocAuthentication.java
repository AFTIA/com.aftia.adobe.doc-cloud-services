package com.aftia.adobe.karaf.doccloud.core;

import java.io.FileInputStream;
import java.io.File;
import java.util.Dictionary;

import com.adobe.pdfservices.operation.auth.Credentials;
import com.aftia.adobe.doccloud.core.authentication.DocAuthentication;
import com.aftia.adobe.doccloud.core.exceptions.DocAuthenticationException;
import com.nimbusds.jose.util.StandardCharset;

import org.apache.commons.io.IOUtils;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

@Component(service = DocAuthentication.class, immediate = true, configurationPid = DocAuthenticationConstants.DOC_AUTHENTICATION_PID)
public class OSGiDocAuthentication implements DocAuthentication {

    private ComponentContext context;

    @Override
    public Credentials getCredentials() throws DocAuthenticationException {
        return Credentials.serviceAccountCredentialsBuilder().withAccountId(this.getAccountId())
                .withClientId(this.getClientId()).withClientSecret(this.getClientSecret())
                .withOrganizationId(this.getOrganizationId()).withPrivateKey(this.getPrivateKey()).build();
    }

    @Activate
    public void activate(ComponentContext context) throws Exception {
        this.context = context;
    }

    @Modified
    public void modified(ComponentContext context) throws Exception {
        this.context = context;
    }

    @Deactivate
    public void deactivate() throws Exception {
        this.context = null;
    }

    public String getClientId() {
        Dictionary<String, Object> dic = this.context.getProperties();
        return dic.get(DocAuthenticationConstants.CLIENT_ID).toString();
    }

    public String getClientSecret() {
        return this.context.getProperties().get(DocAuthenticationConstants.CLIENT_SECRET).toString();
    }

    public String getAccountId() {
        return this.context.getProperties().get(DocAuthenticationConstants.ACCOUNT_ID).toString();
    }

    public String getOrganizationId() {
        return this.context.getProperties().get(DocAuthenticationConstants.ORGANIZATION_ID).toString();
    }

    public String getPrivateKey() throws DocAuthenticationException {
        try {
            String privateKeyUrl = this.context.getProperties().get(DocAuthenticationConstants.PRIVATE_KEY_URL).toString();
            if (privateKeyUrl.startsWith("file://")) {
                String path = privateKeyUrl.replace("file://", "");
                return IOUtils.toString(new FileInputStream(new File(path)), StandardCharset.UTF_8);
            }
        } catch (Exception e) {
            throw new DocAuthenticationException(e.getMessage(), e);
        }

        throw new DocAuthenticationException("Method used to load private key is not valid please provide a valid URL");

    }

}
