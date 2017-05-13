package org.xcolab.service.activities.activityentry.member;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.model.tables.pojos.ActivityEntry;
import org.xcolab.service.activities.activityentry.provider.ActivityEntryContentProvider;
import org.xcolab.util.enums.activity.ActivityEntryType;

public class MemberJoinedActivityEntry implements ActivityEntryContentProvider {

    private ActivityEntry activityEntry;

    private static final Logger _log = LoggerFactory.getLogger(MemberJoinedActivityEntry.class);


    @Override
    public String getBody() {

        try {
            Member member = MembersClient.getMember(activityEntry.getMemberId());
            String colabName = ConfigurationAttributeKey.COLAB_NAME.get();
            return String.format("%s joined the %s community", getUserLink(member), colabName);

        } catch (MemberNotFoundException e) {
            _log.error("Member not found {}", activityEntry.getMemberId());
        }

        return null;
    }

    private String getUserLink(Member user) {

        return (user.generateUserURL()==null)?("<user removed>"):(user.generateUserURL());

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
        MEMBER_JOINED(1L);
        private final Long secondaryTypeId;
        MemberSubActivityType(Long type) {
            this.secondaryTypeId = type;
        }

        public Long getSecondaryTypeId(){
            return this.secondaryTypeId;
        }
    }
}
