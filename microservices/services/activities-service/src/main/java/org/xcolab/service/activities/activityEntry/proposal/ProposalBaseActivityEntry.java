package org.xcolab.service.activities.activityEntry.proposal;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.model.tables.pojos.ActivityEntry;
import org.xcolab.service.activities.activityEntry.provider.ActivityEntryContentProvider;
import org.xcolab.util.enums.activity.ActivityEntryType;

public abstract class ProposalBaseActivityEntry implements ActivityEntryContentProvider {

    protected ActivityEntry activityEntry;

    protected static final String DEFAULT_FEED_ENTRY_PATTERN = "%s %s %s";

    private static final Logger _log = LoggerFactory.getLogger(ProposalBaseActivityEntry.class);

    private Proposal rawProposal;

    private ContestType contestType;

    private Contest contest;

    private String proposalName;

    @Override
    public void setActivityEntry(ActivityEntry activityEntry) {
        this.activityEntry = activityEntry;

        try {
            if(this.getSecondaryType().equals(ProposalActivitySubType.PROPOSAL_CREATED.getSecondaryTypeId())){
                try {
                    Long proposalId = new Long(this.activityEntry.getExtraData());
                    rawProposal =
                            ProposalClientUtil.getProposal(proposalId);
                }catch (NumberFormatException e){
                    //legacy support
                    rawProposal =
                            ProposalClientUtil.getProposal(this.activityEntry.getClassPrimaryKey());
                }
            }else {
                rawProposal =
                        ProposalClientUtil.getProposal(this.activityEntry.getClassPrimaryKey());
            }

            contest = ProposalClientUtil.getCurrentContestForProposal(rawProposal.getProposalId());

            contestType = ContestClientUtil.getContestType(contest.getContestTypeId());

            proposalName = ProposalAttributeClientUtil
                    .getProposalAttribute(rawProposal.getProposalId(), ProposalAttributeKeys.NAME,null).getStringValue();

        } catch (ContestNotFoundException| ProposalNotFoundException e){
            _log.error("Error: {}", e.getMessage());
        }

    }
    @Override
    public Long getPrimaryType() {
        if(this.getSecondaryType().equals(ProposalActivitySubType.PROPOSAL_CREATED.getSecondaryTypeId())){
            return ActivityEntryType.CONTEST.getPrimaryTypeId();
        }else {
            return ActivityEntryType.PROPOSAL.getPrimaryTypeId();
        }
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
