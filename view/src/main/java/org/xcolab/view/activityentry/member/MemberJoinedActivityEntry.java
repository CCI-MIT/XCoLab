package org.xcolab.view.activityentry.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.util.activities.enums.enums.ActivityType;
import org.xcolab.util.activities.enums.enums.MemberActivityType;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.view.activityentry.provider.AbstractActivityEntryContentProvider;
import org.xcolab.view.i18n.ResourceMessageResolver;

@Component
public class MemberJoinedActivityEntry extends AbstractActivityEntryContentProvider {

    private static final String MESSAGE_CODE = "activities.members.message";

    private final ResourceMessageResolver resourceMessageResolver;

    @Autowired
    public MemberJoinedActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        this.resourceMessageResolver = resourceMessageResolver;
    }

    @Override
    protected void initializeInternal() { }

    @Override
    public String getBody() {
        String colabName = ConfigurationAttributeKey.COLAB_NAME.get();
        String[] params = {getUserLink(), colabName};
        return resourceMessageResolver.getLocalizedMessage(MESSAGE_CODE, params);
    }

    @Override
    public String getTitle() {
        return "New account created";
    }

    @Override
    public ActivityType getActivityType() {
        return MemberActivityType.REGISTERED;
    }
}
