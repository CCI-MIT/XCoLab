package org.xcolab.activityEntry.discussion;

import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.DiscussionMessage;
import com.ext.portlet.service.DiscussionMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.activities.contentProviders.ActivityEntryContentProvider;
import org.xcolab.client.activities.pojo.ActivityEntry;

public abstract class DiscussionBaseActivityEntry implements ActivityEntryContentProvider {

    protected ActivityEntry activityEntry;



    protected DiscussionCategoryGroup categoryGroup;

    @Override
    public void setActivityEntry(ActivityEntry activityEntry) {
        this.activityEntry = activityEntry;
        try {
            DiscussionMessage comment = DiscussionMessageLocalServiceUtil.getMessageByMessageId(this.activityEntry.getClassPrimaryKey())
            DiscussionMessage discussion = DiscussionMessageLocalServiceUtil.getThread(comment);
            categoryGroup = DiscussionMessageLocalServiceUtil.getCategoryGroup(discussion);
        }catch( SystemException | PortalException ignore){

        }
    }

    @Override
    public Integer getPrimaryType() {
        return 3;
    }

}
