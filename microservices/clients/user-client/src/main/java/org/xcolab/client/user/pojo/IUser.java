package org.xcolab.client.user.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.user.MembersClient;
import org.xcolab.client.user.MessagingClient;
import org.xcolab.client.user.PermissionsClient;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface IUser {

    @JsonIgnore
    static final String USER_PROFILE_PATH = "/members/profile/%d";

    @JsonIgnore
    static final String USER_PROFILE_EDIT_PATH = "/members/profile/%d/edit";

    @JsonIgnore
    static final String USER_IMAGE_PATH = "/image/member/%d";

    Long getId();

    void setId(Long id);

    String getScreenName();

    void setScreenName(String screenName);

    String getEmailAddress();

    void setEmailAddress(String emailAddress);

    Boolean getIsEmailConfirmed();

    void setIsEmailConfirmed(Boolean isEmailConfirmed);

    Boolean getIsEmailBounced();

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

    @JsonIgnore
    default long getPortraitId() {
        return getPortraitFileEntryId() != null ? getPortraitFileEntryId() : 0;
    }

    @JsonIgnore
    default Boolean isActive() {
        return this.getStatus() != null && this.getStatus() == 0;
    }

    @JsonIgnore
    default String getFullName() {
        final String fullName = WordUtils
                .capitalizeFully(this.getFirstName()+ " " + this.getLastName());
        if (StringUtils.isNotBlank(fullName)) {
            return fullName;
        }
        return getScreenName();
    }

    @JsonIgnore
    default boolean hasSameName(Member otherUser) {
        return StringUtils.equalsIgnoreCase(getFirstName(), otherUser.getFirstName())
                && StringUtils.equalsIgnoreCase(getLastName(), otherUser.getLastName());
    }

    @JsonIgnore
    default List<Role> getRoles() {
        return MembersClient.getMemberRoles(this.getId());
    }

    @JsonIgnore
    default boolean isVerifiedAccount() {
        return getIsEmailConfirmed();
    }

    @JsonIgnore
    default boolean hasLinkedSocialAccount() {
        return getFacebookId() != null || StringUtils.isNotEmpty(getGoogleId())
                || StringUtils.isNotEmpty(getOpenId());
    }

    @JsonIgnore
    default boolean getIsAdmin() {
        return PermissionsClient.canAdminAll(this);
    }

    @JsonIgnore
    default int getUnreadMessageCount() {
        return MessagingClient.countUnreadMessagesForUser(getId());
    }
    @JsonIgnore
    default String getOrGenerateUuid() {
        if (getUuid() == null) {
            setUuid(UUID.randomUUID().toString());
            try {
                StaticUserContext.getUserClient().updateUser(this);
            }catch (MemberNotFoundException e){

            }
        }
        return getUuid();
    }
    @JsonIgnore
    default String getProfileLinkUrl()  {
        return String.format(USER_PROFILE_PATH, getId());
    }

    @JsonIgnore
    default String getProfileEditUrl()  {
        return String.format(USER_PROFILE_EDIT_PATH, getId());
    }

    @JsonIgnore
    default String getImageLinkUrl() {
        final String userImageDomain = PlatformAttributeKey.CDN_URL_IMAGES_UPLOADED.get();
        return getPortraitId() != 0 ? userImageDomain + getRelativeImageUrl() : "";
    }

    @JsonIgnore
    default String getAbsoluteImageUrl() {
        final String cdnUrl = PlatformAttributeKey.CDN_URL_IMAGES_UPLOADED.get();
        final String userImageDomain = StringUtils.isNotEmpty(cdnUrl) ? cdnUrl
                : PlatformAttributeKey.COLAB_URL.get();
        return getPortraitId() != 0 ? userImageDomain + getRelativeImageUrl() : "";
    }


    default String getRelativeImageUrl() {
        return getPortraitId() != 0 ? String.format(USER_IMAGE_PATH, getPortraitId()) : "";
    }

    @JsonIgnore
    default String getLoginDateString() {
        return getLoginDate() != null ? getLoginDate().toString() : "";
    }

    @JsonIgnore
    default int getNumberOfMessagesLeft() {
        return MessagingClient.getNumberOfMessagesLeft(getId());
    }

    default boolean getIsProfileComplete() {
        return getFirstName() != null && getLastName() != null && getEmailAddress() != null && getCountry() != null;
    }
}
