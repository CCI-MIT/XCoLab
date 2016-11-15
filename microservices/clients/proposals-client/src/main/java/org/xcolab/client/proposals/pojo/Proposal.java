package org.xcolab.client.proposals.pojo;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.ProposalAttributeClient;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.http.client.RestService;

import java.sql.Timestamp;

public class Proposal extends AbstractProposal {

    private final ContestClient contestClient;
    private final ProposalClient proposalClient;
    private final ProposalAttributeClient proposalAttributeClient;
    private final ProposalPhaseClient proposalPhaseClient;

    public Proposal() {
        contestClient = ContestClientUtil.getClient();
        proposalClient = ProposalClientUtil.getClient();
        proposalAttributeClient = ProposalAttributeClientUtil.getClient();
        proposalPhaseClient = ProposalPhaseClientUtil.getClient();
    }

    public Proposal(Proposal value) {
        super(value);
        contestClient = ContestClientUtil.getClient();
        proposalClient = ProposalClientUtil.getClient();
        proposalAttributeClient = ProposalAttributeClientUtil.getClient();
        proposalPhaseClient = ProposalPhaseClientUtil.getClient();
    }

    public Proposal(Long proposalid, Timestamp createdate, Timestamp updateddate,
            Integer currentversion, Long authorid, Boolean visible, Long discussionid,
            Long resultsdiscussionid, Long judgediscussionid, Long fellowdiscussionid,
            Long advisordiscussionid, Long groupid) {
        super(proposalid, createdate, updateddate, currentversion, authorid, visible, discussionid,
                resultsdiscussionid, judgediscussionid, fellowdiscussionid,
                advisordiscussionid, groupid);
        contestClient = ContestClientUtil.getClient();
        proposalClient = ProposalClientUtil.getClient();
        proposalAttributeClient = ProposalAttributeClientUtil.getClient();
        proposalPhaseClient = ProposalPhaseClientUtil.getClient();
    }

    public Proposal(AbstractProposal abstractProposal, RestService restService) {
        super(abstractProposal);
        //TODO: we should not be using that here
        contestClient = ContestClientUtil.getClient();
        proposalClient = ProposalClient.fromService(restService);
        proposalAttributeClient = ProposalAttributeClient.fromService(restService);
        proposalPhaseClient = ProposalPhaseClient.fromService(restService);
    }

    public boolean isOpen() {
        ProposalAttribute attribute = proposalAttributeClient
                .getProposalAttribute(this.getProposalId(), ProposalAttributeKeys.OPEN, 0L);
        return attribute != null && attribute.getNumericValue() > 0;
    }

    public String getProposalLinkUrl(Contest contest) {
        return getProposalLinkUrl(contest, 0L);
    }

    public String getProposalLinkUrl(Contest contest, long contestPhaseId) {
        String link = "/";
        Long proposalId = this.getProposalId();

        final ContestType contestType =
                contestClient.getContestType(contest.getContestTypeId());
        link += contestType.getFriendlyUrlStringContests();
        String friendlyUrlStringProposal = contestType.getFriendlyUrlStringProposal();

        if (contestPhaseId > 0) {

            long activePhaseId =
                    contestClient.getActivePhase(contest.getContestPK()).getContestPhasePK();
            if (activePhaseId == contestPhaseId) {
                link += "/%d/%s/c/" + friendlyUrlStringProposal + "/%d";
                return String.format(link, contest.getContestYear(), contest.getContestUrlName(),
                        proposalId);
            }

            link += "/%d/%s/phase/%d/" + friendlyUrlStringProposal + "/%d";
            return String.format(link, contest.getContestYear(), contest.getContestUrlName(),
                    contestPhaseId, proposalId);
        }

        link += "/%d/%s/c/" + friendlyUrlStringProposal + "/%d";
        return String
                .format(link, contest.getContestYear(), contest.getContestUrlName(), proposalId);
    }

    public boolean isVisibleInContest(long contestId) {
        try {
            if (this.getProposalId() == 0) {
                return true;
            }
            final Contest currentContest =
                    proposalClient.getCurrentContestForProposal(this.getProposalId());
            return !isDeleted() && currentContest.getContestPK() == contestId;
        } catch (ContestNotFoundException ignored) {
            return false;
        }

    }

    public boolean isDeleted() {
        if (this.getProposalId() == 0) {
            return false;
        }
        final ContestPhase contestPhase = contestClient.getContestPhase(
                proposalClient.getLatestContestPhaseIdInProposal(this.getProposalId()));
        long visibleAttributeValue = 1;
        if (contestPhase != null) {
            ProposalContestPhaseAttribute pcpa = proposalPhaseClient
                    .getProposalContestPhaseAttribute(this.getProposalId(),
                            contestPhase.getContestPhasePK(),
                            ProposalContestPhaseAttributeKeys.VISIBLE);
            if (pcpa != null) {

                visibleAttributeValue = pcpa.getNumericValue();
            }
        }
        return !this.getVisible() || visibleAttributeValue == 0;

    }
}
