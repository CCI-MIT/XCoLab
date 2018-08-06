package org.xcolab.client.members.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessagingUserPreference implements Serializable {

    private static final long serialVersionUID = 1819303241;

    public static final TypeProvider<MessagingUserPreference> TYPES =
            new TypeProvider<>(MessagingUserPreference.class,
                    new ParameterizedTypeReference<List<MessagingUserPreference>>() {
                    });

    private Long id;
    private long userId;
    private boolean emailOnSend;
    private boolean emailOnReceipt;
    private boolean emailOnActivity;
    private boolean emailActivityDailyDigest;
    private Integer dailyMessageLimit;

    public MessagingUserPreference() {
    }

    public MessagingUserPreference(MessagingUserPreference value) {
        this.id = value.id;
        this.userId = value.userId;
        this.emailOnSend = value.emailOnSend;
        this.emailOnReceipt = value.emailOnReceipt;
        this.emailOnActivity = value.emailOnActivity;
        this.emailActivityDailyDigest = value.emailActivityDailyDigest;
        this.dailyMessageLimit = value.dailyMessageLimit;
    }

    public MessagingUserPreference(long id, long userId, boolean emailOnSend,
            boolean emailOnReceipt, boolean emailOnActivity, boolean emailActivityDailyDigest) {
        this.id = id;
        this.userId = userId;
        this.emailOnSend = emailOnSend;
        this.emailOnReceipt = emailOnReceipt;
        this.emailOnActivity = emailOnActivity;
        this.emailActivityDailyDigest = emailActivityDailyDigest;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
        final MessagingUserPreference other = (MessagingUserPreference) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
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
        result = prime * result + ((id == null) ? 0
                : id.hashCode());
        result = prime * result + (int) (userId ^ userId >>> 32);
        result = prime * result + (emailOnSend ? 1 : 0);
        result = prime * result + (emailOnReceipt ? 1 : 0);
        result = prime * result + (emailOnActivity ? 1 : 0);
        result = prime * result + (emailActivityDailyDigest ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {

        return "MessagingUserPreferences (" + id +
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
