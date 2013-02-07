/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.xcolab.portlets.massmessaging.action;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xcolab.portlets.massmessaging.MessagingConstants;

import com.ext.portlet.NoSuchMessagingIgnoredRecipientsException;
import com.ext.portlet.model.MessagingIgnoredRecipients;
import com.ext.portlet.service.MessagingIgnoredRecipientsLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

public class ManageIgnoredRecipientsAction extends BaseStrutsPortletAction {

    private final static String emailValidationRegexp = "^([a-zA-Z0-9_\\.-])+@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})+$";
    private final static String screenNameValidationRegexp = "^([a-zA-Z0-9_\\.-])+$";

    public ActionForward render(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {

        return mapping.findForward(MessagingConstants.MANAGE_IGNORED_RECIPIENTS_FORWARD);
    }

    public void processAction(ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
            ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        String name = ParamUtil.getString(actionRequest, "name");
        String redirect = ParamUtil.getString(actionRequest, "redirect");
        String op = ParamUtil.getString(actionRequest, "op");

        actionResponse.sendRedirect(redirect);
        long deletedRecipientId = ParamUtil.getLong(actionRequest, "recipientId");

        if (op.equals("Add")) {
            ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

            User user = null;
            // validate name
            if (name.matches(screenNameValidationRegexp)) {
                try {
                    user = UserLocalServiceUtil.getUserByScreenName(themeDisplay.getCompanyId(), name);
                } catch (NoSuchUserException e) {
                    SessionErrors.add(actionRequest, "invalidScreenName");
                    return;
                }
            } else if (!name.matches(emailValidationRegexp)) {
                // name doesn't represent either valid email or screen name,
                // report that to the user
                SessionErrors.add(actionRequest, "invalidName");
                return;
            }

            boolean ignoredRecipientExists = true;
            // check if ignored recipient isn't already present in the DB
            try {
                if (user != null) {
                    MessagingIgnoredRecipientsLocalServiceUtil.findByUserId(user.getUserId());
                } else {
                    MessagingIgnoredRecipientsLocalServiceUtil.findByEmail(name);
                }
            } catch (NoSuchMessagingIgnoredRecipientsException e) {
                // this is expected and should happen
                ignoredRecipientExists = false;
            }

            if (ignoredRecipientExists) {
                SessionErrors.add(actionRequest, "ignoredRecipientAlreadyExists");
                return;
            }

            // save
            Long ignoredRecipientId = CounterLocalServiceUtil.increment(MessagingIgnoredRecipients.class.getName());
            MessagingIgnoredRecipients ignoredRecipient = MessagingIgnoredRecipientsLocalServiceUtil
                    .createMessagingIgnoredRecipients(ignoredRecipientId);

            if (user != null) {
                ignoredRecipient.setUserId(user.getUserId());
                ignoredRecipient.setName(user.getScreenName());
                ignoredRecipient.setEmail(user.getEmailAddress());
            }
            else {
                ignoredRecipient.setEmail(name);
            }
            ignoredRecipient.setCreateDate(new Date());

            MessagingIgnoredRecipientsLocalServiceUtil.addMessagingIgnoredRecipients(ignoredRecipient);
        } else if (op.equals("Delete")) {
            MessagingIgnoredRecipientsLocalServiceUtil.deleteMessagingIgnoredRecipients(deletedRecipientId);
        }
    }
}
