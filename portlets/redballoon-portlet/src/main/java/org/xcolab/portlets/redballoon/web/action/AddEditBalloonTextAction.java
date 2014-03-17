package org.xcolab.portlets.redballoon.web.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.redballoon.web.beans.AddEditBalloonTextBean;

import com.ext.portlet.model.BalloonText;
import com.ext.portlet.service.BalloonTextLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("edit")
public class AddEditBalloonTextAction {
	
	@RequestMapping(params={"balloonTextId", "action=addEditBalloonText"})
	private void execute(ActionRequest request, ActionResponse response, AddEditBalloonTextBean addEditBalloonTextBean) throws PortalException, SystemException {
		BalloonText balloonText = null;
		
		if (addEditBalloonTextBean.getBalloonTextId() > 0) 
			balloonText = BalloonTextLocalServiceUtil.getBalloonText(addEditBalloonTextBean.getBalloonTextId());
		else 
			balloonText = BalloonTextLocalServiceUtil.createBalloonText(CounterLocalServiceUtil.increment(BalloonText.class.getName()));
		
		balloonText.setName(addEditBalloonTextBean.getName());
		balloonText.setTextAfterForm(addEditBalloonTextBean.getTextAfterForm());
		balloonText.setTextBeforeForm(addEditBalloonTextBean.getTextBeforeForm());
		balloonText.setTextAfterShareButtons(addEditBalloonTextBean.getTextAfterShareButtons());
		balloonText.setTextBeforeShareButtons(addEditBalloonTextBean.getTextBeforeShareButtons());
		balloonText.setEnabled(addEditBalloonTextBean.isEnabled());
		balloonText.setAcceptTosText(addEditBalloonTextBean.getAcceptTosText());
		
		if (addEditBalloonTextBean.getBalloonTextId() > 0) 
			BalloonTextLocalServiceUtil.updateBalloonText(balloonText);
		else 
			BalloonTextLocalServiceUtil.addBalloonText(balloonText);
		
	}

}
