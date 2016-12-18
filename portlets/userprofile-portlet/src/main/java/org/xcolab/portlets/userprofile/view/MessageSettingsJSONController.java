package org.xcolab.portlets.userprofile.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.pojo.MessagingUserPreferences;
import org.xcolab.entity.utils.members.MemberAuthUtil;
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
            PortletRequest request, ResourceResponse response,
            @RequestParam("messageSetting") String messageSettingParameter) {

        boolean messageSetting = Boolean.parseBoolean(messageSettingParameter);
        boolean result = updateSendEmailOnActivitySettings(request, messageSetting);
        this.writeSuccessResultResponseJSON(result, response);
    }

    private boolean updateSendEmailOnActivitySettings(PortletRequest request, boolean messageSetting) {
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
            PortletRequest request, ResourceResponse response,
            @RequestParam("messageSetting") String messageSettingParameter) {

        boolean messageSetting = Boolean.parseBoolean(messageSettingParameter);
        boolean result = updateSendDailyEmailOnActivitySettings(request, messageSetting);
        this.writeSuccessResultResponseJSON(result, response);
    }

    private boolean updateSendDailyEmailOnActivitySettings(PortletRequest request, boolean messageSetting) {
        long memberId = MemberAuthUtil.getMemberId(request);
        updateUserSendDailyEmailOnActivityPreferences(memberId, messageSetting);
        return true;
    }

}
