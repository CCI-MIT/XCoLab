package org.xcolab.view.pages.redballon.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.exceptions.BalloonLinkNotFoundException;
import org.xcolab.client.balloons.exceptions.BalloonTextNotFoundException;
import org.xcolab.client.balloons.pojo.BalloonLink;
import org.xcolab.client.balloons.pojo.BalloonText;
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.redballon.utils.BalloonUtils;
import org.xcolab.view.pages.redballon.web.beans.UserEmailBean;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@Controller
public class BalloonController {

    private static final String HOME_VIEW = "redballoon/view";
    private static final String SHARE_VIEW = "redballoon/sharePage";

    private static final String URL_PLACEHOLDER = "URLPLACEHOLDER";

    private static final String SNP_LINK_URL = "/snp/{context}/link/{linkUuid}";

    @GetMapping("/snp/{context}")
    public String showBalloon(HttpServletRequest request,
            HttpServletResponse response, Model model, @PathVariable String context) {
        BalloonUserTracking but = BalloonUtils.getBalloonUserTracking(request, response,
                null, null, context);
        if (but.getBalloonTextId() != null && but.getBalloonTextId() > 0) {
            BalloonText text;
            try {
                text = BalloonsClient.getBalloonText(but.getBalloonTextId());

            } catch (BalloonTextNotFoundException e) {
                text = null;
            }

            model.addAttribute("balloonText", text);
        }


        if (!model.containsAttribute("userEmailBean")) {
            UserEmailBean ueb = new UserEmailBean();

            Member member = MemberAuthUtil.getMemberOrNull(request);
            if (member != null && member.getId_() > 0) {
                ueb.setEmail(member.getEmailAddress());
            }
            model.addAttribute("userEmailBean", ueb);
        }
        return HOME_VIEW;
    }

    @PostMapping("/snp/{context}")
    public String requestLik(HttpServletRequest request, HttpServletResponse response,
            Model model, @PathVariable String context,
            @Valid UserEmailBean userEmailBean, BindingResult bindingResult)
            throws IOException, AddressException {

        if (userEmailBean == null || bindingResult.hasErrors()) {
            return showBalloon(request, response, model, context);
        }

        BalloonUserTracking but = BalloonUtils.getBalloonUserTracking(request, response, null, null, null);

        but.setEmail(userEmailBean.getEmail());
        but.setFormFiledDate(new Timestamp(new Date().getTime()));

        BalloonsClient.updateBalloonUserTracking(but);

        // create link to be used by user
        BalloonLink link = new BalloonLink();
        link.setUuid_(UUID.randomUUID().toString());

        link.setBalloonUserUuid(but.getUuid_());
        link.setCreateDate(new Timestamp(new Date().getTime()));
        final String linkUrl = getSnpLinkUrl(context, link.getUuid_());
        link.setTargetUrl(linkUrl);

        BalloonsClient.createBalloonLink(link);

        if (but.getBalloonTextId() > 0) {
            try {
                BalloonText text = BalloonsClient.getBalloonText(but.getBalloonTextId());
                String messageSubject = text.getEmailSubjectTemplate();
                String messageBody = text.getEmailTemplate()
                        .replaceAll(URL_PLACEHOLDER, LinkUtils.getAbsoluteUrl(link.getTargetUrl()));

                final String fromEmail = ConfigurationAttributeKey.ADMIN_FROM_EMAIL.get();
                EmailClient.sendEmail(fromEmail, userEmailBean.getEmail(), messageSubject, messageBody,
                        true, fromEmail, but.getBalloonTextId());

            } catch (BalloonTextNotFoundException ignored) {

            }
        }

        return "redirect:" + linkUrl;
    }

    private String getSnpLinkUrl(String context, String linkUuid) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("context", context);
        uriVariables.put("linkUuid", linkUuid);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(SNP_LINK_URL);
        return uriBuilder.buildAndExpand(uriVariables).toUriString();
    }

    @GetMapping(SNP_LINK_URL)
    public String showLink(HttpServletRequest request, HttpServletResponse response, Model model,
            @PathVariable(required = false) String linkUuid,
            @PathVariable(required = false) String context)
            throws IOException, ParserConfigurationException {

        BalloonUserTracking but = null;
        if (linkUuid != null) {

            BalloonLink link;
            try {
                link = BalloonsClient.getBalloonLink(linkUuid);
            } catch (BalloonLinkNotFoundException e) {
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

            } catch (BalloonTextNotFoundException e) {
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
            try {
                BalloonLink bl = BalloonsClient.getBalloonLinkByMemberUuid(but.getUuid_());
                model.addAttribute("shareLink", LinkUtils.getAbsoluteUrl(bl.getTargetUrl()));
                model.addAttribute("balloonLink", bl);
                return SHARE_VIEW;
            } catch (BalloonLinkNotFoundException e) {
                //fall through
            }
        }

        return showBalloon(request, response, model, context);
    }
}
