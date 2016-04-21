package org.xcolab.portlets.proposals.view.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.util.mail.MailEngineException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 28/10/13
 * Time: 12:06
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("view")
public class SuggestContestActionController {


    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = {"action=suggestContest"})
    public void suggestContest(ActionRequest request,
                                ActionResponse response, @RequestParam("suggestContestText") String suggestContestText) {
        try {
            sendContestSuggestion(suggestContestText, proposalsContext.getUser(request));
        } catch (MailEngineException | AddressException | PortalException | SystemException e) {
            e.printStackTrace();
        }
    }

    public void sendContestSuggestion(String message, User u) throws MailEngineException, AddressException, NumberFormatException, PortalException, SystemException {
        String[] recipients = {"lfi@mit.edu", "pdeboer@MIT.EDU", "rjl@MIT.EDU"};
        List<String> addressTo = new ArrayList<>();
        for (int i=0; i < recipients.length; i++) {
            addressTo.add(recipients[i]);
        }
        final String messageSubject = "New contest suggestion";
        EmailClient.sendEmail("admin@climatecolab.org", addressTo, messageSubject, message, false, u.getEmailAddress());

    }


}
