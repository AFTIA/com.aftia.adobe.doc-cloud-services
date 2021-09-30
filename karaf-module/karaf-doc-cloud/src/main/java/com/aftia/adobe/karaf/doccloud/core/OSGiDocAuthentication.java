package com.aftia.adobe.karaf.doccloud.core;

import java.io.FileInputStream;
import java.io.File;
import java.util.Dictionary;

import com.adobe.pdfservices.operation.auth.Credentials;
import com.adobe.pdfservices.operation.pdfops.options.extractpdf.ExtractElementType;
import com.adobe.pdfservices.operation.pdfops.options.extractpdf.ExtractPDFOptions;
import com.aftia.adobe.doccloud.core.authentication.DocAuthentication;
import com.aftia.adobe.doccloud.core.exceptions.DocAuthenticationException;
import com.aftia.adobe.karaf.doccloud.core.extractpdf.ExtractPDFServices;
import com.aftia.adobe.karaf.doccloud.core.mergedocument.MergeDocument;
import com.nimbusds.jose.util.StandardCharset;

import org.apache.commons.io.IOUtils;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

@Component(service = DocAuthentication.class, immediate = true, configurationPid = "com.aftia.adobe.karaf.doccloud.core.authentication.config")
public class OSGiDocAuthentication implements DocAuthentication {

    private ComponentContext context;
    private static final String CLIENT_ID = "doccloud.clientId";
    private static final String CLIENT_SECRET = "doccloud.clientSecret";
    private static final String ORGANIZATION_ID = "doccloud.organizationId";
    private static final String ACCOUNT_ID = "doccloud.accountId";
    private static final String PRIVATE_KEY_URL = "doccloud.privateKeyUrl";

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
        return dic.get(CLIENT_ID).toString();
    }

    public String getClientSecret() {
        return this.context.getProperties().get(CLIENT_SECRET).toString();
    }

    public String getAccountId() {
        return this.context.getProperties().get(ACCOUNT_ID).toString();
    }

    public String getOrganizationId() {
        return this.context.getProperties().get(ORGANIZATION_ID).toString();
    }

    public String getPrivateKey() throws DocAuthenticationException {
        try {
            String privateKeyUrl = this.context.getProperties().get(PRIVATE_KEY_URL).toString();
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
