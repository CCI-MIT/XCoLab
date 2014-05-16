package org.xcolab.portlets.redballoon.web.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ext.portlet.service.BalloonTextLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("edit")
public class DeleteBalloonTextAction {

	@RequestMapping(params={"balloonTextId", "action=deleteBalloonText"})
	private void execute(ActionRequest request, ActionResponse response, @RequestParam long balloonTextId) throws PortalException, SystemException {
		BalloonTextLocalServiceUtil.deleteBalloonText(balloonTextId);
	}

}
