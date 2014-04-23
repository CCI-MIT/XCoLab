package org.xcolab.portlets.proposals.view;

import com.ext.PropertiesUtils;
import com.ext.portlet.community.CommunityConstants;
import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Message;
import com.ext.portlet.model.MessageRecipientStatus;
import com.ext.portlet.service.MessageLocalServiceUtil;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
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
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.util.mail.MailEngineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.utils.MessageLimitManager;

import javax.mail.internet.AddressException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Klemens Mang on 16.03.14.
 */
@Controller
@RequestMapping("view")
public class ProposalShareJSONController {

	private static final long companyId = 10112L;
	private static final String MESSAGES_LIMIT_COLUMN = "messages_limit";

	private static final String MESSAGE_ENTITY_CLASS_LOADER_CONTEXT = "plansProposalsFacade-portlet";

	private static Map<Long, Long> mutexes = new HashMap<>();

	@Autowired
	private ProposalsContext proposalsContext;

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

    private List<Long> parseRecipientNames(long companyId, List<String> recipients) throws RecipientParseException {
        List<Long> recipientIds = new ArrayList<Long>();
        List<String> unresolvedRecipients = new ArrayList<>();
        for (int i = 0; i < recipients.size(); i++) {
            String recipient = recipients.get(i);
			if (recipient.equals("")) {
				continue;
			}
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

    private void sendResponseJSON (boolean success, JSONArray message, ResourceResponse response) throws IOException {
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

        String[] screenNames = request.getParameterValues("screenNames[]");

        try {
            List<Long> users = parseRecipientNames(themeDisplay.getCompanyId(), ListUtil.fromArray(screenNames));
        } catch (RecipientParseException e) {
            JSONArray array = JSONFactoryUtil.createJSONArray();
            for (String screenName : e.getUnresolvedScreenNames()) {
                array.put(screenName);
            }
            sendResponseJSON(false, array, response);
            return;
        }

        sendResponseJSON(true, "", response);
    }

    @ResourceMapping("proposalShare-send")
    public void send(ResourceRequest request, ResourceResponse response) throws SystemException, PortalException, IOException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        JSONObject json = parseJSONString(request);

        // Parse parameters
		String[] recipients = request.getParameterValues("recipients[]");
		String subject = request.getParameter("subject");
		String body = request.getParameter("body");

		// Add link to proposal
		ContestPhase phase = proposalsContext.getContestPhase(request);
		String proposalUrl = themeDisplay.getPortalURL();
		if (phase == null || phase.getContestPhasePK() <= 0) {
			proposalUrl += String.format("/web/guest/plans/-/plans/contestId/%d/planId/%d", proposalsContext.getContest(request).getContestPK(), proposalsContext.getProposal(request).getProposalId());
		} else {
			proposalUrl += String.format("/web/guest/plans/-/plans/contestId/%d/phaseId/%d/planId/%d", proposalsContext.getContest(request).getContestPK(), phase.getContestPhasePK(), proposalsContext.getProposal(request).getProposalId());
		}

		body += String.format("\n\n<a href='%s'>Link to proposal</a>", proposalUrl);

		// Send the message
        Long userId = themeDisplay.getUserId();
        // Validate the screenName input
        List<Long> recipientIds = null;
        try {
            List<String> recipientsList = Arrays.asList(recipients);
			recipientIds = parseRecipientNames(themeDisplay.getCompanyId(), recipientsList);
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
			Long mutex = MessageLimitManager.getMutex(userId);
			User user = UserLocalServiceUtil.getUserById(userId);
			synchronized (mutex) {
				if (!MessageLimitManager.canSendMessages(recipientIds.size(), user)) {
					System.err.println("OBSERVED VALIDATION PROBLEM AGAIN. " + userId);

					recipientIds.clear();
					recipientIds.add(1011659L); //patrick
					MessageUtil.sendMessage("VALIDATION PROBLEM  " + subject, "VALIDATION PROBLEM  " + body, userId,
							userId, recipientIds, null);

					return;
				}
			}
            MessageUtil.sendMessage(subject, body, userId,
                    userId, recipientIds, null);
        } catch (com.liferay.portal.kernel.exception.SystemException | AddressException | MailEngineException e) {
            sendResponseJSON(false, "We were unable to share this proposal. Please try again later.", response);
			return;
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
