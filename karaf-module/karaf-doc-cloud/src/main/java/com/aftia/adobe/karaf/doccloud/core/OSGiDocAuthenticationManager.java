package com.aftia.adobe.karaf.doccloud.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = DocAuthenticationManager.class, immediate = true)
public class OSGiDocAuthenticationManager implements DocAuthenticationManager {

        @Reference
        private ConfigurationAdmin configurationAdmin;

        @Override
        public Dictionary<String, Object> updateAuthentication(String clientId, String clientSecret, String accountId,
                        String orgnizationId, InputStream privateKey) throws IOException {
                String here = new File(".").getCanonicalPath();
                here = here + File.separator + "private.key";
                FileOutputStream outFile = new FileOutputStream(new File(here));
                privateKey.transferTo(outFile);

                Configuration configuration = this.configurationAdmin
                                .getConfiguration(DocAuthenticationConstants.DOC_AUTHENTICATION_PID);
                Dictionary dup = this.duplicate(configuration.getProperties());
                dup.put(DocAuthenticationConstants.CLIENT_ID, clientId);
                dup.put(DocAuthenticationConstants.CLIENT_SECRET, clientSecret);
                dup.put(DocAuthenticationConstants.ACCOUNT_ID, accountId);
                dup.put(DocAuthenticationConstants.ORGANIZATION_ID, orgnizationId);
                dup.put(DocAuthenticationConstants.PRIVATE_KEY_URL, String.format("file://%s", here));

                this.configurationAdmin.getConfiguration(DocAuthenticationConstants.DOC_AUTHENTICATION_PID)
                                .updateIfDifferent(dup);

                return configuration.getProperties();
        }

        private Dictionary duplicate(Dictionary dic) {
                Dictionary<String, Object> dup = new Hashtable();

                Enumeration<String> keys = dic.keys();
                while (keys.hasMoreElements()) {
                        String key = keys.nextElement();
                        dup.put(key, dic.get(key));
                }

                return dup;
        }

        @Override
        public Dictionary<String, Object> getAuthentication() throws IOException {
                Configuration configuration = this.configurationAdmin
                                .getConfiguration(DocAuthenticationConstants.DOC_AUTHENTICATION_PID);
                return configuration.getProperties();
        }

}
