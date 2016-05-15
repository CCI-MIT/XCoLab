package org.xcolab.activityEntry.member;

import com.ext.portlet.community.CommunityUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.activities.contentProviders.ActivityEntryContentProvider;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;

public class MemberJoinedActivityEntry implements ActivityEntryContentProvider {

    private ActivityEntry activityEntry;

    private static final Log _log = LogFactoryUtil.getLog(MemberJoinedActivityEntry.class);


    @Override
    public String getBody() {

        String bodyTemplate = "%s joined the %s community";
        try {
            Member member = MembersClient.getMember(activityEntry.getMemberId());
            String colabName = ConfigurationAttributeKey.COLAB_NAME.getStringValue();
            return String.format(bodyTemplate, getUserLink(member), colabName);

        } catch (MemberNotFoundException e) {
            _log.error("Member not found " + activityEntry.getMemberId());
        }

        return null;
    }

    private String getUserLink(Member user) {
        try {
            return CommunityUtil.generateUserURL(user.getUserId());
        } catch (PortalException | SystemException e) {
            _log.info(e.getMessage());
        }
        return "<user removed>";
    }

    @Override
    public String getTitle() {
        return "New account created";
    }

    @Override
    public String getName() {
        return "MEMBER_REGISTERED";
    }


    @Override
    public void setActivityEntry(ActivityEntry activityEntry) {
        this.activityEntry = activityEntry;
    }

    @Override
    public Integer getPrimaryType() {
        return 01;
    } //TODO: EXTRACT VARIABLE TO ENUM

    @Override
    public Integer getSecondaryType() {
        return 01;
    }//TODO: EXTRACT VARIABLE TO ENUM
}
