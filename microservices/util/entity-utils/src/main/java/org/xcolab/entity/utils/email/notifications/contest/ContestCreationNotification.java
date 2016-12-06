package org.xcolab.entity.utils.email.notifications.contest;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.entity.utils.email.notifications.basic.ContestNotification;

public class ContestCreationNotification extends ContestNotification {

    private static final String TEMPLATE_NAME = "CONTEST_CREATION_DEFAULT";

    public ContestCreationNotification(Contest contest, String baseUrl)
            throws MemberNotFoundException {
        super(contest, MembersClient.getMember(contest.getAuthorId()), TEMPLATE_NAME, baseUrl);
    }
}
