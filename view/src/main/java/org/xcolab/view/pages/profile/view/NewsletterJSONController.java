package org.xcolab.view.pages.profile.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import org.xcolab.client.members.MembersClient;
import org.xcolab.view.pages.profile.utils.JSONHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/members/profile/{memberId}/api/settings/newsletter")
public class NewsletterJSONController extends JSONHelper {

    public NewsletterJSONController() { }

    @PostMapping("subscribe")
    public @ResponseBody void handleNewsletterSubscribeAJAXRequest(HttpServletRequest request,
            HttpServletResponse response, @PathVariable long memberId) {

        try {
            boolean memberHasActiveSubscription = MembersClient.subscribeToNewsletter(memberId);
            this.writeSuccessResultResponseJSON(memberHasActiveSubscription, response);
        } catch (HttpClientErrorException e) {
            this.writeSuccessResultResponseJSON(false, response);
        }

    }

    @PostMapping("unsubscribe")
    public @ResponseBody void handleNewsletterUnSubscribeAJAXRequest(HttpServletRequest request,
            HttpServletResponse response, @PathVariable long memberId) {

        try {
            boolean isMemberUnsubscribed = MembersClient.unsubscribeFromNewsletter(memberId);
            this.writeSuccessResultResponseJSON(isMemberUnsubscribed, response);
        } catch (HttpClientErrorException e) {
            this.writeSuccessResultResponseJSON(false, response);
        }
    }
}
