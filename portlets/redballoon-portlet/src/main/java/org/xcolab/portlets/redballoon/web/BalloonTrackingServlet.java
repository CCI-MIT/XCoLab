package org.xcolab.portlets.redballoon.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.xcolab.portlets.redballoon.BalloonCookie;

import com.ext.portlet.model.BalloonUserTracking;
import com.ext.portlet.service.BalloonUserTrackingLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class BalloonTrackingServlet extends HttpServlet {
	private final static Log _log = LogFactoryUtil.getLog(BalloonTrackingServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		BalloonCookie bc = BalloonCookie.fromCookieArray(req.getCookies());
		
		if (bc == null || StringUtils.isBlank(bc.getUuid())) return;
		
		try {
			BalloonUserTracking but = BalloonUserTrackingLocalServiceUtil.getBalloonUserTracking(bc.getUuid());
			but.setLatitude(Double.parseDouble(req.getParameter("latitude")));
			but.setLongitude(Double.parseDouble(req.getParameter("longitude")));
			
			BalloonUserTrackingLocalServiceUtil.updateBalloonUserTracking(but);
			
			bc.setExtraDataLogged("true");
			resp.addCookie(bc.getHttpCookie());
			
		} catch (PortalException | SystemException e) {
			_log.error("Can't find balloonUserTracking for uuid: " + bc.getUuid());
		}
		
	}

}
