package org.xcolab.view.pages.profile.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.MessagingUserPreferences;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.profile.utils.JSONHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("view")
public class MessageSettingsJSONController extends JSONHelper {

    public MessageSettingsJSONController() { }

    @ResourceMapping("updateUserSendEmailOnMessageSettings")
    public @ResponseBody void handleUpdateUserSendEmailOnMessageSettingAJAXRequest(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam("messageSetting") String messageSettingParameter) {

        boolean messageSetting = Boolean.parseBoolean(messageSettingParameter);
        boolean result = updateSendEmailOnMessageSettings(request, messageSetting);
        this.writeSuccessResultResponseJSON(result, response);
    }

    private boolean updateSendEmailOnMessageSettings(HttpServletRequest request, boolean messageSetting) {
        long memberId = MemberAuthUtil.getMemberId(request);
        updateUserSendEmailOnMessagePreferences(memberId, messageSetting);
        return true;
    }

    private void updateUserSendEmailOnMessagePreferences(long memberId, boolean setting) {
        MessagingUserPreferences preferences = MessagingClient.getMessagingPreferencesForMember(memberId);
        preferences.setEmailOnReceipt(setting);
        MessagingClient.updateMessagingPreferences(preferences);
    }

    @ResourceMapping("updateUserSendEmailOnActivitySettings")
    public @ResponseBody void handleUpdateUserSendEmailOnActivityAJAXRequest(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam("messageSetting") String messageSettingParameter) {

        boolean messageSetting = Boolean.parseBoolean(messageSettingParameter);
        boolean result = updateSendEmailOnActivitySettings(request, messageSetting);
        this.writeSuccessResultResponseJSON(result, response);
    }

    private boolean updateSendEmailOnActivitySettings(HttpServletRequest request, boolean messageSetting) {
        long memberId = MemberAuthUtil.getMemberId(request);
        updateUserSendEmailOnActivityPreferences(memberId, messageSetting);
        if (!messageSetting) {
            updateUserSendDailyEmailOnActivityPreferences(memberId, false);
        }
        return true;
    }

    private void updateUserSendEmailOnActivityPreferences(long memberId, boolean setting) {
        MessagingUserPreferences preferences = MessagingClient.getMessagingPreferencesForMember(memberId);
        preferences.setEmailOnActivity(setting);
        MessagingClient.updateMessagingPreferences(preferences);
    }

    private void updateUserSendDailyEmailOnActivityPreferences(long memberId, boolean setting) {
        MessagingUserPreferences preferences = MessagingClient.getMessagingPreferencesForMember(memberId);
        preferences.setEmailActivityDailyDigest(setting);
        MessagingClient.updateMessagingPreferences(preferences);
    }

    @ResourceMapping("updateUserSendDailyEmailOnActivitySettings")
    public @ResponseBody void handleUpdateUserSendDailyEmailOnActivityAJAXRequest(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam("messageSetting") String messageSettingParameter) {

        boolean messageSetting = Boolean.parseBoolean(messageSettingParameter);
        boolean result = updateSendDailyEmailOnActivitySettings(request, messageSetting);
        this.writeSuccessResultResponseJSON(result, response);
    }

    private boolean updateSendDailyEmailOnActivitySettings(HttpServletRequest request, boolean messageSetting) {
        long memberId = MemberAuthUtil.getMemberId(request);
        updateUserSendDailyEmailOnActivityPreferences(memberId, messageSetting);
        return true;
    }

}
