package org.xcolab.view.pages.loginregister;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.util.exceptions.InternalException;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.Cookie;

public class BalloonCookie implements Serializable {

    //TODO: include year?
    private static final String COOKIE_NAME = "xcolabBalloonCookie";

    private static final long serialVersionUID = 1L;
    private String uuid;

    private BalloonCookie() { }

    public static BalloonCookie fromCookieArray(Cookie[] cookies) {
        if (cookies == null) {
            return new BalloonCookie();
        }
        final Optional<Cookie> cookieOptional = Arrays.stream(cookies)
                .filter(c -> c.getName().equals(COOKIE_NAME))
                .findFirst();
        if (cookieOptional.isPresent()) {
            BalloonCookie bc = new BalloonCookie();
            String stringUuid = decodeValue(cookieOptional.get().getValue());
            if (StringUtils.isNotEmpty(stringUuid)) {
                bc.uuid = stringUuid;
                return bc;
            }
        }
        return new BalloonCookie();
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        if (uuid != null && !uuid.equals("null") && !uuid.equals("")) {
            this.uuid = uuid;
        } else {
            this.uuid = "";
        }
    }

    public Cookie getHttpCookie() {
        Cookie cookie = new Cookie(COOKIE_NAME, encodeValue(uuid));
        cookie.setMaxAge(Integer.MAX_VALUE);
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
