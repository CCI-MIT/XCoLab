package org.xcolab.client.sharedcolab.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Member implements Serializable {
    public static final TypeProvider<Member> TYPES =
            new TypeProvider<>(Member.class,
                    new ParameterizedTypeReference<List<Member>>() {
                    });

    private static final long serialVersionUID = 343994517;

    private Long id_;
    private String screenName;
    private String emailAddress;
    private Timestamp createDate;
    private Timestamp modifiedDate;
    private Timestamp passwordModifiedDate;
    private String hashedPassword;
    private String firstName;
    private String lastName;
    private String country;
    private String shortBio;
    private Long facebookId;
    private String openId;
    private String loginIP;
    private Timestamp loginDate;
    private Integer status;
    private int reportKarma;
    private Long portraitfileentryid;
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
        this.portraitfileentryid = value.portraitfileentryid;
        this.uuid = value.uuid;
    }

    public Long getId_() {
        return this.id_;
    }

    //For liferay/jsp compatibility
    @JsonIgnore
    public Long getUserId() {
        return this.id_;
    }

    public void setId_(Long id_) {
        this.id_ = id_;
    }

    public String getScreenName() {
        return this.screenName;
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
        return this.portraitfileentryid;
    }

    public void setPortraitFileEntryId(Long portraitfileentryid) {
        this.portraitfileentryid = portraitfileentryid;
    }

    public Integer getAutoregisteredmemberstatus() {
        return autoregisteredmemberstatus;
    }

    public void setAutoregisteredmemberstatus(Integer autoregisteredmemberstatus) {
        this.autoregisteredmemberstatus = autoregisteredmemberstatus;
    }
    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @JsonIgnore
    public long getPortraitId() {
        //TODO: add image id from fileupload service
        return 0;
    }

    @JsonIgnore
    public Boolean isActive() {
        return this.status != null && this.status == 0;
    }

    @JsonIgnore
    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
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
                ", " + openId +
                ", " + loginIP +
                ", " + loginDate +
                ", " + reportKarma +
                ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Member)) {
            return false;
        }
        if (((Member) obj).getId_() == this.getId_()) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public int hashCode() {
        return this.getId_().intValue();
    }
}
