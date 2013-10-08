package org.xcolab.portlets.proposals.wrappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ext.portlet.JudgingSystemActions;
import org.apache.commons.lang3.StringUtils;

import com.ext.portlet.NoSuchProposalAttributeException;
import com.ext.portlet.NoSuchProposalException;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseRibbonType;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseRibbonTypeLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;

public class ProposalWrapper {
    private final Proposal proposal;
    private final int version;
    private final Contest contest;
    private final ContestPhase contestPhase;
    private final Proposal2Phase proposal2Phase;
    private ContestPhaseRibbonType contestPhaseRibbonType;
    
    private List<ProposalTeamMemberWrapper> members;
    private List<ProposalSectionWrapper> sections;
    private MembershipRequestWrapper membershipRequestWrapper;
    
    public ProposalWrapper(Proposal proposal) {
        this.proposal = proposal;
        this.version = proposal.getCurrentVersion();
        this.contest = null;
        this.contestPhase = null;
        this.proposal2Phase = null;
        this.membershipRequestWrapper = new MembershipRequestWrapper(proposal);
    }


    public ProposalWrapper(Proposal proposal, int version) {
        this.proposal = proposal;
        this.version = version;
        this.contest = null;
        this.contestPhase = null;
        this.proposal2Phase = null;
        this.membershipRequestWrapper = new MembershipRequestWrapper(proposal);
    }
    
    public ProposalWrapper(Proposal proposal, int version, Contest contest, ContestPhase contestPhase, Proposal2Phase proposal2Phase) {
        this.proposal = proposal;
        this.version = version;
        this.contest = contest;
        this.contestPhase = contestPhase;
        this.proposal2Phase = proposal2Phase;
        this.membershipRequestWrapper = new MembershipRequestWrapper(proposal);
    }

    public Class<?> getModelClass() {
        return proposal.getModelClass();
    }

    public long getProposalId() {
        return proposal.getProposalId();
    }

    public Date getCreateDate() {
        return proposal.getCreateDate();
    }

    public void setCreateDate(Date createDate) {
        proposal.setCreateDate(createDate);
    }

    public int getCurrentVersion() {
        return proposal.getCurrentVersion();
    }

    public void setCurrentVersion(int currentVersion) {
        proposal.setCurrentVersion(currentVersion);
    }

    public long getAuthorId() {
        return proposal.getAuthorId();
    }

    public void setAuthorId(long authorId) {
        proposal.setAuthorId(authorId);
    }

    public boolean getVisible() {
        return proposal.getVisible();
    }

    public boolean isVisible() {
        return proposal.isVisible();
    }

    public long getDiscussionId() {
        return proposal.getDiscussionId();
    }

    public void setDiscussionId(long discussionId) {
        proposal.setDiscussionId(discussionId);
    }

    public long getJudgeDiscussionId() {
        return proposal.getJudgeDiscussionId();
    }

    public void setJudgeDiscussionId(long judgeDiscussionId) {
        proposal.setJudgeDiscussionId(judgeDiscussionId);
    }

    public long getFellowDiscussionId() {
        return proposal.getFellowDiscussionId();
    }

    public void setFellowDiscussionId(long fellowDiscussionId) {
        proposal.setFellowDiscussionId(fellowDiscussionId);
    }

    public long getAdvisorDiscussionId() {
        return proposal.getAdvisorDiscussionId();
    }

    public void setAdvisorDiscussionId(long advisorDiscussionId) {
        proposal.setAdvisorDiscussionId(advisorDiscussionId);
    }

    public long getGroupId() {
        return proposal.getGroupId();
    }

    public void setGroupId(long groupId) {
        proposal.setGroupId(groupId);
    }

    public void setCachedModel(boolean cachedModel) {
        proposal.setCachedModel(cachedModel);
    }

    public ExpandoBridge getExpandoBridge() {
        return proposal.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
        proposal.setExpandoBridgeAttributes(serviceContext);
    }
    
    public String getPitch() throws PortalException, SystemException {
        return getAttributeValueString(ProposalAttributeKeys.PITCH, "");
    }


    public String getName() throws PortalException, SystemException {
        return getAttributeValueString(ProposalAttributeKeys.NAME, "");
    } 
    
    public String getDescription() throws PortalException, SystemException {
        return getAttributeValueString(ProposalAttributeKeys.DESCRIPTION, "");
    } 
    
    public boolean isFeatured() throws PortalException, SystemException {
        return getRibbon() > 0;
    }
    
    public int getRibbon() throws PortalException, SystemException {
        getRibbonType();
        if (contestPhaseRibbonType != null) {
            return contestPhaseRibbonType.getRibbon();
        }
        return 0;
    }
    
    public String getRibbonText() throws PortalException, SystemException {
        getRibbonType();
        if (contestPhaseRibbonType != null) {
            return contestPhaseRibbonType.getHoverText();
        }
        return null;
    }

    public Long getJudgeRating() throws SystemException, PortalException {
        return getAttributeValueLong(ProposalAttributeKeys.JUDGE_RATING, 0);
    }

    public Long getFellowRating() throws SystemException, PortalException {
        return getAttributeValueLong(ProposalAttributeKeys.FELLOW_RATING, 0);
    }

    public JudgingSystemActions.JudgeAction getJudgeAction() throws SystemException, PortalException {
        Long action = getAttributeValueLong(ProposalAttributeKeys.JUDGE_ACTION, 0);
        return JudgingSystemActions.JudgeAction.fromInt(action.intValue());
    }

