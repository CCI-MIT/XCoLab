package org.xcolab.liferay;

import com.ext.utils.authentication.service.AuthenticationServiceUtil;
import org.apache.commons.lang3.StringUtils;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.ClassName;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ClassNameLocalServiceUtil;
import com.liferay.portal.service.ContactLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.util.html.HtmlUtil;
import org.xcolab.utils.emailnotification.member.MemberRegistrationNotification;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

//TODO: temporary class for liferay transition
//attributes with "liferay" prefix should be removed later
public final class LoginRegisterUtil {

    private static final long LIFERAY_COMPANY_ID = 10112L;

    private final static Log _log = LogFactoryUtil.getLog(LoginRegisterUtil.class);

    private LoginRegisterUtil() {
    }

    public static void updatePassword(String forgotPasswordToken, String newPassword)
            throws MemberNotFoundException, PortalException, SystemException {
        Long memberId = MembersClient.updateUserPassword(forgotPasswordToken, newPassword);
        if (memberId != null) {
            //TODO: remove, currently needed to update password for liferay
            final User liferayUser = UserLocalServiceUtil.getUser(memberId);
            liferayUser.setPassword
                    (MembersClient.hashPassword(newPassword.trim()));
            UserLocalServiceUtil.updateUser(liferayUser);
        } else {
            throw new MemberNotFoundException("Member with forgotPasswordToken: " + forgotPasswordToken + " was not found");
        }
    }

