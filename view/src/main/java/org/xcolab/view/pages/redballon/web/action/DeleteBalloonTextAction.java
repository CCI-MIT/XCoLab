package org.xcolab.view.pages.redballon.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.balloons.BalloonsClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DeleteBalloonTextAction {

	@PostMapping("redballoon/deleteBalloonText")
	private String execute(HttpServletRequest request, HttpServletResponse response, @RequestParam long balloonTextId) {
		BalloonsClient.deleteBalloonText(balloonTextId);
		return "redballoon/view";
	}

}
