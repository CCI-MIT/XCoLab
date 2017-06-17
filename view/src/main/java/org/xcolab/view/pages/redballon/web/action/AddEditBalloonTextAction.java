package org.xcolab.view.pages.redballon.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.exceptions.BalloonTextNotFoundException;
import org.xcolab.client.balloons.pojo.BalloonText;
import org.xcolab.view.pages.redballon.web.beans.AddEditBalloonTextBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AddEditBalloonTextAction {

    @PostMapping("redballoon/addEditBalloonText")
    private String execute(HttpServletRequest request, HttpServletResponse response, AddEditBalloonTextBean addEditBalloonTextBean)  {
        BalloonText balloonText;

        if (addEditBalloonTextBean.getBalloonTextId() > 0) {

            try {
                balloonText = BalloonsClient.getBalloonText(addEditBalloonTextBean.getBalloonTextId());
            } catch (BalloonTextNotFoundException e) {
                balloonText = null;
            }
        } else {
            balloonText = new BalloonText();
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
        return "redballoon/view";
    }
}
