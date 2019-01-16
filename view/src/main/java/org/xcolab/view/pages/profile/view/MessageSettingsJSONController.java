package org.xcolab.view.pages.profile.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.xcolab.client.user.MessagingClient;
import org.xcolab.client.user.pojo.MessagingUserPreference;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.profile.utils.JSONHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/members/profile/{userId}/api/settings/messaging")
public class MessageSettingsJSONController extends JSONHelper {

    public MessageSettingsJSONController() { }

    @PostMapping("updateEmailOnMessage")
    public @ResponseBody void handleUpdateUserSendEmailOnMessageSettingAJAXRequest(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable long userId,
            @RequestParam("messageSetting") String messageSettingParameter) {

        boolean messageSetting = Boolean.parseBoolean(messageSettingParameter);
        boolean result = updateSendEmailOnMessageSettings(request, messageSetting);
        this.writeSuccessResultResponseJSON(result, response);
    }

    private boolean updateSendEmailOnMessageSettings(HttpServletRequest request, boolean messageSetting) {
        long userId = MemberAuthUtil.getuserId(request);
        updateUserSendEmailOnMessagePreferences(userId, messageSetting);
        return true;
    }

    private void updateUserSendEmailOnMessagePreferences(long userId, boolean setting) {
        MessagingUserPreference preferences = MessagingClient.getMessagingPreferencesForMember(userId);
        preferences.setEmailOnReceipt(setting);
        MessagingClient.updateMessagingPreferences(preferences);
    }

    @PostMapping("updateEmailOnActivity")
    public @ResponseBody void handleUpdateUserSendEmailOnActivityAJAXRequest(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable long userId,
            @RequestParam("messageSetting") String messageSettingParameter) {

        boolean messageSetting = Boolean.parseBoolean(messageSettingParameter);
        boolean result = updateSendEmailOnActivitySettings(request, messageSetting);
        this.writeSuccessResultResponseJSON(result, response);
    }

    private boolean updateSendEmailOnActivitySettings(HttpServletRequest request, boolean messageSetting) {
        long userId = MemberAuthUtil.getuserId(request);
        updateUserSendEmailOnActivityPreferences(userId, messageSetting);
        if (!messageSetting) {
            updateUserSendDailyEmailOnActivityPreferences(userId, false);
        }
        return true;
    }

    private void updateUserSendEmailOnActivityPreferences(long userId, boolean setting) {
        MessagingUserPreference preferences = MessagingClient.getMessagingPreferencesForMember(userId);
        preferences.setEmailOnActivity(setting);
        MessagingClient.updateMessagingPreferences(preferences);
    }

    private void updateUserSendDailyEmailOnActivityPreferences(long userId, boolean setting) {
        MessagingUserPreference preferences = MessagingClient.getMessagingPreferencesForMember(userId);
        preferences.setEmailActivityDailyDigest(setting);
        MessagingClient.updateMessagingPreferences(preferences);
    }

    @PostMapping("updateDailyEmail")
    public @ResponseBody void handleUpdateUserSendDailyEmailOnActivityAJAXRequest(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable long userId,
            @RequestParam("messageSetting") String messageSettingParameter) {

        boolean messageSetting = Boolean.parseBoolean(messageSettingParameter);
        boolean result = updateSendDailyEmailOnActivitySettings(request, messageSetting);
        this.writeSuccessResultResponseJSON(result, response);
    }

    private boolean updateSendDailyEmailOnActivitySettings(HttpServletRequest request, boolean messageSetting) {
        long userId = MemberAuthUtil.getuserId(request);
        updateUserSendDailyEmailOnActivityPreferences(userId, messageSetting);
        return true;
    }

}
