package org.xcolab.client.members.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessagingUserPreferences implements Serializable {

    private static final long serialVersionUID = 1819303241;

    public static final TypeProvider<MessagingUserPreferences> TYPES =
            new TypeProvider<>(MessagingUserPreferences.class,
                    new ParameterizedTypeReference<List<MessagingUserPreferences>>() {
                    });

    private Long messagingPreferencesId;
    private long userId;
    private boolean emailOnSend;
    private boolean emailOnReceipt;
    private boolean emailOnActivity;
    private boolean emailActivityDailyDigest;
    private Integer dailyMessageLimit;

    public MessagingUserPreferences() {
    }

    public MessagingUserPreferences(MessagingUserPreferences value) {
        this.messagingPreferencesId = value.messagingPreferencesId;
        this.userId = value.userId;
        this.emailOnSend = value.emailOnSend;
        this.emailOnReceipt = value.emailOnReceipt;
        this.emailOnActivity = value.emailOnActivity;
        this.emailActivityDailyDigest = value.emailActivityDailyDigest;
    }

    public MessagingUserPreferences(long messagingPreferencesId, long userId, boolean emailOnSend,
            boolean emailOnReceipt, boolean emailOnActivity, boolean emailActivityDailyDigest) {
        this.messagingPreferencesId = messagingPreferencesId;
        this.userId = userId;
        this.emailOnSend = emailOnSend;
        this.emailOnReceipt = emailOnReceipt;
        this.emailOnActivity = emailOnActivity;
        this.emailActivityDailyDigest = emailActivityDailyDigest;
    }

    public Long getMessagingPreferencesId() {
        return this.messagingPreferencesId;
    }

    public void setMessagingPreferencesId(Long messagingPreferencesId) {
        this.messagingPreferencesId = messagingPreferencesId;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean getEmailOnSend() {
        return this.emailOnSend;
    }

    public void setEmailOnSend(boolean emailOnSend) {
        this.emailOnSend = emailOnSend;
    }

    public boolean getEmailOnReceipt() {
        return this.emailOnReceipt;
    }

    public void setEmailOnReceipt(boolean emailOnReceipt) {
        this.emailOnReceipt = emailOnReceipt;
    }

    public boolean getEmailOnActivity() {
        return this.emailOnActivity;
    }

    public void setEmailOnActivity(boolean emailOnActivity) {
        this.emailOnActivity = emailOnActivity;
    }

    public boolean getEmailActivityDailyDigest() {
        return this.emailActivityDailyDigest;
    }

    public void setEmailActivityDailyDigest(boolean emailActivityDailyDigest) {
        this.emailActivityDailyDigest = emailActivityDailyDigest;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MessagingUserPreferences other = (MessagingUserPreferences) obj;
        if (messagingPreferencesId == null) {
            if (other.messagingPreferencesId != null) {
                return false;
            }
        } else if (!messagingPreferencesId.equals(other.messagingPreferencesId)) {
            return false;
        }
        return userId == other.userId
                && emailOnSend == other.emailOnSend
                && emailOnReceipt == other.emailOnReceipt
                && emailOnActivity == other.emailOnActivity
                && emailActivityDailyDigest == other.emailActivityDailyDigest;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((messagingPreferencesId == null) ? 0
                : messagingPreferencesId.hashCode());
        result = prime * result + (int) (userId ^ userId >>> 32);
        result = prime * result + (emailOnSend ? 1 : 0);
        result = prime * result + (emailOnReceipt ? 1 : 0);
        result = prime * result + (emailOnActivity ? 1 : 0);
        result = prime * result + (emailActivityDailyDigest ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {

        return "MessagingUserPreferences (" + messagingPreferencesId +
                ", " + userId +
                ", " + emailOnSend +
                ", " + emailOnReceipt +
                ", " + emailOnActivity +
                ", " + emailActivityDailyDigest +
                ")";
    }

    public Integer getDailyMessageLimit() {
        return dailyMessageLimit;
    }

    public void setDailyMessageLimit(Integer dailyMessageLimit) {
        this.dailyMessageLimit = dailyMessageLimit;
    }
}
