package org.xcolab.utils.emailnotification.contest;

import com.ext.portlet.model.Contest;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.utils.emailnotification.basic.ContestNotification;

public class ContestCreationNotification extends ContestNotification {

    private static final String TEMPLATE_NAME = "CONTEST_CREATION_DEFAULT";

    public ContestCreationNotification(Contest contest, ServiceContext serviceContext)
            throws SystemException, PortalException {
        super(contest, UserLocalServiceUtil.getUserById(contest.getAuthorId()), TEMPLATE_NAME, serviceContext);
    }
}