    public JudgingSystemActions.FellowAction getFellowAction() throws SystemException, PortalException {
        Long action = getAttributeValueLong(ProposalAttributeKeys.FELLOW_ACTION, 0);
        return JudgingSystemActions.FellowAction.fromInt(action.intValue());
    }

    public String getJudgeComment() throws SystemException, PortalException {
        return getAttributeValueString(ProposalAttributeKeys.JUDGE_COMMENT, "");
    }

    public String getFellowComment() throws SystemException, PortalException {
        return getAttributeValueString(ProposalAttributeKeys.FELLOW_COMMENT, "");
    }


    public String getTeam() throws PortalException, SystemException {
        return getAttributeValueString(ProposalAttributeKeys.TEAM, "");
    }
    
    public User getAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(proposal.getAuthorId());
    }
    
    public long getSupportersCount() throws PortalException, SystemException {
        return ProposalLocalServiceUtil.getSupportersCount(proposal.getProposalId());
    }

    public long getCommentsCount() throws PortalException, SystemException {
        if (proposal.getProposalId() > 0) {
            return ProposalLocalServiceUtil.getCommentsCount(proposal.getProposalId());
        }
        return 0;
    }
    
    public Date getLastModifiedDate() {
        return proposal.getUpdatedDate();
    }
    
    public boolean isOpen() throws PortalException, SystemException {
        if (proposal.getProposalId() > 0) {
            return ProposalLocalServiceUtil.isOpen(proposal.getProposalId());
        }
        return false;
    }
    
    public long getVotesCount() throws SystemException {
        if (proposal.getProposalId() > 0) {
            return ProposalLocalServiceUtil.getVotesCount(proposal.getProposalId(), contestPhase.getContestPhasePK());
        }
        return 0;
    }
    
    public long getImageId() throws PortalException, SystemException {
        return getAttributeValueLong(ProposalAttributeKeys.IMAGE_ID, 0L, 0);
    }
     
    
    public List<ProposalSectionWrapper> getSections() throws PortalException, SystemException {
        if (sections == null) {
            sections = new ArrayList<ProposalSectionWrapper>();
            if (contest != null) { 
                PlanTemplate planTemplate = ContestLocalServiceUtil.getPlanTemplate(contest);
                if (planTemplate != null) {
                    for (PlanSectionDefinition psd: PlanTemplateLocalServiceUtil.getSections(planTemplate)) {
                        sections.add(new ProposalSectionWrapper(psd, proposal, version));
                    }
                }
            }
        }
        return sections;
    }
    
    public List<ProposalTeamMemberWrapper> getMembers() throws PortalException, SystemException {
        if (members == null) {
            members = new ArrayList<ProposalTeamMemberWrapper>();
            for (User user: ProposalLocalServiceUtil.getMembers(proposal.getProposalId())) {
                members.add(new ProposalTeamMemberWrapper(proposal, user));
            }
        }
        return members;
    }
    
    public List<User> getSupporters() throws PortalException, SystemException {
        return ProposalLocalServiceUtil.getSupporters(proposal.getProposalId());
    }
    
    public boolean hasAttribute(String name) throws PortalException, SystemException {
        return getAttributeOrNull(name, 0) != null;
    }

    public String attributeString(String name) throws PortalException, SystemException {
        return getAttributeValueString(name, "");
    }
    
    private String getAttributeValueString(String attributeName, String defaultVal) throws PortalException, SystemException {
        return getAttributeValueString(attributeName, 0, defaultVal);
    }

    
    private String getAttributeValueString(String attributeName, long additionalId, String defaultVal) throws PortalException, SystemException {
        ProposalAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getStringValue();
        
    }

    private long getAttributeValueLong(String attributeName, long defaultVal) throws PortalException, SystemException {
        return getAttributeValueLong(attributeName, 0, defaultVal);
    }

    private long getAttributeValueLong(String attributeName, long additionalId, long defaultVal) throws PortalException, SystemException {
        ProposalAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getNumericValue();
        
    }

    private double getAttributeValueReal(String attributeName, long additionalId, double defaultVal) throws PortalException, SystemException {
        ProposalAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getRealValue();
    }
    
    private ProposalAttribute getAttributeOrNull(String attributeName, long additionalId) throws PortalException, SystemException {
        try {
            return ProposalLocalServiceUtil.getAttribute(proposal.getProposalId(), attributeName, additionalId);
        }
        catch (NoSuchProposalAttributeException e) {
            return null;
        }
        catch (NoSuchProposalException e) {
            return null;
        }
    }


    
    public String getAuthorName() throws PortalException, SystemException {
        String authorName = getTeam();
        if (StringUtils.isBlank(authorName)) {
            authorName = getAuthor().getScreenName();
        }
        return authorName;
        
    }
    
    
    private ContestPhaseRibbonType getRibbonType() throws PortalException, SystemException {
        if (contestPhaseRibbonType == null) {
            if (proposal2Phase != null && proposal2Phase.getRibbonTypeId() > 0) {
                contestPhaseRibbonType = ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonType(proposal2Phase.getRibbonTypeId());
            }
        }
        return contestPhaseRibbonType;
    }
    
    public MembershipRequestWrapper getMembershipRequests(){
        return this.membershipRequestWrapper;
    }
    

}
