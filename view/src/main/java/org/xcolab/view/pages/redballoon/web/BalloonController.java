package org.xcolab.view.pages.redballoon.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.tracking.IBalloonClient;
import org.xcolab.client.tracking.exceptions.BalloonLinkNotFoundException;
import org.xcolab.client.tracking.exceptions.BalloonTextNotFoundException;
import org.xcolab.client.tracking.pojo.IBalloonLink;
import org.xcolab.client.tracking.pojo.IBalloonText;
import org.xcolab.client.tracking.pojo.IBalloonUserTracking;
import org.xcolab.client.tracking.pojo.tables.pojos.BalloonLink;
import org.xcolab.commons.exceptions.ReferenceResolutionException;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.view.pages.redballoon.utils.BalloonService;
import org.xcolab.view.pages.redballoon.web.beans.UserEmailBean;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static org.xcolab.util.http.exceptions.ExceptionUtils.getOptional;

@Controller
public class BalloonController {

    private static final String HOME_VIEW = "redballoon/view";
    private static final String SHARE_VIEW = "redballoon/sharePage";

    private final IBalloonClient balloonClient;
    private final BalloonService balloonService;

    @Autowired
    public BalloonController(BalloonService balloonService, IBalloonClient balloonClient) {
        this.balloonService = balloonService;
        this.balloonClient = balloonClient;
    }

    @GetMapping("/snp/socialnetworkprize")
    public String showBalloon(HttpServletRequest request, HttpServletResponse response, Model model,
            UserWrapper member) {

        IBalloonUserTracking but =
                balloonService.getOrCreateBalloonUserTracking(request, response, null, null);
        try {
            IBalloonLink balloonLink = balloonClient.getBalloonLink(but.getUuid());
            return "redirect:" + balloonLink.getTargetUrl();
        } catch (BalloonLinkNotFoundException e) {
            // user has no link -> continue and show page
        }

        if (but.getBalloonTextId() != null && but.getBalloonTextId() > 0) {
            IBalloonText text;
            try {
                text = balloonClient.getBalloonText(but.getBalloonTextId());

            } catch (BalloonTextNotFoundException e) {
                text = null;
            }

            model.addAttribute("balloonText", text);
        }

        if (!model.containsAttribute("userEmailBean")) {
            UserEmailBean ueb = new UserEmailBean();

            if (member != null && member.getId() > 0) {
                ueb.setEmail(member.getEmailAddress());
            }
            model.addAttribute("userEmailBean", ueb);
        }
        populateModelWithModalTexts(model);
        return HOME_VIEW;
    }

    @PostMapping("/snp/socialnetworkprize")
    public String requestLink(HttpServletRequest request, HttpServletResponse response, Model model,
            UserWrapper member, @RequestParam(required = false) String redirect,
            @Valid UserEmailBean userEmailBean, BindingResult bindingResult)
            throws BalloonTextNotFoundException {

        populateModelWithModalTexts(model);

        if (userEmailBean == null || bindingResult.hasErrors()) {
            return showBalloon(request, response, model, member);
        }

        IBalloonUserTracking but =
                balloonService.getOrCreateBalloonUserTracking(request, response, null, null);

        try {
            IBalloonLink balloonLink = balloonClient.getBalloonLink(but.getUuid());
            return "redirect:" + balloonLink.getTargetUrl();
        } catch (BalloonLinkNotFoundException e) {
            // user has no link -> continue and create one
        }

        but.setEmail(userEmailBean.getEmail());
        but.setFormFiledDate(new Timestamp(new Date().getTime()));

        balloonClient.updateBalloonUserTracking(but, but.getUuid());

        // create link to be used by user
        final IBalloonLink link = balloonService.createBalloonLink(userEmailBean.getEmail(), but);

        final String redirectUrl = StringUtils.isEmpty(redirect) ? link.getTargetUrl() : redirect;
        return "redirect:" + redirectUrl;
    }

    @GetMapping(BalloonService.SNP_LINK_URL)
    public String showLink(HttpServletRequest request, HttpServletResponse response, Model model,
            UserWrapper member, @PathVariable String linkUuid) throws ParserConfigurationException {

        populateModelWithModalTexts(model);

        IBalloonLink link = getBalloonLink(linkUuid);

        model.addAttribute("balloonLink", link);

        // get user tracking information, if user is new, then owner of this link should be set
        // as a parent

        IBalloonUserTracking but = balloonService
                .getOrCreateBalloonUserTracking(request, response, link.getBalloonUserUuid(),
                        linkUuid);

        link.setVisits(link.getVisits() + 1);
        try {
            balloonClient.updateBalloonLink(link, link.getUuid());
        } catch (BalloonLinkNotFoundException e) {
            // do nothing
        }

        if (but == null) {
            // user wasn't following any link so we need to create new root of a reference tree
            but = balloonService.getOrCreateBalloonUserTracking(request, response, null, null);
        }
        if (but.getBalloonTextId() != null && but.getBalloonTextId() > 0) {
            IBalloonText text;
            try {
                text = balloonClient.getBalloonText(but.getBalloonTextId());

            } catch (BalloonTextNotFoundException e) {
                text = null;
            }

            model.addAttribute("balloonText", text);

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

            Element element = doc.createElement("meta");

            element.setAttribute("property", "og:title");
            element.setAttribute("content", text != null ? text.getShareTitle() : "");


            model.addAttribute("meta1", element);
            element = doc.createElement("meta");
            element.setAttribute("property", "og:description");
            element.setAttribute("content", text != null ? text.getShareDescription() : "");


            model.addAttribute("meta2", element);
        }

        if (StringUtils.isNotBlank(but.getEmail())) {
            try {
                IBalloonLink bl = balloonClient.getBalloonLink(but.getUuid());
                model.addAttribute("shareLink", LinkUtils.getAbsoluteUrl(bl.getTargetUrl()));
                model.addAttribute("balloonLink", bl);
                return SHARE_VIEW;
            } catch (BalloonLinkNotFoundException e) {
                //fall through
            }
        }

        return showBalloon(request, response, model, member);
    }

    private IBalloonLink getBalloonLink(@PathVariable String linkUuid) {
        return getOptional(() -> balloonClient.getBalloonLink(linkUuid)).orElseThrow(
                () -> ReferenceResolutionException.toObject(BalloonLink.class, linkUuid).build());
    }

    private void populateModelWithModalTexts(Model model) {
        final String lang = LocaleContextHolder.getLocale().getLanguage();

        final String consentFormText = ConfigurationAttributeKey.SNP_CONSENT_FORM_TEXT.get(lang);
        final String exampleText = ConfigurationAttributeKey.SNP_EXAMPLE_TEXT.get(lang);
        model.addAttribute("consentFormText", consentFormText);
        model.addAttribute("exampleText", exampleText);
    }
}
