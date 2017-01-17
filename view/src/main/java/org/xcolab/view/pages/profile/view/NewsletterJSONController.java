package org.xcolab.view.pages.profile.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import org.xcolab.client.members.MembersClient;
import org.xcolab.view.pages.profile.utils.JSONHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("view")
public class NewsletterJSONController extends JSONHelper {

    public NewsletterJSONController() { }

    @ResourceMapping("newsletterSubscribe")
    public @ResponseBody void handleNewsletterSubscribeAJAXRequest(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam long userId) {

        try {
            boolean memberHasActiveSubscription = MembersClient.subscribeToNewsletter(userId);
            this.writeSuccessResultResponseJSON(memberHasActiveSubscription, response);
        } catch (HttpClientErrorException e) {
            this.writeSuccessResultResponseJSON(false, response);
        }

    }

    @ResourceMapping("newsletterUnSubscribe")
    public @ResponseBody void handleNewsletterUnSubscribeAJAXRequest(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam long userId) {

        try {
            boolean isMemberUnsubscribed = MembersClient.unsubscribeFromNewsletter(userId);
            this.writeSuccessResultResponseJSON(isMemberUnsubscribed, response);
        } catch (HttpClientErrorException e) {
            this.writeSuccessResultResponseJSON(false, response);
        }
    }
}
