package org.xcolab.client.user.pojo.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.text.WordUtils;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class UserWrapper extends org.xcolab.client.user.pojo.tables.pojos.User implements Serializable {

    private static final long serialVersionUID = 1;

    @JsonIgnore
    private static final String USER_PROFILE_PATH = "/members/profile/%d";

    @JsonIgnore
    private static final String USER_PROFILE_EDIT_PATH = "/members/profile/%d/edit";

    @JsonIgnore
    private static final String USER_IMAGE_PATH = "/image/member/%d";


    public UserWrapper() {
    }

    public UserWrapper(UserWrapper value) {
        super(value);

    }

    public static UserWrapper fromId(String userIdString) {
        return fromId(Long.parseLong(userIdString));
    }

    public static UserWrapper fromId(long userId) {
        return StaticUserContext.getUserClient().getMemberUnchecked(userId);
    }


    @JsonIgnore
    public String getDisplayName() {

        if (ConfigurationAttributeKey.DISPLAY_FIRST_NAME_LAST_NAME.get()) {
            return getFullName();
        } else {
            return getScreenName();
        }
    }


    @JsonIgnore
    public long getPortraitId() {
        return getPortraitFileEntryId() != null ? getPortraitFileEntryId() : 0;
    }

    @JsonIgnore
    public Boolean isActive() {
        return this.getStatus() != null && this.getStatus() == 0;
    }

    @JsonIgnore
    public String getFullName() {
        final String fullName = WordUtils
                .capitalizeFully(this.getFirstName()+ " " + this.getLastName());
        if (StringUtils.isNotBlank(fullName)) {
            return fullName;
        }
        return getScreenName();
    }

    @JsonIgnore
    public boolean hasSameName(UserWrapper otherUser) {
        return StringUtils.equalsIgnoreCase(getFirstName(), otherUser.getFirstName())
                && StringUtils.equalsIgnoreCase(getLastName(), otherUser.getLastName());
    }

    @JsonIgnore
    public List<RoleWrapper> getRoles() {
        return StaticUserContext.getUserClient().getUserRoles(this.getId(),null);
    }

    @JsonIgnore
    public boolean isVerifiedAccount() {
        return this.isIsEmailConfirmed();
    }

    @JsonIgnore
    public boolean hasLinkedSocialAccount() {
        return getFacebookId() != null || StringUtils.isNotEmpty(getGoogleId())
                || StringUtils.isNotEmpty(getOpenId());
    }

    @JsonIgnore
    public boolean getIsAdmin() {
        return StaticUserContext.getPermissionClient().canAdminAll(this);
    }

    @JsonIgnore
    public int getUnreadMessageCount() {
        return StaticUserContext.getMessagingClient().countUnreadMessagesForUser(getId());
    }



    @JsonIgnore
    public String getOrGenerateUuid() {
        if (getUuid() == null) {
            setUuid(UUID.randomUUID().toString());
            try{
                StaticUserContext.getUserClient().updateUser(this, this.getId());
            }catch (MemberNotFoundException ignored){

            }
        }
        return getUuid();
    }



    @JsonIgnore
    public String getProfileLinkUrl()  {
        return String.format(USER_PROFILE_PATH, getId());
    }

    @JsonIgnore
    public String getProfileEditUrl()  {
        return String.format(USER_PROFILE_EDIT_PATH, getId());
    }

    @JsonIgnore
    public String getImageLinkUrl() {
        final String userImageDomain = PlatformAttributeKey.CDN_URL_IMAGES_UPLOADED.get();
        return getPortraitId() != 0 ? userImageDomain + getRelativeImageUrl() : "";
    }

    @JsonIgnore
    public String getAbsoluteImageUrl() {
        final String cdnUrl = PlatformAttributeKey.CDN_URL_IMAGES_UPLOADED.get();
        final String userImageDomain = StringUtils.isNotEmpty(cdnUrl) ? cdnUrl
                : PlatformAttributeKey.COLAB_URL.get();
        return getPortraitId() != 0 ? userImageDomain + getRelativeImageUrl() : "";
    }

    public String getRelativeImageUrl() {
        return getPortraitId() != 0 ? String.format(USER_IMAGE_PATH, getPortraitId()) : "";
    }

    @JsonIgnore
    public String getLoginDateString() {
        return getLoginDate() != null ? getLoginDate().toString() : "";
    }

    @JsonIgnore
    public int getNumberOfMessagesLeft() {
        return StaticUserContext.getMessagingClient().getNumberOfMessagesLeft(getId());
    }

    public boolean getIsProfileComplete() {
        return getFirstName() != null && getLastName() != null && getEmailAddress() != null && getCountry() != null;
    }

    public void anonymize() {
        this.setFirstName("Member");
        this.setLastName("Removed");
        this.setEmailAddress("removed+userId" + this.getId() + "@climatecolab.org");
        this.setScreenName("MemberRemoved" + this.getId());
        this.setFacebookId(null);
        this.setGoogleId(null);
        this.setOpenId(null);
        this.setColabSsoId(null);
        this.setClimateXId(null);
        this.setLoginIp(null);
        this.setShortBio(null);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UserWrapper
                && ((UserWrapper) obj).getId().longValue() == this.getId().longValue();
    }

    @Override
    public int hashCode() {
        if(this.getId()!=null) {
            return (int) (this.getId() ^ this.getId() >>> 32);
        } else {
            return 21929038;
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", getId())
                .append("screenName", getScreenName())
                .append("emailAddress", getEmailAddress())
                .append("isEmailConfirmed", this.isIsEmailConfirmed())
                .append("isEmailBounced", this.isIsEmailBounced())
                .append("createdAt", getCreatedAt())
                .append("updatedAt", getUpdatedAt())
                .append("passwordUpdatedAt", getPasswordUpdatedAt())
                .append("hashedPassword", getHashedPassword() != null ? "REDACTED" : null)
                .append("firstName", getFirstName())
                .append("lastName", getLastName())
                .append("country", getCountry())
                .append("shortBio", getShortBio())
                .append("facebookId", getFacebookId())
                .append("googleId", getGoogleId())
                .append("colabSsoId", getColabSsoId())
                .append("climateXSsoId", getClimateXId())
                .append("openId", getOpenId())
                .append("loginIP", getLoginIp())
                .append("loginDate", getLoginDate())
                .append("status", getStatus())
                .append("reportKarma", getReportKarma())
                .append("portraitFileEntryId", getPortraitFileEntryId())
                .append("autoregisteredmemberstatus", getAutoRegisteredMemberStatus())
                .append("uuid", getUuid())
                .append("defaultlocale", getDefaultLocale())
                .append("loginTokenId", getLoginTokenId())
                .append("loginTokenKey", getLoginTokenId() != null ? "REDACTED" : null)
                .append("loginTokenExpirationDate", getLoginTokenExpirationDate()).toString();
    }
}
