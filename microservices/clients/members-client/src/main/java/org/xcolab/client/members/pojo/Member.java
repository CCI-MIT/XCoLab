package org.xcolab.client.members.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.xcolab.client.members.MembersClient;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Member implements Serializable {

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
    }

    public Long getId() {
        return this.id_;
    }

    //For liferay/jsp compatibility
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

    public boolean isActive() {
        return this.getStatus() == 0;
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    public List<Role_> getRoles() {
        return MembersClient.getMemberRoles(this.getId());
    }

    public String getHashedPassword() {
        return this.hashedPassword == null ? "" : this.hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setPassword(String password) {
        this.hashedPassword = MembersClient.hashPassword(password);
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
                ")";
    }
}
