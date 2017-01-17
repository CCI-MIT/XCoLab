package org.xcolab.view.pages.loginregister;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;

public class BalloonCookie implements Serializable {
    public static final String COOKIE_NAME = "climatecolabBalloonCookie2016";

    private static final long serialVersionUID = 1L;
    private String uuid;
    private String extraDataLogged;

    public BalloonCookie() { }

    public BalloonCookie(BalloonCookie bc) {
        if (bc != null) {
            setUuid(bc.uuid);
            extraDataLogged = bc.extraDataLogged;
        }
    }

    public static BalloonCookie fromCookieArray(Cookie[] cookies) {
        if (cookies == null) {
            return new BalloonCookie();
        }
        for (Cookie c : cookies) {
            if (c.getName().equals(COOKIE_NAME)) {
                BalloonCookie bc = new BalloonCookie();
                try {
                    String[] vals = URLDecoder.decode(c.getValue(), "UTF-8").split("\\|");
                    if (vals.length > 0) {
                        bc.uuid = vals[0];
                        if (vals.length > 1) {
                            bc.extraDataLogged = vals[1];
                        }

                        return bc;
                    }
                } catch (IOException e) {
                    //
                }
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
            uuid = "";
        }
    }

    /**
     * convenience method for liferay
     */
    public String getCookieName() {
        return COOKIE_NAME;
    }

    public Cookie getHttpCookie() {
        Cookie cookie = new Cookie(COOKIE_NAME, toString());
        cookie.setMaxAge(Integer.MAX_VALUE);
        cookie.setPath("/");

        return cookie;
    }

    @Override
    public String toString() {
        try {
            return URLEncoder
                    .encode((uuid == null ? "" : uuid) + "|" + (extraDataLogged == null ? "" : extraDataLogged),
                            "UTF-8");
        } catch (UnsupportedEncodingException ignored) {
        }
        return "";
    }
}
