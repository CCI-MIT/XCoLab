package org.xcolab.client.activities.activityEntry.proposal;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.community.CommunityUtil;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.activities.contentProviders.ActivityEntryContentProvider;
import org.xcolab.client.activities.pojo.ActivityEntry;
import org.xcolab.helpers.ProposalAttributeHelper;
import org.xcolab.util.enums.activity.ActivityEntryType;

public abstract class ProposalBaseActivityEntry implements ActivityEntryContentProvider {

    protected ActivityEntry activityEntry;

    protected static final String DEFAULT_FEED_ENTRY_PATTERN = "%s %s %s";

    private static final Log _log = LogFactoryUtil.getLog(ProposalBaseActivityEntry.class);

    private Proposal rawProposal;

    private ContestType contestType;

    private Contest contest;

    private String proposalName;
    @Override
    public void setActivityEntry(ActivityEntry activityEntry) {
        this.activityEntry = activityEntry;

        try {
            rawProposal = ProposalLocalServiceUtil.getProposal(this.activityEntry.getClassPrimaryKey());
            contestType = ContestTypeLocalServiceUtil.getContestTypeFromProposalId(rawProposal.getProposalId());

            contest = Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(rawProposal.getProposalId());

            ProposalAttributeHelper proposalAttributeHelper = new ProposalAttributeHelper(rawProposal);

            proposalName = proposalAttributeHelper.getAttributeValueString(ProposalAttributeKeys.NAME, "");




        } catch (PortalException e){
            _log.error("Portal exception  " + e.getMessage());
        } catch (SystemException e){
            _log.error("System exception  " + e.getMessage());
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
            return CommunityUtil.generateUserURL(activityEntry.getMemberId());
        } catch (PortalException | SystemException e) {
            _log.info(e.getMessage());
        }
        return "<user removed>";
    }
    private String getProposalLink(){
        String url = "<a href='" + ProposalLocalServiceUtil.getProposalLinkUrl(contest, rawProposal)+ "'>" + proposalName + "</a>";
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
