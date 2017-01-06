package org.xcolab.view.pages.redballon.web;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.exceptions.BalloonUserTrackingNotFound;
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.view.pages.loginregister.BalloonCookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BalloonTrackingServlet extends HttpServlet {
	private final static Logger _log = LoggerFactory.getLogger(BalloonTrackingServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		BalloonCookie bc = BalloonCookie.fromCookieArray(req.getCookies());
		
		if (bc == null || StringUtils.isBlank(bc.getUuid())) {
			return;
		}
		
		try {
			BalloonUserTracking but = BalloonsClient.getBalloonUserTracking(bc.getUuid());

			but.setLatitude(Double.parseDouble(req.getParameter("latitude")));
			but.setLongitude(Double.parseDouble(req.getParameter("longitude")));
			
			BalloonsClient.updateBalloonUserTracking(but);

			
			//bc.setExtraDataLogged("true");
			resp.addCookie(bc.getHttpCookie());
			
		} catch (BalloonUserTrackingNotFound e) {
			_log.error("Can't find balloonUserTracking for uuid: {}", bc.getUuid());
		}
		
	}

}
