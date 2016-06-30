package org.xcolab.utils.emailnotification.member;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.utils.emailnotification.basic.MemberNotification;

public class MemberRegistrationNotification extends MemberNotification {
    private static final String TEMPLATE_NAME = "MEMBER_REGISTERED_DEFAULT";

    public MemberRegistrationNotification(Member recipient, ServiceContext serviceContext)
            throws SystemException, PortalException {
        super(recipient, TEMPLATE_NAME, serviceContext);
    }
}
