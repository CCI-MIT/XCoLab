package org.climatecollaboratorium.facelets.discussions.permissions;

import com.liferay.portal.kernel.exception.SystemException;
import org.climatecollaboratorium.facelets.discussions.support.MessageWrapper;

public interface DiscussionsPermissions {
    boolean getCanAddCategory();
    boolean getCanAddThread();
    boolean getCanAddMessage();
    boolean getCanAdminMessages();
    boolean getCanAdminCategories();
    boolean getCanAdmin();
    boolean getCanSubscribe();
    boolean getIsLoggedIn();
    boolean getCanAddComment();
    boolean getCanReportSpam();
    boolean getCanAdminSpamReports();
    boolean getCanReportMessage(MessageWrapper message) throws SystemException;
    boolean getCanRemoveSpamReport(MessageWrapper message) throws SystemException;
}
