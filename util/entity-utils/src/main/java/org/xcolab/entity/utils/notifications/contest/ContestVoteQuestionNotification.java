package org.xcolab.entity.utils.notifications.contest;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.pojo.IEmailTemplate;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.entity.utils.notifications.basic.ContestNotification;

import java.util.List;

public class ContestVoteQuestionNotification extends ContestNotification {

    private static final String DEFAULT_TEMPLATE_STRING = "CONTEST_VOTE_QUESTION_DEFAULT";

    private static final String SUPPORTED_PROPOSALS_PLACEHOLDER = "supported-proposals";

    private final List<ProposalWrapper> supportedProposals;
    private ContestVoteQuestionTemplate templateWrapper;

    public ContestVoteQuestionNotification(UserWrapper recipient, ContestWrapper contest,
            List<ProposalWrapper> supportedProposals, String baseUrl) {
        super(contest, recipient, null);
        this.supportedProposals = supportedProposals;
    }

    @Override
    protected ContestVoteQuestionTemplate getTemplateWrapper() {
        if (templateWrapper != null) {
            return templateWrapper;
        }

        String voteQuestionTemplateString = contest.getVoteQuestionTemplateString();
        if (voteQuestionTemplateString.isEmpty()) {
            voteQuestionTemplateString = DEFAULT_TEMPLATE_STRING;
        }
        final IEmailTemplate emailTemplate =
                StaticAdminContext.getEmailTemplateClient().getEmailTemplate(voteQuestionTemplateString);
        templateWrapper = new ContestVoteQuestionTemplate(emailTemplate, contest.getTitle());

        return templateWrapper;
    }

    private class ContestVoteQuestionTemplate extends ContestNotificationTemplate {

        public ContestVoteQuestionTemplate(IEmailTemplate template, String contestName) {
            super(template, "", contestName);
        }

        @Override
        protected Node resolvePlaceholderTag(Element tag) {
            Node node = super.resolvePlaceholderTag(tag);
            if (node != null) {
                return node;
            }

            switch (tag.nodeName()) {
                case SUPPORTED_PROPOSALS_PLACEHOLDER:
                    StringBuilder supportedProposalsLinks = new StringBuilder();
                    supportedProposalsLinks.append("<span>");
                    for (ProposalWrapper proposal : supportedProposals) {
                        UserWrapper member;
                        try {
                            member = StaticUserContext.getUserClient().getMember(proposal.getAuthorUserId());
                        } catch (MemberNotFoundException e) {
                            _log.error("Author {} of proposal {} does not exist",
                                    proposal.getAuthorUserId(), proposal.getId());
                            member = null;
                        }
                        //TODO COLAB-2505: this does not actually generate a link for direct voting
                        supportedProposalsLinks.append(getProposalLinkForDirectVoting(contest, proposal)).append(" by ").append(
                                member != null ? member.getFullName() : "Unknown author").append("<br />");
                    }
                    supportedProposalsLinks.append("</span>");
                    return parseXmlNode(supportedProposalsLinks.toString());
                 
                //missing default case
                default:
                    // add default case
                    break;

            }
            return null;
        }
    }
}
