package org.xcolab.portlets.redballoon.utils;

import com.ext.portlet.NoSuchBalloonUserTrackingException;
import com.ext.portlet.model.BalloonLink;
import com.ext.portlet.model.BalloonText;
import com.ext.portlet.model.BalloonUserTracking;
import com.ext.portlet.service.BalloonTextLocalServiceUtil;
import com.ext.portlet.service.BalloonUserTrackingLocalServiceUtil;
import com.ext.utils.iptranslation.Location;
import com.ext.utils.iptranslation.service.IpTranslationServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import org.xcolab.portlets.redballoon.BalloonCookie;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class BalloonUtils {
	
	private static Random rand = new Random();
	private final static String SHARE_LINK_PATTERN = "%s/socialnetworkprize2015/-/link/%s";
	private final static Log _log = LogFactoryUtil.getLog(BalloonUtils.class);
	
	public static BalloonUserTracking getBalloonUserTracking(PortletRequest request, PortletResponse response, 
			String parent, String linkuuid, String context) throws PortalException, SystemException {
		BalloonCookie cookie = BalloonCookie.fromCookieArray(request.getCookies());
		
		ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		User user = td.getUser();
		if (cookie.getUuid() == null) {
			if (td.getUserId() > 0 && td.getUserId() != td.getDefaultUserId()) {
				cookie.setUuid(user.getUuid());
			}
			else {
				cookie.setUuid(UUID.randomUUID().toString());
			}
			response.addProperty(cookie.getHttpCookie());
		}
		
		BalloonUserTracking but = null;
		try { 
			but = BalloonUserTrackingLocalServiceUtil.getBalloonUserTracking(cookie.getUuid());
		}
		catch (NoSuchBalloonUserTrackingException e) {
			if (! user.getDefaultUser()) {
				List<BalloonUserTracking> buts = BalloonUserTrackingLocalServiceUtil.findByEmail(user.getEmailAddress());
				if (! buts.isEmpty()) {
					but = buts.get(0);
					but.setUserUuid(user.getUuid());
					BalloonUserTrackingLocalServiceUtil.updateBalloonUserTracking(but);
					return but;
				}
			}
			
			
			HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(request);

			but = BalloonUserTrackingLocalServiceUtil.createBalloonUserTracking(cookie.getUuid());
			but.setCreateDate(new Date());
			but.setIp(PortalUtil.getHttpServletRequest(request).getRemoteAddr());
			but.setParent(parent);
			but.setBalloonLinkContext(context);
			but.setBalloonLinkUuid(linkuuid);
			but.setReferrer(httpServletRequest.getHeader("referer"));
			but.setUserAgent(httpServletRequest.getHeader("User-Agent"));
			// populate GeoLocation data
			try {
				Location location = IpTranslationServiceUtil.getLocationForIp(PortalUtil.getHttpServletRequest(request).getRemoteAddr());
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
			List<BalloonText> texts = BalloonTextLocalServiceUtil.getBalloonTextsEnabled(true);
			if (! texts.isEmpty()) {
				but.setBalloonTextId(texts.get(rand.nextInt(texts.size())).getId());
			}
			
			BalloonUserTrackingLocalServiceUtil.addBalloonUserTracking(but);
		}
		return but;
	}
	
	public static String getBalloonUrlForLink(PortletRequest request, BalloonLink bl) {

		// create URL that should be used when sharing
		String requestUrl = PortalUtil.getHttpServletRequest(request).getRequestURL().toString();
		// find first occurrence of / to get address and port
		String protocolHostPort = requestUrl.substring(0, requestUrl.indexOf("/", 10));
		
		return String.format(SHARE_LINK_PATTERN, protocolHostPort, bl.getUuid());
	}

}
