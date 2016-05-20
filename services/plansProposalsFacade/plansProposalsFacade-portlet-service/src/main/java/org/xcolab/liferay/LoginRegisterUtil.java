package org.xcolab.liferay;

import com.ext.portlet.service.LoginLogLocalServiceUtil;
import com.ext.utils.authentication.service.AuthenticationServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.util.PortalUtil;

import org.apache.commons.lang3.StringUtils;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.HtmlUtil;
import org.xcolab.utils.HtmlUtil;
import org.xcolab.utils.emailnotification.member.MemberRegistrationNotification;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

//TODO: temporary class for liferay transition
//attributes with "liferay" prefix should be removed later
public final class LoginRegisterUtil {

    private static final long LIFERAY_COMPANY_ID = 10112L;

    private LoginRegisterUtil() {
    }

    public static void updatePassword(String forgotPasswordToken, String newPassword) throws MemberNotFoundException , PortalException, SystemException{
        Long memberId = MembersClient.updateUserPassword(forgotPasswordToken, newPassword);
        if (memberId != null) {
            //TODO: remove, currently needed to update password for liferay
            final User liferayUser = UserLocalServiceUtil.getUser(memberId);
            liferayUser.setPassword
                    (MembersClient.hashPassword(newPassword.trim(), true));
            UserLocalServiceUtil.updateUser(liferayUser);
        }else {

            throw new MemberNotFoundException("Member with forgotPasswordToken: " + forgotPasswordToken + " was not found");
        }
    }

    public static Member register(String screenName, String password, String email, String firstName, String lastName,
                                  String shortBio, String country, String fbIdString, String openId, String imageId,
                                  Locale liferayLocale, ServiceContext liferayServiceContext)
            throws Exception {
        User liferayUser = UserServiceUtil.addUserWithWorkflow(LIFERAY_COMPANY_ID, false,
                password, password, false, screenName, email,
                0L, "", liferayLocale, HtmlUtil.cleanAll(firstName), "", HtmlUtil.cleanAll(lastName),
                0, 0, true, 1, 1, 1970, "", new long[]{}, new long[]{}, new long[]{},
                new long[]{}, true, liferayServiceContext);
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

        if (imageId != null && !imageId.isEmpty()) {
            Image img = ImageLocalServiceUtil.getImage(Long.parseLong(imageId));
            //TODO: we need to set permission checker for liferay
            PermissionChecker permissionChecker = PermissionCheckerFactoryUtil.create(liferayUser);
            PermissionThreadLocal.setPermissionChecker(permissionChecker);

            if (img != null) {
                byte[] bytes = img.getTextObj();
                UserServiceUtil.updatePortrait(liferayUser.getUserId(), bytes);
                liferayUser.setPortraitId(0L);
                UserLocalServiceUtil.updateUser(liferayUser);
                UserServiceUtil.updatePortrait(liferayUser.getUserId(), bytes);
                liferayUser = UserLocalServiceUtil.getUser(liferayUser.getUserId());
            }
        }
        sendEmailNotificationToRegisteredUser(liferayServiceContext, liferayUser);

        return MembersClient.getMember(liferayUser.getUserId());
    }

    private static void sendEmailNotificationToRegisteredUser(ServiceContext serviceContext, User recipient)
            throws PortalException, SystemException {
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
        final String screenName = getScreenNameFromLogin(login);
        AuthenticationServiceUtil.logUserIn(request, response, screenName, password);
        User user = UserLocalServiceUtil.getUserByScreenName(LIFERAY_COMPANY_ID, login);
        LoginLogLocalServiceUtil.createLoginLog(user, PortalUtil.getHttpServletRequest(request).getRemoteAddr(), referer);
        return user;
    }

    private static String getScreenNameFromLogin(String login) throws SystemException, PortalException {
        if (login.contains("@")) {
            User user = UserLocalServiceUtil.getUserByEmailAddress(LIFERAY_COMPANY_ID, login);
            return user.getScreenName();
        }
        return login;
    }
}
