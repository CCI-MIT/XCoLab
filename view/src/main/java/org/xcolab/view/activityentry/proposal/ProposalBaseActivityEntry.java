package org.xcolab.view.activityentry.proposal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import org.xcolab.client.activities.enums.ProposalActivityType;
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
import org.xcolab.view.activityentry.ActivityInitializationException;
import org.xcolab.view.activityentry.provider.ActivityEntryContentProvider;
import org.xcolab.view.i18n.ResourceMessageResolver;

public abstract class ProposalBaseActivityEntry implements ActivityEntryContentProvider {

    private static final Logger _log = LoggerFactory.getLogger(ProposalBaseActivityEntry.class);

    protected ActivityEntry activityEntry;

    private Proposal rawProposal;

    private ContestType contestType;

    private Contest contest;

    private String proposalName;

    private final ResourceMessageResolver resourceMessageResolver;

    public ProposalBaseActivityEntry(ResourceMessageResolver resourceMessageResolver) {
        this.resourceMessageResolver = resourceMessageResolver;
    }

    @Override
    public void setActivityEntry(ActivityEntry activityEntry) throws ActivityInitializationException {
        this.activityEntry = activityEntry;

        try {
            if (ProposalActivityType.CREATED.equals(getActivityType())) {
                try {
                    Long proposalId = new Long(this.activityEntry.getExtraData());
                    rawProposal = ProposalClientUtil.getProposal(proposalId);
                } catch (NumberFormatException e) {
                    //legacy support
                    rawProposal =
                            ProposalClientUtil.getProposal(this.activityEntry.getClassPrimaryKey());
                }
            } else {
                rawProposal =
                        ProposalClientUtil.getProposal(this.activityEntry.getClassPrimaryKey());
            }

            contest = ProposalClientUtil.getCurrentContestForProposal(rawProposal.getProposalId());

            contestType = ContestTypeClient.getContestType(contest.getContestTypeId(),
                    LocaleContextHolder.getLocale().getLanguage());

            proposalName = ProposalAttributeClientUtil
                    .getProposalAttribute(rawProposal.getProposalId(), ProposalAttributeKeys.NAME,
                            null).getStringValue();

        } catch (ContestNotFoundException | ProposalNotFoundException e) {
            throw new ActivityInitializationException(activityEntry.getActivityEntryId(), e);
        }
    }


    @Override
    public String getBody() {
        String[] params = {getUserLink(), contestType.getProposalName(), getProposalLink()};
        return resourceMessageResolver.getLocalizedMessage(getBodyTemplate(), params);
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

    abstract protected String getBodyTemplate();

    private String getUserLink() {
        try {
            Member member = MembersClient.getMember(activityEntry.getMemberId());
            return member.generateUserURL();
        } catch (MemberNotFoundException e) {
            //_log.info(e.getMessage());
        }
        return "<user removed>";
    }

    private String getProposalLink() {
        return "<a href='" + rawProposal.getProposalLinkUrl(contest) + "'>" + proposalName + "</a>";
    }
}
