package org.xcolab.client.activities.activityEntry.member;

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
import org.xcolab.util.enums.activity.ActivityEntryType;

public class MemberJoinedActivityEntry implements ActivityEntryContentProvider {

    private ActivityEntry activityEntry;

    private static final Log _log = LogFactoryUtil.getLog(MemberJoinedActivityEntry.class);


    @Override
    public String getBody() {

        String bodyTemplate = "%s joined the %s community";
        try {
            Member member = MembersClient.getMember(activityEntry.getMemberId());
            String colabName = ConfigurationAttributeKey.COLAB_NAME.get();
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
    public Long getPrimaryType() {
        return ActivityEntryType.MEMBER.getPrimaryTypeId();
    }

    @Override
    public Long getSecondaryType() {
        return MemberSubActivityType.MEMBER_JOINED.getSecondaryTypeId();
    }

    public enum MemberSubActivityType{
        MEMBER_JOINED(1l);
        private final Long secondaryTypeId;
        MemberSubActivityType(Long type) {
            this.secondaryTypeId = type;
        }

        public Long getSecondaryTypeId(){
            return this.secondaryTypeId;
        }
    }
}
