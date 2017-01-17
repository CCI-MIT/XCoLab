package org.xcolab.view.pages.redballon.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.exceptions.BalloonUserTrackingNotFound;
import org.xcolab.client.balloons.pojo.BalloonLink;
import org.xcolab.client.balloons.pojo.BalloonText;
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.tracking.TrackingClient;
import org.xcolab.client.tracking.pojo.Location;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.loginregister.BalloonCookie;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BalloonUtils {
	
	private static final Random rand = new Random();
	private final static String SHARE_LINK_PATTERN = "%s/redballoon/socialnetworkprize2016/link/%s";
	private final static Logger _log = LoggerFactory.getLogger(BalloonUtils.class);
	
	public static BalloonUserTracking getBalloonUserTracking(HttpServletRequest request,
			HttpServletResponse response, String parent, String linkuuid, String context) {
		BalloonCookie cookie = BalloonCookie.fromCookieArray(request.getCookies());
		

		Member member = MemberAuthUtil.getMemberOrNull(request);
		if (cookie.getUuid() == null) {
                if (member !=null && member.getId_() > 0 ) {
                    cookie.setUuid(member.getUuid());
                }
                else {
                    cookie.setUuid(UUID.randomUUID().toString());
                }

            response.addCookie(cookie.getHttpCookie());
		}
		
		BalloonUserTracking but;
		try { 
			but = BalloonsClient.getBalloonUserTracking(cookie.getUuid());
		}
		catch (BalloonUserTrackingNotFound e) {

			if (member !=null && member.getId_() > 0 ) {
				List<BalloonUserTracking> buts = BalloonsClient.getBalloonUserTrackingByEmail(member.getEmailAddress());
				if (! buts.isEmpty()) {
					but = buts.get(0);
					but.setUserId(member.getUserId());
					BalloonsClient.updateBalloonUserTracking(but);
					return but;
				}
			}

			


			but = new BalloonUserTracking();
			but.setUuid_(cookie.getUuid());
			but.setCreateDate(new Timestamp(new Date().getTime()));
			but.setIp(request.getRemoteAddr());
			but.setParent(parent);
			but.setBalloonLinkContext(context);
			but.setBalloonLinkUuid(linkuuid);
			but.setReferrer(request.getHeader("referer"));
			but.setUserAgent(request.getHeader("User-Agent"));
			// populate GeoLocation data
			try {
				Location location = TrackingClient
						.getLocationForIp(request.getRemoteAddr());
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
			 
			
			if (member !=null && member.getId_() > 0 ) {
				but.setUserId(member.getUserId());
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
	
	public static String getBalloonUrlForLink(HttpServletRequest request, BalloonLink bl) {

		// create URL that should be used when sharing
		String requestUrl = request.getRequestURL().toString();
		// find first occurrence of / to get address and port
		String protocolHostPort = requestUrl.substring(0, requestUrl.indexOf("/", 10));
		
		return String.format(SHARE_LINK_PATTERN, protocolHostPort, bl.getUuid_());
	}

}
