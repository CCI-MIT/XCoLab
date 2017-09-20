package org.xcolab.view.util.googleanalytics;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class GoogleAnalyticsUtils {

    private static final Logger _log = LoggerFactory.getLogger(GoogleAnalyticsUtils.class);

    private static final String APPLICATION_NAME = ConfigurationAttributeKey.COLAB_NAME.get();
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String KEY_FILE_LOCATION =
            PlatformAttributeKey.ANALYTICS_PRIVATE_KEY_PATH.get();
    private static final String GOOGLE_ANALYTICS_KEY =
            ConfigurationAttributeKey.GOOGLE_ANALYTICS_KEY.get();
    private static final String GOOGLE_ANALYTICS_URL = "https://www.google-analytics.com/collect";

    public static void pushEventAsync(GoogleAnalyticsEventType googleAnalyticsEventType) {
        pushEventAsync(googleAnalyticsEventType, null);
    }

    public static void pushEventAsync(GoogleAnalyticsEventType googleAnalyticsEventType,
            String value) {

        try {
            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            GoogleCredential credential =
                    GoogleCredential.fromStream(new FileInputStream(KEY_FILE_LOCATION))
                            .createScoped(AnalyticsScopes.all());

            // Construct the Analytics service object.
            Analytics analytics = new Analytics.Builder(httpTransport, JSON_FACTORY, credential)
                    .setApplicationName(APPLICATION_NAME).build();

            HttpRequest httpRequest =
                    analytics.management().accounts().list().buildHttpRequestUsingHead();
            httpRequest.setRequestMethod("POST");
            GenericUrl url = new GenericUrl(GOOGLE_ANALYTICS_URL);
            httpRequest.setUrl(url);
            url.put("v", "1");
            url.put("tid", GOOGLE_ANALYTICS_KEY);
            url.put("cid", "555");
            url.put("t", "event");
            url.put("ec", googleAnalyticsEventType.getEventCategory());
            url.put("ea", googleAnalyticsEventType.getEventAction());
            url.put("el", googleAnalyticsEventType.getEventLabel());
            if (value != null) {
                url.put("ev", value);
            }

            httpRequest.executeAsync();

        } catch (IOException | GeneralSecurityException e) {
            _log.error("Not able to send Google Analytics event " + googleAnalyticsEventType
                    .getEventCategory() + " " + googleAnalyticsEventType.getEventAction() + " " +
            googleAnalyticsEventType.getEventLabel());
        }
    }
}

