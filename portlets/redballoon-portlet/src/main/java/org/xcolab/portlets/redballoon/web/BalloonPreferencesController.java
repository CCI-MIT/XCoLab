package org.xcolab.portlets.redballoon.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.redballoon.web.beans.AddEditBalloonTextBean;

import com.ext.portlet.service.BalloonTextLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

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
		
		if (balloonTextId > 0) {
			model.addAttribute("addEditBalloonText", new AddEditBalloonTextBean(BalloonTextLocalServiceUtil.getBalloonText(balloonTextId)));
		}
		else {
			model.addAttribute("addEditBalloonText", new AddEditBalloonTextBean());			
		}
		
		
		return "edit/addEditBalloonText";
	}
}
