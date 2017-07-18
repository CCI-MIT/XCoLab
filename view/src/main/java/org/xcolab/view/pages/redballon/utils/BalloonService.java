package org.xcolab.view.pages.redballon.utils;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.exceptions.BalloonUserTrackingNotFoundException;
import org.xcolab.client.balloons.pojo.BalloonText;
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.tracking.TrackingClient;
import org.xcolab.client.tracking.pojo.Location;
import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.pages.loginregister.BalloonCookie;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class BalloonService {

    private final AuthenticationService authenticationService;

    @Autowired
    public BalloonService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public BalloonUserTracking getBalloonUserTracking(HttpServletRequest request,
			HttpServletResponse response, String parent, String linkUuid, String context) {
		BalloonCookie cookie = BalloonCookie.fromCookieArray(request.getCookies());

		Member member = authenticationService.getRealMemberOrNull();
		if (cookie.getUuid() == null) {
            if (member != null && member.getId_() > 0 ) {
                cookie.setUuid(member.getUuid());
            } else {
                cookie.setUuid(UUID.randomUUID().toString());
            }

            response.addCookie(cookie.getHttpCookie());
		}
		
		BalloonUserTracking but = null;
		if (StringUtils.isNotBlank(cookie.getUuid())) {
			try {
				but = BalloonsClient.getBalloonUserTracking(cookie.getUuid());
			} catch (BalloonUserTrackingNotFoundException ignored) {
			}
		}
		if (but == null) {
			if (member != null && member.getId_() > 0) {
				List<BalloonUserTracking> buts = BalloonsClient
						.getBalloonUserTrackingByEmail(member.getEmailAddress());
				if (!buts.isEmpty()) {
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
			but.setBalloonLinkUuid(linkUuid);
			but.setReferrer(request.getHeader(HttpHeaders.REFERER));
			but.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
			// populate GeoLocation data
            Location location = TrackingClient.getLocationForIp(request.getRemoteAddr());
            if (location != null) {
                but.setCity(location.getCity());
                but.setCountry(location.getCountry());
                but.setLatitude(location.getLatitude());
                but.setLongitude(location.getLongitude());
            }

			if (member != null && member.getId_() > 0) {
				but.setUserId(member.getUserId());
			}

			// pick random balloon text to be displayed
			List<BalloonText> texts = BalloonsClient.getAllEnabledBalloonTexts();
			if (!texts.isEmpty()) {
				but.setBalloonTextId(texts.get(RandomUtils.nextInt(0, texts.size())).getId_());
			} else {
				but.setBalloonTextId(0L);
			}

			BalloonsClient.createBalloonUserTracking(but);
		}
		return but;
	}

}
