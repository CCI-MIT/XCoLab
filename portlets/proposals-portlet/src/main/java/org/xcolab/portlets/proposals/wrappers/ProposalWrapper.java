package org.xcolab.portlets.proposals.wrappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ext.portlet.NoSuchProposalAttributeException;
import com.ext.portlet.NoSuchProposalException;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.service.ContestLocalServiceUtil;
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
    
    private List<ProposalTeamMemberWrapper> members;
    private List<ProposalSectionWrapper> sections;
    
    public ProposalWrapper(Proposal proposal) {
        this.proposal = proposal;
        this.version = proposal.getCurrentVersion();
        this.contest = null;
        this.contestPhase = null;
    }


    public ProposalWrapper(Proposal proposal, int version) {
        this.proposal = proposal;
        this.version = version;
        this.contest = null;
        this.contestPhase = null;
    }
    
    public ProposalWrapper(Proposal proposal, int version, Contest contest, ContestPhase contestPhase) {
        this.proposal = proposal;
        this.version = version;
        this.contest = contest;
        this.contestPhase = contestPhase;
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
        return getAttributeValueString("PITCH", "");
    }


    public String getName() throws PortalException, SystemException {
        return getAttributeValueString("NAME", "");
    } 
    
    public boolean isFeatured() {
        // TODO
        return false;
    }
    
    public int getRibbon() {
        // TODO
        return 0;
    }
    
    public String getTeam() {
        // TODO
        return null;
    }
    
    public User getAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(proposal.getAuthorId());
    }
    
    public long getSupportersCount() throws PortalException, SystemException {
        return ProposalLocalServiceUtil.getSupportersCount(proposal.getProposalId());
    }

    public long getCommentsCount() throws PortalException, SystemException {
        return ProposalLocalServiceUtil.getCommentsCount(proposal.getProposalId());
    }
    
    public Date getLastModifiedDate() {
        return proposal.getUpdatedDate();
    }
    
    public boolean isOpen() throws PortalException, SystemException {
        return ProposalLocalServiceUtil.isOpen(proposal.getProposalId());
    }
    
    public long getVotesCount() throws SystemException {
        return ProposalLocalServiceUtil.getVotesCount(proposal.getProposalId(), contestPhase.getContestPhasePK());
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
    
    
    private String getAttributeValueString(String attributeName, String defaultVal) throws PortalException, SystemException {
        return getAttributeValueString(attributeName, 0, defaultVal);
    }

    
    private String getAttributeValueString(String attributeName, long additionalId, String defaultVal) throws PortalException, SystemException {
        ProposalAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getStringValue();
        
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
    
    
    
    

}
