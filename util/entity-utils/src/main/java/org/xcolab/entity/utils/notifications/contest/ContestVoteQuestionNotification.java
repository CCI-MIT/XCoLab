package org.xcolab.entity.utils.notifications.contest;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.admin.pojo.EmailTemplate;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.entity.utils.notifications.basic.ContestNotification;

import java.util.List;

public class ContestVoteQuestionNotification extends ContestNotification {

    private static final String DEFAULT_TEMPLATE_STRING = "CONTEST_VOTE_QUESTION_DEFAULT";

    private static final String SUPPORTED_PROPOSALS_PLACEHOLDER = "supported-proposals";

    private final List<Proposal> supportedProposals;
    private ContestVoteQuestionTemplate templateWrapper;

    public ContestVoteQuestionNotification(Member recipient, Contest contest,
            List<Proposal> supportedProposals, String baseUrl) {
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
        final EmailTemplate emailTemplate =
                EmailTemplateClientUtil.getContestEmailTemplateByType(voteQuestionTemplateString);
        templateWrapper = new ContestVoteQuestionTemplate(emailTemplate, contest.getContestTitle());

        return templateWrapper;
    }

    private class ContestVoteQuestionTemplate extends ContestNotificationTemplate {

        public ContestVoteQuestionTemplate(EmailTemplate template, String contestName) {
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
                            member = MembersClient.getMember(proposal.getauthorUserid());
                        } catch (MemberNotFoundException e) {
                            _log.error("Author {} of proposal {} does not exist",
                                    proposal.getauthorUserid(), proposal.getId());
                            member = null;
                        }
                        //TODO COLAB-2505: this does not actually generate a link for direct voting
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
