package org.xcolab.portlets.redballoon.web.action;


import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.exceptions.BalloonUserTrackingNotFound;
import org.xcolab.client.balloons.pojo.BalloonText;
import org.xcolab.portlets.redballoon.web.beans.AddEditBalloonTextBean;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Controller
@RequestMapping("edit")
public class AddEditBalloonTextAction {

    @RequestMapping(params = {"balloonTextId", "action=addEditBalloonText"})
    private void execute(ActionRequest request, ActionResponse response, AddEditBalloonTextBean addEditBalloonTextBean) throws PortalException, SystemException {
        BalloonText balloonText = null;

        if (addEditBalloonTextBean.getBalloonTextId() > 0) {

            try {
                balloonText = BalloonsClient.getBalloonText(addEditBalloonTextBean.getBalloonTextId());
            } catch (BalloonUserTrackingNotFound balloonUserTrackingNotFound) {
                balloonText = null;
            }
        } else {
            balloonText = new BalloonText();
            balloonText.setId_(CounterLocalServiceUtil.increment(com.ext.portlet.model.BalloonText.class.getName()));

        }
        balloonText.setName(addEditBalloonTextBean.getName());
        balloonText.setTextAfterForm(addEditBalloonTextBean.getTextAfterForm());
        balloonText.setTextBeforeForm(addEditBalloonTextBean.getTextBeforeForm());
        balloonText.setTextAfterShareButtons(addEditBalloonTextBean.getTextAfterShareButtons());
        balloonText.setTextBeforeShareButtons(addEditBalloonTextBean.getTextBeforeShareButtons());
        balloonText.setEnabled(addEditBalloonTextBean.isEnabled());
        balloonText.setAcceptTosText(addEditBalloonTextBean.getAcceptTosText());
        balloonText.setEmailTemplate(addEditBalloonTextBean.getEmailTemplate());
        balloonText.setEmailSubjectTemplate(addEditBalloonTextBean.getEmailSubjectTemplate());
        balloonText.setFacebookDescription(addEditBalloonTextBean.getFacebookDescription());
        balloonText.setFacebookSubject(addEditBalloonTextBean.getFacebookTitle());

        balloonText.setTwitterDescription(addEditBalloonTextBean.getTwitterDescription());
        balloonText.setTwitterSubject(addEditBalloonTextBean.getTwitterTitle());


        if (addEditBalloonTextBean.getBalloonTextId() > 0) {
            BalloonsClient.updateBalloonText(balloonText);
        } else {
            BalloonsClient.createBalloonText(balloonText);

        }

    }

}
