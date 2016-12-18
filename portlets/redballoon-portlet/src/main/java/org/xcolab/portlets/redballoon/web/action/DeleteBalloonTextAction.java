package org.xcolab.portlets.redballoon.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.balloons.BalloonsClient;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Controller
@RequestMapping("edit")
public class DeleteBalloonTextAction {

	@RequestMapping(params={"balloonTextId", "action=deleteBalloonText"})
	private void execute(ActionRequest request, ActionResponse response, @RequestParam long balloonTextId) {
		BalloonsClient.deleteBalloonText(balloonTextId);
	}

}
