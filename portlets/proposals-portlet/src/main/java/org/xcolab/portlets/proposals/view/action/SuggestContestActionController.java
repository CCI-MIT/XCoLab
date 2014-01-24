package org.xcolab.portlets.proposals.view.action;

import com.liferay.portal.model.User;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.util.mail.MailEngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import com.liferay.util.mail.MailEngine;
import java.awt.event.ActionEvent;

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
                                ActionResponse response, @RequestParam("suggestContestText") String suggestContestText)
         throws PortalException, SystemException {
        try {
            sendContestSuggestion(suggestContestText, proposalsContext.getUser(request));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendContestSuggestion(String message, User u) throws MailEngineException, AddressException, NumberFormatException, PortalException, SystemException {
        String messageSubject = "New contest suggestion";
        String messageBody = message;
        String[] recipients = new String[] {"lfi@mit.edu", "pdeboer@MIT.EDU", "rjl@MIT.EDU"};
        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i=0; i < recipients.length; i++) {
            addressTo[i] = new InternetAddress(recipients[i]);
        }
        InternetAddress addressFrom = new InternetAddress("admin@climatecolab.org");
        InternetAddress replyTo[] = {new InternetAddress(u.getEmailAddress())};
        MailEngine.send(addressFrom, addressTo, null, null, null, messageSubject, messageBody, false, replyTo, null, null);
    }


}
