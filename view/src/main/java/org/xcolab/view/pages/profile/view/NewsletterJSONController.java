package org.xcolab.view.pages.profile.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.view.pages.profile.utils.JSONHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/members/profile/{userId}/api/settings/newsletter")
public class NewsletterJSONController extends JSONHelper {


    private final IUserClient userClient;

    @Autowired
    public NewsletterJSONController(IUserClient userClient) {
        this.userClient = userClient;
    }

    @PostMapping("subscribe")
    public @ResponseBody void handleNewsletterSubscribeAJAXRequest(HttpServletRequest request,
            HttpServletResponse response, @PathVariable long userId) {

        try {
                boolean memberHasActiveSubscription = userClient.subscribeToNewsletter(userId);

            this.writeSuccessResultResponseJSON(memberHasActiveSubscription, response);
        } catch (HttpClientErrorException | MemberNotFoundException e) {
            this.writeSuccessResultResponseJSON(false, response);
        }

    }

    @PostMapping("unsubscribe")
    public @ResponseBody void handleNewsletterUnSubscribeAJAXRequest(HttpServletRequest request,
            HttpServletResponse response, @PathVariable long userId) {

        try {
            boolean isMemberUnsubscribed = userClient.unsubscribeToNewsletter(userId);
            this.writeSuccessResultResponseJSON(isMemberUnsubscribed, response);
        } catch (HttpClientErrorException | MemberNotFoundException e) {
            this.writeSuccessResultResponseJSON(false, response);
        }
    }
}
