package org.xcolab.portlets.userprofile.view;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.MessagingUserPreferences;
import org.xcolab.portlets.userprofile.utils.JSONHelper;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;

@Controller
@RequestMapping("view")
public class MessageSettingsJSONController extends JSONHelper {

    public MessageSettingsJSONController() { }

    @ResourceMapping("updateUserSendEmailOnMessageSettings")
    public @ResponseBody void handleUpdateUserSendEmailOnMessageSettingAJAXRequest(
            PortletRequest request, ResourceResponse response,
            @RequestParam("messageSetting") String messageSettingParameter) {

        boolean messageSetting = Boolean.parseBoolean(messageSettingParameter);
        boolean result = updateSendEmailOnMessageSettings(request, messageSetting);
        this.writeSuccessResultResponseJSON(result, response);
    }

    private boolean updateSendEmailOnMessageSettings(PortletRequest request, boolean messageSetting) {
        try {
            User user = PortalUtil.getUser(request);
            updateUserSendEmailOnMessagePreferences(user, messageSetting);
        } catch (PortalException | SystemException e) {
            return false;
        }
        return true;
    }

    private void updateUserSendEmailOnMessagePreferences(User user, boolean setting) {
        MessagingUserPreferences preferences = MessagingClient.getMessagingPreferencesForMember(user.getUserId());
        preferences.setEmailOnReceipt(setting);
        MessagingClient.updateMessagingPreferences(preferences);
    }

    @ResourceMapping("updateUserSendEmailOnActivitySettings")
    public @ResponseBody void handleUpdateUserSendEmailOnActivityAJAXRequest(
            PortletRequest request, ResourceResponse response,
            @RequestParam("messageSetting") String messageSettingParameter) {

        boolean messageSetting = Boolean.parseBoolean(messageSettingParameter);
        boolean result = updateSendEmailOnActivitySettings(request, messageSetting);
        this.writeSuccessResultResponseJSON(result, response);
    }

    private boolean updateSendEmailOnActivitySettings(PortletRequest request, boolean messageSetting) {
        try {
            User user = PortalUtil.getUser(request);
            updateUserSendEmailOnActivityPreferences(user, messageSetting);
            if (!messageSetting) {
                updateUserSendDailyEmailOnActivityPreferences(user, false);
            }
        } catch (PortalException | SystemException e) {
            return false;
        }
        return true;
    }

    private void updateUserSendEmailOnActivityPreferences(User user, boolean setting) {
        MessagingUserPreferences preferences = MessagingClient.getMessagingPreferencesForMember(user.getUserId());
        preferences.setEmailOnActivity(setting);
        MessagingClient.updateMessagingPreferences(preferences);
    }

    private void updateUserSendDailyEmailOnActivityPreferences(User user, boolean setting) {
        MessagingUserPreferences preferences = MessagingClient.getMessagingPreferencesForMember(user.getUserId());
        preferences.setEmailActivityDailyDigest(setting);
        MessagingClient.updateMessagingPreferences(preferences);
    }

    @ResourceMapping("updateUserSendDailyEmailOnActivitySettings")
    public @ResponseBody void handleUpdateUserSendDailyEmailOnActivityAJAXRequest(
            PortletRequest request, ResourceResponse response,
            @RequestParam("messageSetting") String messageSettingParameter) {

        boolean messageSetting = Boolean.parseBoolean(messageSettingParameter);
        boolean result = updateSendDailyEmailOnActivitySettings(request, messageSetting);
        this.writeSuccessResultResponseJSON(result, response);
    }

    private boolean updateSendDailyEmailOnActivitySettings(PortletRequest request, boolean messageSetting) {
        try {
            User user = PortalUtil.getUser(request);
            updateUserSendDailyEmailOnActivityPreferences(user, messageSetting);
        } catch (PortalException | SystemException e) {
            return false;
        }
        return true;
    }

}
