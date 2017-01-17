package org.xcolab.view.pages.proposals.view;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.MessagingClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.messaging.MessageLimitExceededException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Klemens Mang on 16.03.14.
 */
@Controller
//-- @RequestMapping("view")
public class ProposalShareJSONController {

	@Autowired
	private ProposalsContext proposalsContext;

    private static final String SUCCESS_JSON_KEY = "success";
    private static final String MESSAGE_JSON_KEY = "message";

    private List<Long> parseRecipientNames(List<String> recipients) throws RecipientParseException {
        List<Long> recipientIds = new ArrayList<>();
        List<String> unresolvedRecipients = new ArrayList<>();
        for (String recipient : recipients) {
            if (recipient.equals("")) {
                continue;
            }
            try {
                Member user = MembersClient.findMemberByScreenName(recipient);
                recipientIds.add(user.getUserId());
            } catch (MemberNotFoundException  e) {
                unresolvedRecipients.add(recipient);
            }
        }

        if (!unresolvedRecipients.isEmpty()) {
            throw new RecipientParseException("Could not parse all recipients.", unresolvedRecipients);
        }

        return recipientIds;
    }

    private void sendResponseJSON (boolean success, String message, HttpServletResponse response) throws IOException {
        JSONObject json = new JSONObject();
        json.put(SUCCESS_JSON_KEY, success);
        json.put(MESSAGE_JSON_KEY, message);

        response.getOutputStream().write(json.toString().getBytes());
    }

    private void sendResponseJSON (boolean success, JSONArray message, HttpServletResponse response) throws IOException {
        JSONObject json = new JSONObject();
        json.put(SUCCESS_JSON_KEY, success);
        json.put(MESSAGE_JSON_KEY, message);

        response.getOutputStream().write(json.toString().getBytes());
    }

    //-- @ResourceMapping("proposalShare-validate")
    public void validateRecipients(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] screenNames = request.getParameterValues("screenNames[]");

        try {
            parseRecipientNames(Arrays.asList(screenNames));
        } catch (RecipientParseException e) {
            JSONArray array = new JSONArray();
            for (String screenName : e.getUnresolvedScreenNames()) {
                array.put(screenName);
            }
            sendResponseJSON(false, array, response);
            return;
        }

        sendResponseJSON(true, "", response);
    }

    //-- @ResourceMapping("proposalShare-send")
    public void send(HttpServletRequest request, HttpServletResponse response) throws IOException {



        // Parse parameters
		String[] recipients = request.getParameterValues("recipients[]");
		String subject = request.getParameter("subject");
		String body = request.getParameter("body");

		// Add link to proposal
		ContestPhase phase = proposalsContext.getContestPhase(request);
        final Contest contest = proposalsContext.getContest(request);
        final Proposal proposal = proposalsContext.getProposal(request);
        String proposalUrl = ConfigurationAttributeKey.COLAB_URL.get();

        if (phase == null || phase.getContestPhasePK() <= 0) {
			proposalUrl += proposal.getProposalLinkUrl(contest);
		} else {
			proposalUrl += proposal.getProposalLinkUrl(contest, phase.getContestPhasePK());
		}
        ContestType contestType = ContestClientUtil.getContestType(contest.getContestTypeId());
		body += String.format("<p><a href='%s'>Link to %s</a></p>", proposalUrl, contestType.getProposalName());

		// Send the message
        long memberId = MemberAuthUtil.getMemberId(request);
        // Validate the screenName input
        List<Long> recipientIds = null;
        try {
            List<String> recipientsList = Arrays.asList(recipients);
			recipientIds = parseRecipientNames(recipientsList);
        } catch (RecipientParseException e) {
            List<String> unresolvedRecipients = e.getUnresolvedScreenNames();
            StringBuilder builder = new StringBuilder();
            if (unresolvedRecipients.size() == 1) {
                builder.append(unresolvedRecipients.get(0));
            } else {
                for (String recipient : e.getUnresolvedScreenNames()) {
                    builder.append(recipient).append(", ");
                }
            }

            sendResponseJSON(false, builder.toString(), response);
        }

        // Send the message
        if (recipientIds != null) {
            try {
                MessagingClient.checkLimitAndSendMessage(subject, body, memberId, recipientIds);
                sendResponseJSON(true, String.format("You successfully shared the %s", contestType.getProposalName()), response);
            } catch (MessageLimitExceededException e) {
                sendResponseJSON(false, "Messages limit has been exceeded, if you want to send more messages, " +
                        "please contact the administrators.", response);
            }
        }
    }

    private static class RecipientParseException extends Exception {
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
