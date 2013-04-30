/**
 * 
 */
package org.xcolab.portlets.redballoon;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;

/**
 * @author pdeboer
 * 
 */
public class BalloonCookie implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
	 * 
	 */
    private String email;
    private String uuid;
    private String url;

    public static final String COOKIE_NAME = "climatecolabBalloonCookie";

    /**
	 * 
	 */
    public BalloonCookie() {
    }

    public BalloonCookie(BalloonCookie bc) {
        if (bc != null) {
            setEmail(bc.email);
            setUuid(bc.uuid);
            setUrl(bc.getUrl());
        }
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param uuid
     *            the uuid to set
     */
    public void setUuid(String uuid) {
        if (uuid != null && !uuid.equals("null") && !uuid.equals(""))
            this.uuid = uuid;
        else {
            uuid = "";
        }
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    public static BalloonCookie fromCookieArray(Cookie[] cookies) {
        for (Cookie c : cookies) {
            if (c.getName().equals(COOKIE_NAME)) {
                BalloonCookie bc = new BalloonCookie();
                try {
                    String[] vals = URLDecoder.decode(c.getValue(), "UTF-8").split("\\|");
                    if (vals.length > 0) {
                        bc.uuid = vals[0];
                        if (vals.length > 1) {
                            bc.email = vals[1];
                        }
                        if (vals.length == 3) {
                            bc.url = vals[2];
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
     * convenience method for liferay
     * 
     * @return
     */
    public String getCookieName() {
        return COOKIE_NAME;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        try {
            return URLEncoder.encode((uuid == null ? "" : uuid) + "|" + (email == null ? "" : email) + "|"
                    + (url == null ? "" : url), "UTF-8");
        } catch (IOException e) {
            //
        }
        return "";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
