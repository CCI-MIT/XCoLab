package org.xcolab.portlets.proposals.view;

import com.ext.portlet.messaging.MessageUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.mail.MailEngineException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import javax.mail.internet.AddressException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Klemens Mang on 16.03.14.
 */
@Controller
@RequestMapping("view")
public class ProposalShareJSONController {

    private static final String RECIPIENT_LIST_JSON_KEY = "recipients";
    private static final String RECIPIENT_JSON_KEY = "recipient";
    private static final String SUBJECT_JSON_KEY = "subject";
    private static final String BODY_JSON_KEY = "body";
    private static final String SUCCESS_JSON_KEY = "success";
    private static final String MESSAGE_JSON_KEY = "message";

    private JSONObject parseJSONString(ResourceRequest request) throws JSONException {
        String jsonString = (String)request.getAttribute("params");
        JSONObject json = JSONFactoryUtil.createJSONObject(jsonString);
        return json;
    }

    private List<Long> parseRecipientNames(long companyId, JSONArray recipients) throws RecipientParseException {
        List<Long> recipientIds = new ArrayList<Long>();
        List<String> unresolvedRecipients = new ArrayList<>();
        for (int i = 0; i < recipients.length(); i++) {
            String recipient = recipients.getString(i);
            try {
                User user = UserLocalServiceUtil.getUserByScreenName(companyId, recipient);
                recipientIds.add(user.getUserId());
            } catch (PortalException | SystemException e) {
                unresolvedRecipients.add(recipient);
            }
        }

        if (unresolvedRecipients.size() > 0) {
            throw new RecipientParseException("Could not parse all recipients.", unresolvedRecipients);
        }

        return recipientIds;
    }

    private void sendResponseJSON (boolean success, String message, ResourceResponse response) throws IOException {
        JSONObject json = JSONFactoryUtil.createJSONObject();
        json.put(SUCCESS_JSON_KEY, success);
        json.put(MESSAGE_JSON_KEY, message);

        response.getPortletOutputStream().write(json.toString().getBytes());
    }

    @ResourceMapping("proposalShare-autocomplete")
    public void autocompleteRecipient(ResourceRequest request, ResourceResponse response) throws PortalException, IOException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        String queryString = request.getParameter("term");

        try {
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(User.class);
            query.add(RestrictionsFactoryUtil.ilike("screenName", queryString + "%"));
            List<User> recipients = UserLocalServiceUtil.dynamicQuery(query);

            JSONArray result = JSONFactoryUtil.createJSONArray();
            for (User user: recipients) {
                final JSONObject userObject = JSONFactoryUtil.createJSONObject();
                userObject.put("id", user.getUserId());
                userObject.put("label", user.getScreenName());
                userObject.put("value", user.getScreenName());
                result.put(userObject);
            }

            response.getPortletOutputStream().write(result.toString().getBytes());
        } catch (SystemException e) {
            e.printStackTrace();

            sendResponseJSON(false, "The request could not be processed.", response);
        }
    }

    @ResourceMapping("proposalShare-validate")
    public void validateRecipients(ResourceRequest request, ResourceResponse response) throws PortalException, IOException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        String queryString = request.getParameter("recipients");

        try {
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(User.class);
            query.add(RestrictionsFactoryUtil.ilike("screenName", queryString + "%"));
            List<User> recipients = UserLocalServiceUtil.dynamicQuery(query);

            JSONArray result = JSONFactoryUtil.createJSONArray();
            for (User user: recipients) {
                final JSONObject userObject = JSONFactoryUtil.createJSONObject();
                userObject.put("id", user.getUserId());
                userObject.put("label", user.getScreenName());
                userObject.put("value", user.getScreenName());
                result.put(userObject);
            }

            response.getPortletOutputStream().write(result.toString().getBytes());
        } catch (SystemException e) {
            e.printStackTrace();

            sendResponseJSON(false, "The request could not be processed.", response);
        }
    }

    @ResourceMapping("proposalShare-send")
    public void send(ResourceRequest request, ResourceResponse response) throws SystemException, PortalException, IOException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        JSONObject json = parseJSONString(request);

        // Parse parameters
        JSONArray recipients = json.getJSONArray(RECIPIENT_LIST_JSON_KEY);
        String subject = json.getString(SUBJECT_JSON_KEY);
        String body = json.getString(BODY_JSON_KEY);


        Long userId = themeDisplay.getUserId();
        //Long mutex = MessageLimitManager.getMutex(userId);
        //synchronized (mutex) {
//            if (!MessageLimitManager.canSendMessages(recipientIds.size())) {
//                System.err.println("OBSERVED VALIDATION PROBLEM AGAIN. "+userId);
//
//                recipientIds.clear();
//                recipientIds.add(1011659L); //patrick
//                MessageUtil.sendMessage("VALIDATION PROBLEM  "+subject, "VALIDATION PROBLEM  "+content, userId,
//                        Helper.getLiferayUser().getUserId(), recipientIds, null);
//
//                return;
//            }

        // Validate the screenName input
        List<Long> recipientIds = null;
        try {
            recipientIds = parseRecipientNames(themeDisplay.getCompanyId(), recipients);
        } catch (RecipientParseException e) {
            List<String> unresolvedRecipients = e.getUnresolvedScreenNames();
            StringBuilder builder = new StringBuilder();
            if (unresolvedRecipients.size() == 1) {
                builder.append(unresolvedRecipients.get(0));
            } else {
                for (String recipient : e.getUnresolvedScreenNames()) {
                    builder.append(recipient + ", ");
                }
            }

            sendResponseJSON(false, builder.toString(), response);
        }

        // Send the message
        try {
            MessageUtil.sendMessage(subject, body, userId,
                    userId, recipientIds, null);
        } catch (com.liferay.portal.kernel.exception.SystemException | AddressException | MailEngineException e) {
            sendResponseJSON(false, "The proposal could not be shared.", response);
        }

        sendResponseJSON(true, "You successfully shared the proposal", response);
    }

    class RecipientParseException extends Exception {
        public List<String> getUnresolvedScreenNames() {
            return unresolvedScreenNames;
        }

        List<String> unresolvedScreenNames;

        public RecipientParseException(String message, List<String> unresolvedScreenNames) {
            super(message);
            this.unresolvedScreenNames = unresolvedScreenNames;
        }
    }
}
