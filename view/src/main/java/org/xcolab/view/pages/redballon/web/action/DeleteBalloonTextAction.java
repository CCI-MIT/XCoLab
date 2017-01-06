package org.xcolab.view.pages.redballon.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.balloons.BalloonsClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("edit")
public class DeleteBalloonTextAction {

	@RequestMapping(params={"balloonTextId", "action=deleteBalloonText"})
	private void execute(HttpServletRequest request, HttpServletResponse response, @RequestParam long balloonTextId) {
		BalloonsClient.deleteBalloonText(balloonTextId);
	}

}
