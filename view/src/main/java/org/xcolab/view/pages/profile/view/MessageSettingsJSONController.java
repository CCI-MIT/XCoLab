package org.xcolab.view.pages.profile.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.xcolab.client.user.IMessagingClient;
import org.xcolab.client.user.pojo.wrapper.MessagingUserPreferenceWrapper;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.profile.utils.JSONHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/members/profile/{userId}/api/settings/messaging")
public class MessageSettingsJSONController extends JSONHelper {

    private final IMessagingClient messagingClient;

    @Autowired
    public MessageSettingsJSONController(IMessagingClient messagingClient) {
        this.messagingClient = messagingClient;
    }

    @PostMapping("updateEmailOnMessage")
    public @ResponseBody void handleUpdateUserSendEmailOnMessageSettingAJAXRequest(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable long userId,
            @RequestParam("messageSetting") String messageSettingParameter) {

        boolean messageSetting = Boolean.parseBoolean(messageSettingParameter);
        boolean result = updateSendEmailOnMessageSettings(messageSetting);
        this.writeSuccessResultResponseJSON(result, response);
    }

    private boolean updateSendEmailOnMessageSettings(boolean messageSetting) {
        long userId = MemberAuthUtil.getUserId();
        updateUserSendEmailOnMessagePreferences(userId, messageSetting);
        return true;
    }

    private void updateUserSendEmailOnMessagePreferences(long userId, boolean setting) {
        MessagingUserPreferenceWrapper preferences = messagingClient.getMessagingPreferences(userId);
        preferences.setEmailOnReceipt(setting);
        messagingClient.updateMessagingPreferences(userId, preferences.getId(), preferences);
    }

    @PostMapping("updateEmailOnActivity")
    public @ResponseBody void handleUpdateUserSendEmailOnActivityAJAXRequest(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable long userId,
            @RequestParam("messageSetting") String messageSettingParameter) {

        boolean messageSetting = Boolean.parseBoolean(messageSettingParameter);
        boolean result = updateSendEmailOnActivitySettings(messageSetting);
        this.writeSuccessResultResponseJSON(result, response);
    }

    private boolean updateSendEmailOnActivitySettings(boolean messageSetting) {
        long userId = MemberAuthUtil.getUserId();
        updateUserSendEmailOnActivityPreferences(userId, messageSetting);
        if (!messageSetting) {
            updateUserSendDailyEmailOnActivityPreferences(userId, false);
        }
        return true;
    }

    private void updateUserSendEmailOnActivityPreferences(long userId, boolean setting) {
        MessagingUserPreferenceWrapper preferences = messagingClient.getMessagingPreferences(userId);
        preferences.setEmailOnActivity(setting);
        messagingClient.updateMessagingPreferences(userId, preferences.getId(), preferences);
    }

    private void updateUserSendDailyEmailOnActivityPreferences(long userId, boolean setting) {
        MessagingUserPreferenceWrapper preferences = messagingClient.getMessagingPreferences(userId);
        preferences.setEmailActivityDailyDigest(setting);
        messagingClient.updateMessagingPreferences(userId, preferences.getId(), preferences);
    }

    @PostMapping("updateDailyEmail")
    public @ResponseBody void handleUpdateUserSendDailyEmailOnActivityAJAXRequest(
            HttpServletRequest request, HttpServletResponse response,
            @PathVariable long userId,
            @RequestParam("messageSetting") String messageSettingParameter) {

        boolean messageSetting = Boolean.parseBoolean(messageSettingParameter);
        boolean result = updateSendDailyEmailOnActivitySettings(messageSetting);
        this.writeSuccessResultResponseJSON(result, response);
    }

    private boolean updateSendDailyEmailOnActivitySettings(boolean messageSetting) {
        long userId = MemberAuthUtil.getUserId();
        updateUserSendDailyEmailOnActivityPreferences(userId, messageSetting);
        return true;
    }

}
