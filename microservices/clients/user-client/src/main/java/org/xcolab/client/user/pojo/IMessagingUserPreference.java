package org.xcolab.client.user.pojo;

public interface IMessagingUserPreference {

    Long getId();

    void setId(Long id);

    Long getUserId();

    void setUserId(Long userId);

    Boolean getEmailOnSend();

    void setEmailOnSend(Boolean emailOnSend);

    Boolean getEmailOnReceipt();

    void setEmailOnReceipt(Boolean emailOnReceipt);

    Boolean getEmailOnActivity();

    void setEmailOnActivity(Boolean emailOnActivity);

    Boolean getEmailActivityDailyDigest();

    void setEmailActivityDailyDigest(Boolean emailActivityDailyDigest);

    Integer getDailyMessageLimit();

    void setDailyMessageLimit(Integer dailyMessageLimit);
}
