package org.xcolab.client.user.pojo;

import java.sql.Timestamp;

public interface IUser {


    Long getId();

    void setId(Long id);

    String getScreenName();

    void setScreenName(String screenName);

    String getEmailAddress();

    void setEmailAddress(String emailAddress);

    Boolean isIsEmailConfirmed();

    void setIsEmailConfirmed(Boolean isEmailConfirmed);

    Boolean isIsEmailBounced();

    void setIsEmailBounced(Boolean isEmailBounced);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getHashedPassword();

    void setHashedPassword(String hashedPassword);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    Timestamp getUpdatedAt();

    void setUpdatedAt(Timestamp updatedAt);

    Timestamp getPasswordUpdatedAt();

    void setPasswordUpdatedAt(Timestamp passwordUpdatedAt);

    String getCountry();

    void setCountry(String country);

    String getShortBio();

    void setShortBio(String shortBio);

    Long getFacebookId();

    void setFacebookId(Long facebookId);

    String getGoogleId();

    void setGoogleId(String googleId);

    String getOpenId();

    void setOpenId(String openId);

    String getLoginIp();

    void setLoginIp(String loginIp);

    Timestamp getLoginDate();

    void setLoginDate(Timestamp loginDate);

    Integer getStatus();

    void setStatus(Integer status);

    String getForgotPasswordToken();

    void setForgotPasswordToken(String forgotPasswordToken);

    Timestamp getForgotPasswordTokenExpireTime();

    void setForgotPasswordTokenExpireTime(Timestamp forgotPasswordTokenExpireTime);

    Long getPortraitFileEntryId();

    void setPortraitFileEntryId(Long portraitFileEntryId);

    Integer getReportKarma();

    void setReportKarma(Integer reportKarma);

    String getDefaultLocale();

    void setDefaultLocale(String defaultLocale);

    Integer getAutoRegisteredMemberStatus();

    void setAutoRegisteredMemberStatus(Integer autoRegisteredMemberStatus);

    String getUuid();

    void setUuid(String uuid);

    String getLoginTokenId();

    void setLoginTokenId(String loginTokenId);

    String getLoginTokenKey();

    void setLoginTokenKey(String loginTokenKey);

    Timestamp getLoginTokenExpirationDate();

    void setLoginTokenExpirationDate(Timestamp loginTokenExpirationDate);

    String getState();

    void setState(String state);

    String getColabSsoId();

    void setColabSsoId(String colabSsoId);

    String getClimateXId();

    void setClimateXId(String climateXId);

}
