package org.xcolab.portlets.proposals.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Mente on 16.03.14.
 */
@Controller
@RequestMapping("view")
public class ProposalShareJSONController {

    @RequestMapping()
    public void send(ActionEvent e) throws AddressException, SystemException, PortalException, MailEngineException {

        Map<Long, User> usersMap = new HashMap<Long, User>();
        List<Long> recipientIds = new ArrayList<Long>();
        Long userId = Helper.getLiferayUser().getUserId();
        Long mutex = MessageLimitManager.getMutex(userId);
        synchronized (mutex) {
            if (!MessageLimitManager.canSendMessages(recipientIds.size())) {
                System.err.println("OBSERVED VALIDATION PROBLEM AGAIN. "+userId);

                recipientIds.clear();
                recipientIds.add(1011659L); //patrick
                MessageUtil.sendMessage("VALIDATION PROBLEM  "+subject, "VALIDATION PROBLEM  "+content, userId,
                        Helper.getLiferayUser().getUserId(), recipientIds, null);

                return;
            }

            for (String recipientId: receipients.split(",")) {
                if (! recipientId.trim().equals("")) {
                    recipientIds.add(Long.parseLong(recipientId));
                }
            }
            MessageUtil.sendMessage(subject, content, userId,
                    Helper.getLiferayUser().getUserId(), recipientIds, null);

            messagingBean.messageSent();
        }

    }
}
