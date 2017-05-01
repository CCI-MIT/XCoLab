package org.xcolab.entity.utils.notifications.member;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.entity.utils.notifications.basic.MemberNotification;

public class MemberRegistrationNotification extends MemberNotification {
    private static final String TEMPLATE_NAME = "MEMBER_REGISTERED_DEFAULT";

    public MemberRegistrationNotification(Member recipient) {
        super(recipient, TEMPLATE_NAME);
    }
}
