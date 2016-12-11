package org.xcolab.portlets.redballoon.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.exceptions.BalloonUserTrackingNotFound;
import org.xcolab.client.balloons.pojo.BalloonLink;
import org.xcolab.client.balloons.pojo.BalloonText;
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.client.tracking.TrackingClient;
import org.xcolab.client.tracking.pojo.Location;
import org.xcolab.entity.utils.portlet.PortletUtil;
import org.xcolab.portlets.redballoon.BalloonCookie;
import org.xcolab.util.exceptions.InternalException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;

public class BalloonUtils {
	
	private static final Random rand = new Random();
	private final static String SHARE_LINK_PATTERN = "%s/socialnetworkprize2016/-/link/%s";
	private final static Logger _log = LoggerFactory.getLogger(BalloonUtils.class);
	
	public static BalloonUserTracking getBalloonUserTracking(PortletRequest request,
			PortletResponse response, String parent, String linkuuid, String context) {
		BalloonCookie cookie = BalloonCookie.fromCookieArray(request.getCookies());
		
		ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		User user = td.getUser();
		if (cookie.getUuid() == null) {
            try {
                if (td.getUserId() > 0 && td.getUserId() != td.getDefaultUserId()) {
                    cookie.setUuid(user.getUuid());
                }
                else {
                    cookie.setUuid(UUID.randomUUID().toString());
                }
            } catch (PortalException | SystemException e) {
                throw new InternalException(e);
            }
            response.addProperty(cookie.getHttpCookie());
		}
		
		BalloonUserTracking but;
		try { 
			but = BalloonsClient.getBalloonUserTracking(cookie.getUuid());
		}
		catch (BalloonUserTrackingNotFound e) {

			if (! user.getDefaultUser()) {
				List<BalloonUserTracking> buts = BalloonsClient.getBalloonUserTrackingByEmail(user.getEmailAddress());
				if (! buts.isEmpty()) {
					but = buts.get(0);
					but.setUserId(user.getUserId());
					BalloonsClient.updateBalloonUserTracking(but);
					return but;
				}
			}

			
			HttpServletRequest httpServletRequest = PortletUtil.getHttpServletRequest(request);

			but = new org.xcolab.client.balloons.pojo.BalloonUserTracking();
			but.setUuid_(cookie.getUuid());
			but.setCreateDate(new Timestamp(new Date().getTime()));
			but.setIp(PortalUtil.getHttpServletRequest(request).getRemoteAddr());
			but.setParent(parent);
			but.setBalloonLinkContext(context);
			but.setBalloonLinkUuid(linkuuid);
			but.setReferrer(httpServletRequest.getHeader("referer"));
			but.setUserAgent(httpServletRequest.getHeader("User-Agent"));
			// populate GeoLocation data
			try {
				Location location = TrackingClient
						.getLocationForIp(PortalUtil.getHttpServletRequest(request).getRemoteAddr());
				if (location != null) {
					but.setCity(location.getCity());
					but.setCountry(location.getCountry());
					but.setLatitude(location.getLatitude());
					but.setLongitude(location.getLongitude());
				}
			}
			catch (Throwable t) {
				_log.error("Error when processing user location", t);
			}
			 
			
			if (! user.getDefaultUser()) {
				but.setUserId(user.getUserId());
			}
			
			// pick randomly balloon text to be displayed
			List<BalloonText> texts = BalloonsClient.getAllEnabledBalloonTexts();
			if (! texts.isEmpty()) {
				but.setBalloonTextId(texts.get(rand.nextInt(texts.size())).getId_());
			}

			BalloonsClient.createBalloonUserTracking(but);

		}
		return but;
	}
	
	public static String getBalloonUrlForLink(PortletRequest request, BalloonLink bl) {

		// create URL that should be used when sharing
		String requestUrl = PortalUtil.getHttpServletRequest(request).getRequestURL().toString();
		// find first occurrence of / to get address and port
		String protocolHostPort = requestUrl.substring(0, requestUrl.indexOf("/", 10));
		
		return String.format(SHARE_LINK_PATTERN, protocolHostPort, bl.getUuid_());
	}

}
