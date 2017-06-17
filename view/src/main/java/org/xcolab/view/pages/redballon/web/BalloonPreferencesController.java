package org.xcolab.view.pages.redballon.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.exceptions.BalloonTextNotFoundException;
import org.xcolab.view.pages.redballon.web.beans.AddEditBalloonTextBean;

@Controller
public class BalloonPreferencesController {

	@GetMapping("/redballoon/editPreferences")
	public String showBalloon(Model model) {
		
		model.addAttribute("balloonTexts", BalloonsClient.getAllEnabledBalloonTexts());
		
		return "edit/editBalloonConfiguration";
	}

	@PostMapping("editBalloonText/balloonTextId")
	public String editBalloonText(Model model, @RequestParam long balloonTextId) {
		try {
			if (balloonTextId > 0) {
				model.addAttribute("addEditBalloonText",
						new AddEditBalloonTextBean(BalloonsClient.getBalloonText(balloonTextId)));
			} else {
				model.addAttribute("addEditBalloonText", new AddEditBalloonTextBean());
			}
		} catch(BalloonTextNotFoundException ignored) {

		}
		
		return "edit/addEditBalloonText";
	}
}