    private static User registerLiferayWithId(Long userId, String screenName, String password, String email, String firstName, String lastName, String fbStringId) {
        User user = null;
        long companyId = LIFERAY_COMPANY_ID;
        long groupId = 10136;
        long facebookId = 0;
        try {
            facebookId = Long.parseLong(fbStringId);
        } catch (NumberFormatException ignored) {
            facebookId = 0;
        }
        try {

            Role role = RoleLocalServiceUtil.getRole(companyId, "User");


            long idContact = CounterLocalServiceUtil.increment("com.liferay.portal.model.Contact");
            Contact contact = ContactLocalServiceUtil.createContact(idContact);
            contact.setCompanyId(companyId);
            contact.setCreateDate(new Date());
            contact.setUserName(screenName);
            contact.setUserId(userId);
            contact.setModifiedDate(new Date());
            contact.setFirstName(firstName);
            contact.setLastName(lastName);
            contact.setMale(true);
            contact.setMiddleName("");
            contact.setPrefixId(0);
            contact.setSuffixId(0);
            contact.setJobTitle("");
            contact.setParentContactId(0l);
            contact.setBirthday(new Date());
            try {
                ContactLocalServiceUtil.addContact(contact);
            } catch (SystemException ignored) {
                _log.debug("LOGINREGISTERDEBUG: Creating contact failed userId: " + userId + " - screenName: " + screenName);
            }

            String greeting = "Welcome " + firstName + " " + lastName;

            user = UserLocalServiceUtil.createUser(userId);
            user.setCompanyId(companyId);
            String encryptedPassword = null;
            try {
                MessageDigest sha1Digest = MessageDigest.getInstance("SHA-1");
                byte[] secretKeyBytes = sha1Digest.digest(password.getBytes());

                ByteBuffer byteBuffer = ByteBuffer.allocate(secretKeyBytes.length);
                byteBuffer.put(secretKeyBytes);
                encryptedPassword = DatatypeConverter.printBase64Binary((byteBuffer.array()));
            } catch (NoSuchAlgorithmException e) {

            }
            user.setPassword("{SHA-1}" + encryptedPassword);
            user.setScreenName(screenName);
            user.setEmailAddress(email);
            user.setFacebookId(facebookId);
            user.setOpenId("");

            user.setGreeting(greeting);
            user.setFirstName(HtmlUtil.cleanAll(firstName));
            user.setLastName(HtmlUtil.cleanAll(lastName));

            user.setJobTitle("");
            user.setCreateDate(new Date());
            user.setContactId(idContact);
            user.setPasswordReset(false);
            user.setPasswordEncrypted(true);
            user.setPasswordModifiedDate(new Date());
            user.setCreateDate(new Date());
            user.setModifiedDate(new Date());
            user.setLanguageId("en_US");
            user.setTimeZoneId("America/New_York");
            UserLocalServiceUtil.addUser(user);

            ClassName clsNameUser = ClassNameLocalServiceUtil.getClassName("com.liferay.portal.model.User");
            long classNameId = clsNameUser.getClassNameId();

            //Insert Group for a user
            long gpId = CounterLocalServiceUtil.increment(Group.class.getName());
            Group userGrp = GroupLocalServiceUtil.createGroup(gpId);
            userGrp.setClassNameId(classNameId);
            userGrp.setClassPK(user.getUserId());
            userGrp.setCompanyId(companyId);
            userGrp.setName(user.getUserId() + "");
            userGrp.setFriendlyURL("/" + user.getScreenName() + user.getUserId());
            userGrp.setCreatorUserId(user.getUserId());
            userGrp.setActive(true);
            try {
                GroupLocalServiceUtil.addGroup(userGrp);
            } catch (SystemException ignored) {
                _log.debug("LOGINREGISTERDEBUG: Creating group failed userId: " + userId + " - screenName: " + screenName + " gpId : " + gpId);
            }


            //Associate a role with user
            long userid[] = {user.getUserId()};
            long roleids[] = {role.getRoleId()};
            try {
                UserGroupRoleLocalServiceUtil.addUserGroupRoles(user.getUserId(), groupId, roleids);
                UserLocalServiceUtil.addRoleUsers(role.getRoleId(), userid);
            } catch (SystemException ignored) {
                _log.debug("LOGINREGISTERDEBUG: Creating addRoleUser failed userId: " + userId + " - screenName: " + screenName + " gpId : " + gpId);
            }

            //Create AssetEntry
            long assetEntryId = CounterLocalServiceUtil.increment(AssetEntry.class.getName());
            AssetEntry ae = AssetEntryLocalServiceUtil.createAssetEntry(assetEntryId);
            ae.setCompanyId(companyId);
            ae.setClassPK(user.getUserId());
            ae.setGroupId(userGrp.getGroupId());
            ae.setClassNameId(classNameId);
            try {
                AssetEntryLocalServiceUtil.addAssetEntry(ae);
            } catch (SystemException ignored) {
                _log.debug("LOGINREGISTERDEBUG: Creating AssetEntry failed userId: " + userId + " - screenName: " + screenName + " gpId : " + gpId + " assetEntryId: " +assetEntryId);
            }

            //Insert Layoutset for public and private
            long layoutSetIdPub = CounterLocalServiceUtil.increment(LayoutSet.class.getName());
            LayoutSet layoutSetPub = LayoutSetLocalServiceUtil.createLayoutSet(layoutSetIdPub);
            layoutSetPub.setCompanyId(companyId);
            layoutSetPub.setPrivateLayout(false);
            layoutSetPub.setGroupId(userGrp.getGroupId());
            layoutSetPub.setThemeId("classic");
            try {
                LayoutSetLocalServiceUtil.addLayoutSet(layoutSetPub);
            } catch (SystemException se) {
                _log.debug("LOGINREGISTERDEBUG: Creating LayoutSet failed userId: " + userId + " - screenName: " + screenName + " gpId : " + gpId + " assetEntryId: " +assetEntryId
                + " layoutSetIdPub public " + layoutSetIdPub);
            }

            long layoutSetIdPriv = CounterLocalServiceUtil.increment(LayoutSet.class.getName());
            LayoutSet layoutSetPriv = LayoutSetLocalServiceUtil.createLayoutSet(layoutSetIdPriv);
            layoutSetPriv.setCompanyId(companyId);
            layoutSetPriv.setPrivateLayout(true);
            layoutSetPriv.setThemeId("classic");
            layoutSetPriv.setGroupId(userGrp.getGroupId());
            try {
                LayoutSetLocalServiceUtil.addLayoutSet(layoutSetPriv);
            } catch (SystemException ignored) {
                _log.debug("LOGINREGISTERDEBUG: Creating LayoutSet failed userId: " + userId + " - screenName: " + screenName + " gpId : " + gpId + " assetEntryId: " +assetEntryId
                       + " layoutSetIdPriv private" + layoutSetIdPriv);
            }
        } catch (SystemException | PortalException ignored) {
            _log.debug("LOGINREGISTERDEBUG: Outside exception failed userId: " + userId + " - screenName: " + screenName
                    +" exception: "+ ignored.getLocalizedMessage());
        }

        return user;


    }


