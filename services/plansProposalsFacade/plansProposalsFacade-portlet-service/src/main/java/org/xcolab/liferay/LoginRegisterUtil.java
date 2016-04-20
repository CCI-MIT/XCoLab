package org.xcolab.liferay;

import com.ext.portlet.community.CommunityConstants;
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
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import org.apache.commons.lang3.StringUtils;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.User_;
import org.xcolab.utils.CountryUtil;
import org.xcolab.utils.HtmlUtil;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

//TODO: temporary class for liferay transition
//attributes with "liferay" prefix should be removed later
public final class LoginRegisterUtil {

    private static final long LIFERAY_COMPANY_ID = 10112L;

    private LoginRegisterUtil() {
    }

    public static User_ register(String screenName, String password, String email, String firstName, String lastName,
            String shortBio, String country, String fbIdString, String openId, String imageId,
            Locale liferayLocale, ServiceContext liferayServiceContext)
            throws Exception {
        User liferayUser = UserServiceUtil.addUserWithWorkflow(LIFERAY_COMPANY_ID, false,
                password, password, false, screenName, email,
                0L, "", liferayLocale, HtmlUtil.cleanAll(firstName), "", HtmlUtil.cleanAll(lastName),
                0, 0, true, 1, 1, 1970, "", new long[]{}, new long[]{}, new long[]{},
                new long[]{}, true, liferayServiceContext);

        //TODO: need to be moved to database
        if (shortBio != null && !shortBio.isEmpty()) {
            setExpandoValue(liferayUser, CommunityConstants.BIO,
                    HtmlUtil.cleanSome(shortBio, ConfigurationAttributeKey.COLAB_URL.getStringValue()));
        }
        if (country != null && !country.isEmpty()) {
            setExpandoValue(liferayUser, CommunityConstants.COUNTRY,
                    CountryUtil.getCountryForCode(country));
        }

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

        return MembersClient.getMember(liferayUser.getUserId());
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

    private static void setExpandoValue(User user, String valueName, Object data)
            throws SystemException, PortalException {
        ExpandoValueLocalServiceUtil.addValue(
                user.getCompanyId(),
                User.class.getName(),
                CommunityConstants.EXPANDO,
                valueName,
                user.getUserId(),
                data);
    }
}
