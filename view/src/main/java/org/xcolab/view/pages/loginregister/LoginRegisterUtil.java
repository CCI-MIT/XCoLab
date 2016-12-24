package org.xcolab.view.pages.loginregister;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.LockoutLoginException;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.exceptions.PasswordLoginException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.sharedcolab.SharedColabClient;
import org.xcolab.entity.utils.email.notifications.member.MemberRegistrationNotification;
import org.xcolab.view.auth.MemberDetails;

import javax.servlet.http.HttpServletRequest;

public final class LoginRegisterUtil {

    private LoginRegisterUtil() {
    }

    public static void updatePassword(String forgotPasswordToken, String newPassword)
            throws MemberNotFoundException {
        Long memberId = MembersClient.updateUserPassword(forgotPasswordToken, newPassword);
        if (memberId == null) {
            throw new MemberNotFoundException("Member with forgotPasswordToken: "
                    + forgotPasswordToken + " was not found");
        }
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

    public static Member register(String screenName, String password, String email,
            String firstName, String lastName, String shortBio, String country,
            String fbIdString, String openId, String imageId, String baseUrl) {

        Long memberId = SharedColabClient.retrieveSharedId(email, screenName,
                ConfigurationAttributeKey.COLAB_NAME.get());

        Member member = new Member();
        member.setId_(memberId);
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
        member.setPortraitFileEntryId(0L);
        MembersClient.register(member);
        member = MembersClient.getMemberUnchecked(member.getId_());

        if (imageId != null && !imageId.isEmpty()) {
            member.setPortraitFileEntryId(Long.parseLong(imageId));
            MembersClient.updateMember(member);
        } else {
            member.setPortraitFileEntryId(0L);
            MembersClient.updateMember(member);
        }
        sendEmailNotificationToRegisteredUser(baseUrl, member);

        return member;
    }

    private static void sendEmailNotificationToRegisteredUser(String baseUrl,
            Member recipient) {
        new MemberRegistrationNotification(recipient, baseUrl).sendEmailNotification();
    }

    public static Member login(HttpServletRequest request, String login, String password)
            throws MemberNotFoundException, PasswordLoginException, LockoutLoginException {
        return login(request, login, password, "");
    }

    public static Member login(HttpServletRequest request,
            String login, String password, String referer)
            throws MemberNotFoundException, PasswordLoginException, LockoutLoginException {
        if (StringUtils.isBlank(login)) {
            return null;
        }
        final String screenName = getScreenNameFromLogin(login);
        Member member = MembersClient.findMemberByScreenNameNoRole(screenName);
        boolean loggedIn = MembersClient
                .login(member.getId_(), password, request.getRemoteAddr(), referer);
        if (loggedIn) {
            final MemberDetails memberDetails = new MemberDetails(member);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    memberDetails, memberDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        return member;
    }

    private static String getScreenNameFromLogin(String login) throws MemberNotFoundException {
        if (login.contains("@")) {
            Member member = MembersClient.findMemberByEmailAddress(login);
            return member.getScreenName();
        }
        return login;
    }

}

