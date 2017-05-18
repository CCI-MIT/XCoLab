package org.xcolab.client.members.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Member implements Serializable {

    private static final long serialVersionUID = 1;

    @JsonIgnore
    private static final String USER_PROFILE_PATH = "/members/profile/";

    public static final TypeProvider<Member> TYPES =
            new TypeProvider<>(Member.class,
                    new ParameterizedTypeReference<List<Member>>() {
                    });

    private long id_;
    private String screenName;
    private String emailAddress;
    private boolean isEmailConfirmed;
    private Timestamp createDate;
    private Timestamp modifiedDate;
    private Timestamp passwordModifiedDate;
    private String hashedPassword;
    private String firstName;
    private String lastName;
    private String country;
    private String shortBio;
    private Long facebookId;
    private String googleId;
    private String openId;
    private String loginIP;
    private Timestamp loginDate;
    private Integer status;
    private int reportKarma;
    private Long portraitFileEntryId;
    private Integer   autoregisteredmemberstatus;
    private String    uuid;

    public Member() {
    }

    public Member(Member value) {
        this.id_ = value.id_;
        this.screenName = value.screenName;
        this.emailAddress = value.emailAddress;
        this.createDate = value.createDate;
        this.modifiedDate = value.modifiedDate;
        this.passwordModifiedDate = value.passwordModifiedDate;
        this.firstName = value.firstName;
        this.lastName = value.lastName;
        this.country = value.country;
        this.shortBio = value.shortBio;
        this.facebookId = value.facebookId;
        this.openId = value.openId;
        this.loginIP = value.loginIP;
        this.loginDate = value.loginDate;
        this.status = value.status;
        this.reportKarma = value.reportKarma;
        this.portraitFileEntryId = value.portraitFileEntryId;
        this.autoregisteredmemberstatus = value.autoregisteredmemberstatus;
        this.uuid = value.uuid;
    }

    public static Member fromId(String memberIdString) {
        return fromId(Long.parseLong(memberIdString));
    }

    public static Member fromId(long memberId) {
        return MembersClient.getMemberUnchecked(memberId);
    }

    public long getId_() {
        return this.id_;
    }

    //For liferay/jsp compatibility
    @JsonIgnore
    public long getUserId() {
        return this.id_;
    }

    public void setId_(long id_) {
        this.id_ = id_;
    }

    public String getScreenName() {
        return this.screenName;
    }

    @JsonIgnore
    public String getDisplayName() {

        if(ConfigurationAttributeKey.DISPLAY_FIRST_NAME_LAST_NAME.get()){
            return this.getFullName();
        }
        else {
            return this.screenName;
        }
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Timestamp getPasswordModifiedDate() {
        return this.passwordModifiedDate;
    }

    public void setPasswordModifiedDate(Timestamp passwordModifiedDate) {
        this.passwordModifiedDate = passwordModifiedDate;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getShortBio() {
        return this.shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    public Long getFacebookId() {
        return this.facebookId;
    }

    public void setFacebookId(Long facebookId) {
        this.facebookId = facebookId;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getLoginIP() {
        return this.loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public Timestamp getLoginDate() {
        return this.loginDate;
    }

    public void setLoginDate(Timestamp loginDate) {
        this.loginDate = loginDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getPortraitFileEntryId() {
        return this.portraitFileEntryId;
    }

    public void setPortraitFileEntryId(Long portraitfileentryid) {
        this.portraitFileEntryId = portraitfileentryid;
    }

    @JsonIgnore
    public long getPortraitId() {
        return getPortraitFileEntryId() != null ? getPortraitFileEntryId() : 0;
    }

    @JsonIgnore
    public Boolean isActive() {
        return this.status != null && this.status == 0;
    }

    @JsonIgnore
    public String getFullName() {
        return StringUtils.capitalize(this.getFirstName()) + " " + StringUtils.capitalize(this.getLastName());
    }

    @JsonIgnore
    public List<Role_> getRoles() {
        return MembersClient.getMemberRoles(this.getId_());
    }

    @JsonIgnore
    public boolean isVerifiedAccount() {
        return hasLinkedSocialAccount() || isEmailConfirmed;
    }

    @JsonIgnore
    public boolean hasLinkedSocialAccount() {
        return facebookId != null || StringUtils.isNotEmpty(googleId)
                || StringUtils.isNotEmpty(openId);
    }

    public String getHashedPassword() {
        return this.hashedPassword == null ? "" : this.hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public int getReportKarma() {
        return reportKarma;
    }

    public void setReportKarma(int reportKarma) {
        this.reportKarma = reportKarma;
    }

    public Integer getAutoRegisteredMemberStatus() {
        return this.autoregisteredmemberstatus;
    }

    public void setAutoRegisteredMemberStatus(Integer autoregisteredmemberstatus) {
        this.autoregisteredmemberstatus = autoregisteredmemberstatus;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {

        return "Member (" + id_ +
                ", " + screenName +
                ", " + emailAddress +
                ", " + createDate +
                ", " + modifiedDate +
                ", " + passwordModifiedDate +
                ", " + firstName +
                ", " + lastName +
                ", " + country +
                ", " + shortBio +
                ", " + facebookId +
                ", " + googleId +
                ", " + openId +
                ", " + loginIP +
                ", " + loginDate +
                ", " + reportKarma +
                ")";
    }

    @JsonIgnore
    public  String generateUserURL() {
        if (this.getId_() <= 0) {
            return "";
        }
        return "<a href='" + generateUserHref(this.getId_())+ "'>" + this.getDisplayName()+ "</a>";
    }

    private  String generateUserHref(long userId)  {
        if (userId <= 0) {
            return "";
        }
        return USER_PROFILE_PATH + userId;
    }

    @JsonIgnore
    public int getNumberOfMessagesLeft() {
        return MessagingClient.getNumberOfMessagesLeft(getId_());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Member
                && ((Member) obj).getId_() == this.getId_();
    }

    @Override
    public int hashCode() {
        return (int) (this.getId_() ^ this.getId_() >>> 32);
    }

    public boolean getIsEmailConfirmed() {
        return isEmailConfirmed;
    }

    public void setIsEmailConfirmed(boolean emailConfirmed) {
        isEmailConfirmed = emailConfirmed;
    }
}
