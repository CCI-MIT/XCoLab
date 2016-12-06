package org.xcolab.utils.emailnotification.member;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.utils.emailnotification.basic.MemberNotification;

public class MemberRegistrationNotification extends MemberNotification {
    private static final String TEMPLATE_NAME = "MEMBER_REGISTERED_DEFAULT";

    public MemberRegistrationNotification(Member recipient, String baseUrl) {
        super(recipient, TEMPLATE_NAME, baseUrl);
    }
}
