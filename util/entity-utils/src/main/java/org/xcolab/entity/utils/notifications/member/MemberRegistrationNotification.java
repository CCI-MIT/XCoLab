package org.xcolab.entity.utils.notifications.member;

import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.entity.utils.notifications.basic.MemberNotification;

public class MemberRegistrationNotification extends MemberNotification {
    private static final String TEMPLATE_NAME = "MEMBER_REGISTERED_DEFAULT";

    public MemberRegistrationNotification(UserWrapper recipient) {
        super(recipient, TEMPLATE_NAME);
    }
}
