package org.xcolab.service.activities.activityEntry.proposal;



import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalAttributeClient;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.model.tables.pojos.ActivityEntry;
import org.xcolab.service.activities.activityEntry.provider.ActivityEntryContentProvider;
import org.xcolab.util.enums.activity.ActivityEntryType;

public abstract class ProposalBaseActivityEntry implements ActivityEntryContentProvider {

    protected ActivityEntry activityEntry;

    protected static final String DEFAULT_FEED_ENTRY_PATTERN = "%s %s %s";

    //private static final Log _log = LogFactoryUtil.getLog(ProposalBaseActivityEntry.class);

    private Proposal rawProposal;

    private ContestType contestType;

    private Contest contest;

    private String proposalName;

    @Override
    public void setActivityEntry(ActivityEntry activityEntry) {
        this.activityEntry = activityEntry;

        try {
            rawProposal = ProposalsClient.getProposal(this.activityEntry.getClassPrimaryKey());

            contest = ProposalsClient.getCurrentContestForProposal(rawProposal.getProposalId());

            contestType = ContestClient.getContestType(contest.getContestTypeId());

            proposalName = ProposalAttributeClient.getProposalAttribute(rawProposal.getProposalId(), ProposalAttributeKeys.NAME,null).getStringValue();

        } catch (ContestNotFoundException| ProposalNotFoundException e){
            //_log.error("Portal exception  " + e.getMessage());
        }

    }
    @Override
    public Long getPrimaryType() {
        return ActivityEntryType.PROPOSAL.getPrimaryTypeId();
    }


    @Override
    public String getBody() {
        String body = String.format(
                DEFAULT_FEED_ENTRY_PATTERN,
                getUserLink(),
                getBodyTemplate(),
                getProposalLink());
        body = body.replaceAll("<proposal/>", contestType.getProposalName());
        return body;
    }

    @Override
    public String getTitle() {
        String title = getTitleTemplate().replaceAll("<proposal/>", contestType.getProposalName());
        return title;
    }
    @Override
    public String getName() {
        return null;
    }

    abstract protected String getTitleTemplate();

    //abstract protected String getNameTemplate();

    abstract protected String getBodyTemplate();

    protected String getUserLink() {
        try {
            Member member = MembersClient.getMember(activityEntry.getMemberId());
            return member.generateUserURL();
        } catch (MemberNotFoundException e) {
            //_log.info(e.getMessage());
        }
        return "<user removed>";
    }
    private String getProposalLink(){
        String url = "<a href='" + rawProposal.getProposalLinkUrl(contest)+ "'>" + proposalName + "</a>";
        return url;
    }

    public enum ProposalActivitySubType{
        PROPOSAL_ATTRIBUTE_REMOVED(2l),
        PROPOSAL_ATTRIBUTE_UPDATE(1l),
        PROPOSAL_CREATED(0l),
        PROPOSAL_MEMBER_ADDED(6l),
        PROPOSAL_MEMBER_REMOVED(7l),
        PROPOSAL_SUPPORTER_ADDED(8l),
        PROPOSAL_SUPPORTER_REMOVED(9l),
        PROPOSAL_VOTE(3l),
        PROPOSAL_VOTE_RETRACT(4l),
        PROPOSAL_VOTE_SWITCH(5l);

        private final Long secondaryTypeId;
        ProposalActivitySubType(Long type) {
            this.secondaryTypeId = type;
        }

        public Long getSecondaryTypeId(){
            return this.secondaryTypeId;
        }
    }

}
