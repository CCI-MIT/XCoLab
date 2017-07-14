package org.xcolab.view.activityentry.proposal;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.view.activityentry.provider.ActivityEntryContentProvider;
import org.xcolab.view.i18n.ResourceMessageResolver;


public abstract class ProposalBaseActivityEntry implements ActivityEntryContentProvider {

    protected ActivityEntry activityEntry;

    protected static final String DEFAULT_FEED_ENTRY_PATTERN = "%s %s %s";

    private static final Logger _log = LoggerFactory.getLogger(ProposalBaseActivityEntry.class);

    private Proposal rawProposal;

    private ContestType contestType;

    private Contest contest;

    private String proposalName;

    @Autowired
    private ResourceMessageResolver resourceMessageResolver;

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

            contestType = ContestTypeClient.getContestType(contest.getContestTypeId());

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


        String[] params = { getUserLink(),contestType.getProposalName(),
                getProposalLink()};

        return resourceMessageResolver.getLocalizedMessage(getBodyTemplate(),params);

    }

    @Override
    public String getTitle() {
        return getTitleTemplate().replaceAll("<proposal/>", contestType.getProposalName());
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
        return "<a href='" + rawProposal.getProposalLinkUrl(contest)+ "'>" + proposalName + "</a>";
    }

    public enum ProposalActivitySubType{
        PROPOSAL_ATTRIBUTE_REMOVED(2L),
        PROPOSAL_ATTRIBUTE_UPDATE(1L),
        PROPOSAL_CREATED(0L),
        PROPOSAL_MEMBER_ADDED(6L),
        PROPOSAL_MEMBER_REMOVED(7L),
        PROPOSAL_SUPPORTER_ADDED(8L),
        PROPOSAL_SUPPORTER_REMOVED(9L),
        PROPOSAL_VOTE(3L),
        PROPOSAL_VOTE_RETRACT(4L),
        PROPOSAL_VOTE_SWITCH(5L);

        private final Long secondaryTypeId;
        ProposalActivitySubType(Long type) {
            this.secondaryTypeId = type;
        }

        public Long getSecondaryTypeId(){
            return this.secondaryTypeId;
        }
    }

}
