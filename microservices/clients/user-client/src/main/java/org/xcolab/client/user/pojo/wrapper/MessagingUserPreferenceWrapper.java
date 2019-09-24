package org.xcolab.client.user.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessagingUserPreferenceWrapper extends org.xcolab.client.user.pojo.tables.pojos.MessagingUserPreference {


    public MessagingUserPreferenceWrapper() {
    }

    public MessagingUserPreferenceWrapper(MessagingUserPreferenceWrapper value) {
        this.setId(value.getId());
        this.setUserId(value.getUserId());
        this.setEmailOnSend(value.isEmailOnSend());
        this.setEmailOnReceipt(value.isEmailOnReceipt());
        this.setEmailOnActivity(value.isEmailOnActivity());
        this.setEmailActivityDailyDigest(value.isEmailActivityDailyDigest());
        this.setDailyMessageLimit(value.getDailyMessageLimit());

    }

    public MessagingUserPreferenceWrapper(long id, long userId, boolean emailOnSend,
            boolean emailOnReceipt, boolean emailOnActivity, boolean emailActivityDailyDigest) {
        this.setId(id);
        this.setUserId(userId);
        this.setEmailOnSend(emailOnSend);
        this.setEmailOnActivity(emailOnActivity);
        this.setEmailOnReceipt(emailOnReceipt);
        this.setEmailActivityDailyDigest(emailActivityDailyDigest);
    }
}
