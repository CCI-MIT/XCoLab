/**
 * 
 */
package org.xcolab.view.pages.redballon;

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
    private String uuid;
    private String extraDataLogged;

    public static final String COOKIE_NAME = "climatecolabBalloonCookie2016";

    /**
	 * 
	 */
    public BalloonCookie() {
    }

    public BalloonCookie(BalloonCookie bc) {
        if (bc != null) {
            setUuid(bc.uuid);
            extraDataLogged = bc.extraDataLogged;
        }
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
    	if(cookies == null) return new BalloonCookie();
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
            return URLEncoder.encode((uuid == null ? "" : uuid) + "|" + (extraDataLogged == null ? "" : extraDataLogged), "UTF-8");
        } catch (IOException e) {
            //
        }
        return "";
    }


	public Cookie getHttpCookie() {
		Cookie cookie = new Cookie(COOKIE_NAME, toString());
		cookie.setMaxAge(Integer.MAX_VALUE);
		cookie.setPath("/");
		cookie.setHttpOnly(false);
		
		return cookie;
	}

	public String getExtraDataLogged() {
		return extraDataLogged;
	}

	public void setExtraDataLogged(String extraDataLogged) {
		this.extraDataLogged = extraDataLogged;
	}
}