package org.xcolab.utils.emailnotification.contest;



import com.liferay.portal.service.ServiceContext;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import org.xcolab.client.admin.EmailTemplateClient;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.utils.emailnotification.basic.ContestNotification;

import java.util.List;

public class ContestVoteQuestionNotification extends ContestNotification {

    private static final String DEFAULT_TEMPLATE_STRING = "CONTEST_VOTE_QUESTION_DEFAULT";

    private static final String SUPPORTED_PROPOSALS_PLACEHOLDER = "supported-proposals";

    private final List<Proposal> supportedProposals;
    private ContestVoteQuestionTemplate templateWrapper;

    public ContestVoteQuestionNotification(Member recipient, Contest contest, List<Proposal> supportedProposals,
                                           ServiceContext serviceContext) {
        super(contest, recipient, null, serviceContext);
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
        final ContestEmailTemplate emailTemplate =
                EmailTemplateClient.getContestEmailTemplateByType(voteQuestionTemplateString);
        templateWrapper = new ContestVoteQuestionTemplate(emailTemplate, contest.getContestShortName());

        return templateWrapper;
    }

    private class ContestVoteQuestionTemplate extends ContestNotificationTemplate {

        public ContestVoteQuestionTemplate(ContestEmailTemplate template, String contestName) {
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
                    for (Proposal proposal : supportedProposals) {
                        Member member;
                        try {
                            member = MembersClient.getMember(proposal.getAuthorId());
                        } catch (MemberNotFoundException e) {
                            _log.error("Author " + proposal.getAuthorId() + " of proposal "
                                    + proposal.getProposalId() + " does not exist");
                            member = null;
                        }
                        supportedProposalsLinks.append(getProposalLinkForDirectVoting(contest, proposal)).append(" by ").append(
                                member != null ? member.getFullName() : "Unknown author").append("<br />");
                    }
                    supportedProposalsLinks.append("</span>");
                    return parseXmlNode(supportedProposalsLinks.toString());
            }
            return null;
        }
    }
}
