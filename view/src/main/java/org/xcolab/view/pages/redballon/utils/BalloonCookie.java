package org.xcolab.view.pages.redballon.utils;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.util.exceptions.InternalException;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;

public class BalloonCookie implements Serializable {

    private static final int YEAR_IN_SECONDS = 31_536_000;

    private static final String COOKIE_PREFIX = "Balloon-";

    private static final long serialVersionUID = 1L;
    private final String uuid;

    private BalloonCookie(String uuid) {
        this.uuid = uuid;
    }

    public static Optional<BalloonCookie> from(Cookie[] cookies) {
        if (cookies != null) {
            final String cookieName = COOKIE_PREFIX + ConfigurationAttributeKey.SNP_CONTEXT.get();
            final Optional<Cookie> cookieOptional =
                    Arrays.stream(cookies).filter(c -> c.getName().equals(cookieName)).findFirst();
            if (cookieOptional.isPresent()) {
                String stringUuid = decodeValue(cookieOptional.get().getValue());
                if (StringUtils.isNotEmpty(stringUuid)) {
                    return Optional.of(new BalloonCookie(stringUuid));
                }
            }
        }
        return Optional.empty();
    }

    public static BalloonCookie of(String uuid) {
        return new BalloonCookie(uuid);
    }

    public String getUuid() {
        return uuid;
    }

    public Cookie getHttpCookie() {
        final String cookieName = COOKIE_PREFIX + ConfigurationAttributeKey.SNP_CONTEXT.get();
        Cookie cookie = new Cookie(cookieName, encodeValue(uuid));
        cookie.setMaxAge(YEAR_IN_SECONDS);
        cookie.setPath("/");
        return cookie;
    }

    private static String encodeValue(String value) {
        try {
            final String stringValue = value == null ? "" : value;
            return URLEncoder.encode(stringValue, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new InternalException(e);
        }
    }

    private static String decodeValue(String encodedValue) {
        try {
            return URLDecoder.decode(encodedValue, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new InternalException(e);
        }
    }

    @Override
    public String toString() {
        return "BalloonCookie[uuid = " + uuid + "]";
    }
}
