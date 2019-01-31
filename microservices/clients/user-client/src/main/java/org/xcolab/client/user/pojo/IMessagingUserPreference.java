package org.xcolab.client.user.pojo;

public interface IMessagingUserPreference {

    Long getId();

    void setId(Long id);

    Long getUserId();

    void setUserId(Long userId);

    Boolean isEmailOnSend();

    void setEmailOnSend(Boolean emailOnSend);

    Boolean isEmailOnReceipt();

    void setEmailOnReceipt(Boolean emailOnReceipt);

    Boolean isEmailOnActivity();

    void setEmailOnActivity(Boolean emailOnActivity);

    Boolean isEmailActivityDailyDigest();

    void setEmailActivityDailyDigest(Boolean emailActivityDailyDigest);

    Integer getDailyMessageLimit();

    void setDailyMessageLimit(Integer dailyMessageLimit);
}
