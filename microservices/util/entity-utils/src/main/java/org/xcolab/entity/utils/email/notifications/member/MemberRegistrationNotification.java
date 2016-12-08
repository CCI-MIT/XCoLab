package org.xcolab.entity.utils.email.notifications.member;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.entity.utils.email.notifications.basic.MemberNotification;

public class MemberRegistrationNotification extends MemberNotification {
    private static final String TEMPLATE_NAME = "MEMBER_REGISTERED_DEFAULT";

    public MemberRegistrationNotification(Member recipient, String baseUrl) {
        super(recipient, TEMPLATE_NAME, baseUrl);
    }
}
