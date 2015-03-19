package org.xcolab.portlets.userprofile.view;

import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.MessagingUserPreferences;
import com.ext.portlet.service.MessagingUserPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.portlets.userprofile.utils.JSONHelper;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;

/**
 * Created by Thomas on 1/12/2015.
 */
@Controller
@RequestMapping("view")
public class MessageSettingsJSONController extends JSONHelper{

    public MessageSettingsJSONController(){
    }

    @ResourceMapping("updateUserSendEmailOnMessageSettings")
    public @ResponseBody
    void handleUpdateUserSendEmailOnMessageSettingAJAXRequest(
            PortletRequest request,
            ResourceResponse response,
            @RequestParam("messageSetting") String messageSettingParameter
    ) throws IOException {

        boolean messageSetting = Boolean.parseBoolean(messageSettingParameter);
        boolean result = updateSendEmailOnMessageSettings(request, messageSetting);
        this.writeSuccessResultResponseJSON(result, response);
    }

    @ResourceMapping("updateUserSendEmailOnActivitySettings")
    public @ResponseBody
    void handleUpdateUserSendEmailOnActivityAJAXRequest(
            PortletRequest request,
            ResourceResponse response,
            @RequestParam("messageSetting") String messageSettingParameter
    ) throws IOException {

        boolean messageSetting = Boolean.parseBoolean(messageSettingParameter);
        boolean result = updateSendEmailOnActivitySettings(request,messageSetting);
        this.writeSuccessResultResponseJSON(result, response);
    }

    @ResourceMapping("updateUserSendDailyEmailOnActivitySettings")
    public @ResponseBody
    void handleUpdateUserSendDailyEmailOnActivityAJAXRequest(
            PortletRequest request,
            ResourceResponse response,
            @RequestParam("messageSetting") String messageSettingParameter
    ) throws PortalException, SystemException, IOException {

        boolean messageSetting = Boolean.parseBoolean(messageSettingParameter);
        boolean result = updateSendDailyEmailOnActivitySettings(request, messageSetting);
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

    private boolean updateSendEmailOnActivitySettings(PortletRequest request, boolean messageSetting) {
        try {
            User user = PortalUtil.getUser(request);
            updateUserSendEmailOnActivityPreferences(user, messageSetting);
            if(!messageSetting) updateUserSendDailyEmailOnActivityPreferences(user, messageSetting);
        } catch (PortalException | SystemException e) {
            return false;
        }
        return true;
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

    private void updateUserSendEmailOnMessagePreferences(User user, boolean setting) throws SystemException{

        MessagingUserPreferences prefs = MessageUtil.getMessagingPreferences(user.getUserId());
        prefs.setEmailOnReceipt(setting);
        MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(prefs);

    }

    private void updateUserSendEmailOnActivityPreferences(User user, boolean setting) throws SystemException{

        MessagingUserPreferences prefs = MessageUtil.getMessagingPreferences(user.getUserId());
        prefs.setEmailOnActivity(setting);
        MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(prefs);

    }

    private void updateUserSendDailyEmailOnActivityPreferences(User user, boolean setting) throws SystemException{

        MessagingUserPreferences prefs = MessageUtil.getMessagingPreferences(user.getUserId());
        prefs.setEmailActivityDailyDigest(setting);
        MessagingUserPreferencesLocalServiceUtil.updateMessagingUserPreferences(prefs);

    }

}
