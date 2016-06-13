package org.xcolab.portlets.redballoon.web;

import com.ext.portlet.service.BalloonTextLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.exceptions.BalloonUserTrackingNotFound;
import org.xcolab.portlets.redballoon.web.beans.AddEditBalloonTextBean;

@RequestMapping("edit")
@Controller
public class BalloonPreferencesController {
	
	@RequestMapping
	public String showBalloon(Model model) throws SystemException {
		
		model.addAttribute("balloonTexts", BalloonTextLocalServiceUtil.getBalloonTexts(0, Integer.MAX_VALUE));
		
		return "edit/editBalloonConfiguration";
	}

	@RequestMapping(params="balloonTextId")
	public String editBalloonText(Model model, @RequestParam long balloonTextId) throws PortalException, SystemException {
		try {


			if (balloonTextId > 0) {
				model.addAttribute("addEditBalloonText", new AddEditBalloonTextBean(BalloonsClient.getBalloonText(balloonTextId)));
			} else {
				model.addAttribute("addEditBalloonText", new AddEditBalloonTextBean());
			}
		}catch(BalloonUserTrackingNotFound ignored){

		}
		
		return "edit/addEditBalloonText";
	}
}
