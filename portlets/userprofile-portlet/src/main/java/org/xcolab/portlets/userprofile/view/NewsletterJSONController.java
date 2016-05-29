package org.xcolab.portlets.userprofile.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.client.members.MembersClient;
import org.xcolab.portlets.userprofile.utils.JSONHelper;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;

@Controller
@RequestMapping("view")
public class NewsletterJSONController extends JSONHelper {

    public NewsletterJSONController() { }

    @ResourceMapping("newsletterSubscribe")
    public @ResponseBody void handleNewsletterSubscribeAJAXRequest(
            PortletRequest request,
            ResourceResponse response,
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
            PortletRequest request,
            ResourceResponse response,
            @RequestParam long userId) {

        try {
            boolean isMemberUnsubscribed = MembersClient.unsubscribeFromNewsletter(userId);
            this.writeSuccessResultResponseJSON(isMemberUnsubscribed, response);
        } catch (HttpClientErrorException e) {
            this.writeSuccessResultResponseJSON(false, response);
        }
    }
}