    public static void registerMemberInSharedColab(Long memberId) {
        try {
            Member memberInCurrentColab = MembersClient.getMember(memberId);

            org.xcolab.client.sharedcolab.pojo.Member member = new org.xcolab.client.sharedcolab.pojo.Member();
            member.setId_(memberInCurrentColab.getId_());
            member.setScreenName(memberInCurrentColab.getScreenName());
            member.setEmailAddress(memberInCurrentColab.getEmailAddress());
            member.setFirstName(memberInCurrentColab.getFirstName());
            member.setHashedPassword(memberInCurrentColab.getHashedPassword());
            member.setLastName(memberInCurrentColab.getLastName());
            member.setOpenId(memberInCurrentColab.getOpenId());
            member.setFacebookId(memberInCurrentColab.getFacebookId());
            member.setShortBio(memberInCurrentColab.getShortBio());
            member.setCountry(memberInCurrentColab.getCountry());
            SharedColabClient.registerInPartnerColab(member);
        } catch (MemberNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Member register(String screenName, String password, String email, String firstName, String lastName,
                                  String shortBio, String country, String fbIdString, String openId, String imageId,
                                  ServiceContext liferayServiceContext)
            throws Exception {

        Long memberId = SharedColabClient.retrieveSharedId(email, screenName, ConfigurationAttributeKey.COLAB_NAME.get());
        User liferayUser = registerLiferayWithId(memberId, screenName, password, email, firstName, lastName, fbIdString);

        Member member = new Member();
        member.setId_(liferayUser.getUserId());
        member.setScreenName(screenName);
        member.setEmailAddress(email);
        member.setFirstName(firstName);
        member.setHashedPassword(password);
        member.setLastName(lastName);
        member.setOpenId(openId);
        try {
            member.setFacebookId(Long.parseLong(fbIdString));
        } catch (NumberFormatException ignored) {
        }

        member.setShortBio(shortBio);
        member.setCountry(country);
        MembersClient.register(member);
        member = MembersClient.getMemberUnchecked(member.getId_());

        if (imageId != null && !imageId.isEmpty()) {

            member.setPortraitFileEntryId(Long.parseLong(imageId));
            MembersClient.updateMember(member);
        } else {
            member.setPortraitFileEntryId(0L);
            MembersClient.updateMember(member);
        }
        sendEmailNotificationToRegisteredUser(liferayServiceContext, member);

        return MembersClient.getMemberUnchecked(liferayUser.getUserId());
    }

    private static void sendEmailNotificationToRegisteredUser(ServiceContext serviceContext,
                                                              Member recipient) {
        new MemberRegistrationNotification(recipient, serviceContext).sendEmailNotification();
    }

    public static User login(PortletRequest request, PortletResponse response,
                             String login, String password) throws Exception {
        return login(request, response, login, password, "");
    }

    public static User login(PortletRequest request,
                             PortletResponse response,
                             String login, String password, String referer) throws Exception {
        if (StringUtils.isBlank(login)) {
            return null;
        }
        HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
        final String screenName = getScreenNameFromLogin(login);
        Member member = MembersClient.findMemberByScreenNameNoRole(screenName);
        boolean loggedIn = MembersClient.login(member.getId_(), password, httpReq.getRemoteAddr(), referer);
        if (loggedIn) {
            //TODO: liferay  throws a raw exception here
            checkIfMemberAutoRegisteredNeedsLiferayCreation(member, password);
            AuthenticationServiceUtil.logUserIn(request, response, screenName, password);
            return UserLocalServiceUtil.getUserByScreenName(LIFERAY_COMPANY_ID, login);
        }
        return null;
    }

    private static void checkIfMemberAutoRegisteredNeedsLiferayCreation(Member member, String password){

            if (member.getAutoRegisteredMemberStatus() == 1) {
                User liferayUser = registerLiferayWithId(member.getId_(), member.getScreenName(), password, member.getEmailAddress(), member.getFirstName(), member.getLastName(), (member.getFacebookId() != null ? (member.getFacebookId().toString()) : ("0")));
                if(liferayUser != null ) {
                    member.setAutoRegisteredMemberStatus(2);
                    MembersClient.updateMember(member);
                }
            }

    }
    private static String getScreenNameFromLogin(String login) throws MemberNotFoundException {
        if (login.contains("@")) {
            Member member = MembersClient.findMemberByEmailAddress(login);
            return member.getScreenName();
        }
        return login;
    }
}
