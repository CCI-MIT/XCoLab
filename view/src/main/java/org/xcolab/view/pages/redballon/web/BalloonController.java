package org.xcolab.view.pages.redballon.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.exceptions.BalloonUserTrackingNotFound;
import org.xcolab.client.balloons.pojo.BalloonLink;
import org.xcolab.client.balloons.pojo.BalloonText;
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.redballon.utils.BalloonUtils;
import org.xcolab.view.pages.redballon.web.beans.UserEmailBean;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@Controller
public class BalloonController {

    @GetMapping({"/redballoon/{context}/link/{linkUuid}","/{context}"})
    public String showBalloon(HttpServletRequest request, HttpServletResponse response, Model model,
            @PathVariable(required = false) String linkUuid,
            @PathVariable(required = false) String context,
            @Valid UserEmailBean userEmailBean, BindingResult bindingResult)
            throws IOException, ParserConfigurationException {

        BalloonUserTracking but = null;
        if (linkUuid != null) {

            BalloonLink link;
            try {
                link = BalloonsClient.getBalloonLink(linkUuid);
            } catch (BalloonUserTrackingNotFound balloonUserTrackingNotFound) {
                link = null;
            }

            if (link != null) {
                model.addAttribute("balloonLink", link);

                // get user tracking information, if user is new, then owner of this link should be set as a parent
                but = BalloonUtils.getBalloonUserTracking(request, response, link.getBalloonUserUuid(), linkUuid, context);
                link.setVisits(link.getVisits() + 1);
                BalloonsClient.updateBalloonLink(link);

            }
        }

        if (but == null) {
            // user wasn't following any link so we need to create new root of a reference tree
            but = BalloonUtils.getBalloonUserTracking(request, response, null, null, null);
        }
        if (but.getBalloonTextId() != null && but.getBalloonTextId() > 0) {
            BalloonText text;
            try {
                text = BalloonsClient.getBalloonText(but.getBalloonTextId());

            } catch (BalloonUserTrackingNotFound balloonUserTrackingNotFound) {
                text = null;
            }


            model.addAttribute("balloonText", text);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element element = doc.createElement("meta");

            element.setAttribute("property", "og:title");
            element.setAttribute("content", text != null ? text.getFacebookSubject() : "");


            model.addAttribute("meta1", element);
            element = doc.createElement("meta");
            element.setAttribute("property", "og:description");
            element.setAttribute("content", text != null ? text.getFacebookDescription() : "");


            model.addAttribute("meta2", element);
        }

        if (StringUtils.isNotBlank(but.getEmail())) {
            BalloonLink bl;
            try {
                bl = BalloonsClient.getBalloonLinkByMemberUuid(but.getUuid_());
            } catch (BalloonUserTrackingNotFound balloonUserTrackingNotFound) {
                bl = null;
            }

            model.addAttribute("shareLink", BalloonUtils.getBalloonUrlForLink(request, bl));
            model.addAttribute("balloonLink", bl);
            return "redballoon/sharePage";
        } else {
            if (userEmailBean != null && userEmailBean.getEmail() != null) {
                model.addAttribute("userEmailBean", userEmailBean);
            } else {
                UserEmailBean ueb = new UserEmailBean();

                Member member = MemberAuthUtil.getMemberOrNull(request);
                if (member != null && member.getId_() > 0) {
                    ueb.setEmail(member.getEmailAddress());
                }
                model.addAttribute("userEmailBean", ueb);
            }
            return "redballoon/view";
        }
    }
}
