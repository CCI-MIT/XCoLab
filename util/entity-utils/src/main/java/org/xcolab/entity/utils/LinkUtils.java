package org.xcolab.entity.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;

import javax.servlet.http.HttpServletRequest;

public final class LinkUtils {

    public static final String COLAB_URL = PlatformAttributeKey.COLAB_URL.get();

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
        return COLAB_URL + relativeUri;
    }

    /**
     * Takes an absolute URI and removes the scheme, host, and port (if present).
     *
     * @param uri some uri - absolute or relative
     * @return a relative uri
     */
    public static String getRelativeUri(String uri) {
        if (uri == null) {
            return null;
        }
        final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(uri);

        // Clear scheme, host, and port
        uriBuilder.scheme(null);
        uriBuilder.host(null);
        uriBuilder.port(-1);

        final String newUri = uriBuilder.toUriString();
        return StringUtils.isEmpty(newUri) ? "/" : newUri;
    }

    public static String getLocalUrl(String url) {
        return getLocalUrl(url, "/");
    }

    public static String getLocalUrl(String url, String defaultRedirect) {
        if (StringUtils.isNotBlank(url) && isLocalUrl(url)) {
            return url;
        }
        return defaultRedirect;
    }

    public static boolean isLocalUrl(String url) {
        return isRelativeUrl(url) || url.startsWith(PlatformAttributeKey.COLAB_URL.get());
    }

    public static boolean isRelativeUrl(String url) {
        return url.startsWith("/");
    }

    public static boolean isLoginPageLink(String url) {
        final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url);
        return uriBuilder.build().getPath().startsWith("/login");
    }

    public static String getSafeRedirectUri(String uri) {
        return getSafeRedirectUri(uri, "/");
    }

    public static String getSafeRedirectUri(String uri, String defaultUri) {
        if (isLoginPageLink(uri)) {
            return defaultUri;
        }
        return getLocalUrl(uri, defaultUri);
    }
}
