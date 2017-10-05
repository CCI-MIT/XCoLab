package org.xcolab.view.activityentry.member;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.view.activityentry.provider.ActivityEntryContentProvider;
import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class MemberJoinedActivityEntry implements ActivityEntryContentProvider {

    private static final Logger _log = LoggerFactory.getLogger(MemberJoinedActivityEntry.class);

    private static final String MESSAGE_CODE = "activities.members.message";

    private final ResourceMessageResolver resourceMessageResolver;

    private ActivityEntry activityEntry;

    @Autowired
    public MemberJoinedActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        this.resourceMessageResolver = resourceMessageResolver;
    }

    @Override
    public String getBody() {

        try {
            Member member = MembersClient.getMember(activityEntry.getMemberId());
            String colabName = ConfigurationAttributeKey.COLAB_NAME.get();
            String[] params = {getUserLink(member), colabName};
            return resourceMessageResolver.getLocalizedMessage(MESSAGE_CODE, params);

        } catch (MemberNotFoundException e) {
            _log.error("Member not found {}", activityEntry.getMemberId());
        }

        return null;
    }

    private String getUserLink(Member user) {
        return (user.generateUserURL() == null) ? ("<user removed>") : (user.generateUserURL());
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

    public enum MemberSubActivityType {
        MEMBER_JOINED(1L);

        private final Long secondaryTypeId;

        MemberSubActivityType(Long type) {
            this.secondaryTypeId = type;
        }

        public Long getSecondaryTypeId() {
            return this.secondaryTypeId;
        }
    }
}
