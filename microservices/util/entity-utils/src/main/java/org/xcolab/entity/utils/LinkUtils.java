package org.xcolab.entity.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;

import javax.servlet.http.HttpServletRequest;

public final class LinkUtils {

    private LinkUtils() {
    }

    public static String getBaseUri(HttpServletRequest request) {
        String baseUri = String.format("%s://%s", request.getScheme(), request.getServerName());
        int port = request.getServerPort();
        if (port != 80 && port != 443) {
            baseUri += ":" + port;
        }
        return baseUri;
    }

    public static String getNonBlankStringOrDefault(String string, String defaultString) {
        if (StringUtils.isNotBlank(string)) {
            return string;
        }
        return defaultString;
    }

    public static String getAbsoluteUrl(String relativeUri) {
        return PlatformAttributeKey.COLAB_URL.get() + relativeUri;
    }

    /**
     * Takes an absolute URI and removes the scheme, host, and port (if present).
     *
     * An empty, null, or already relative URI will be returned unchanged.
     *
     * @param uri some uri - absolute or relative
     * @return a relative uri
     */
    public static String getRelativeUri(String uri) {
        if (StringUtils.isNotEmpty(uri)) {
            if (uri.startsWith("http://") || uri.startsWith("https://")) {
                final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(uri);
                final UriComponents uriComponents = uriBuilder.build();
                StringBuilder relativeUri = new StringBuilder();
                relativeUri.append(uriComponents.getPath() != null ? uriComponents.getPath() : "/");
                if (uriComponents.getQuery() != null) {
                    relativeUri.append("?").append(uriComponents.getQuery());
                }
                return relativeUri.toString();
            }
        }
        return uri;
    }
}
