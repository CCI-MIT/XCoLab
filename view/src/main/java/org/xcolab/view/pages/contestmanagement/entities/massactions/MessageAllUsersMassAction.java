package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.email.StaticEmailContext;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.html.HtmlUtil;
import org.xcolab.view.pages.contestmanagement.beans.MassMessageBean;
import org.xcolab.view.pages.contestmanagement.entities.MassActionRequiresConfirmationException;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class MessageAllUsersMassAction extends AbstractContestMassAction {

    public MessageAllUsersMassAction() {
        super("Message all users in the platform");
    }


    @Override
    public void execute(List<ContestWrapper> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response)
            throws MassActionRequiresConfirmationException, IllegalArgumentException, IOException {
        List<UserWrapper> userList = StaticUserContext.getUserClient().listAllMembers();
        MassMessageBean massMessageBean = dataWrapper.getMassMessageBean();
        final String adminEmail = ConfigurationAttributeKey.ADMIN_EMAIL.get();
        final String messageSubject = massMessageBean.getSubject();
        final String messageBody = massMessageBean.getBody();
        final String emailSubject = messageSubject;
        final String emailBody = HtmlUtil.addHtmlLineBreaks(messageBody);

        for (UserWrapper user : userList) {


            StaticEmailContext.getEmailClient()
                    .sendEmail(adminEmail, ConfigurationAttributeKey.COLAB_NAME.get(), user.getEmailAddress(),
                            emailSubject,
                            emailBody, true, null, adminEmail, null);
        }

    }
}
