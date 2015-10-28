package org.xcolab.wrappers;


import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestTeamMember;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestTeamMemberLocalServiceUtil;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by johannes on 10/27/15.
 *
 * A wrapper class for contests to be shared across portlets.
 * More specific wrappers should inherit from this one.
 */
public class BaseContestWrapper {

    protected final static Log _log = LogFactoryUtil.getLog(BaseContestWrapper.class);
    protected static final String WHERE = "where";
    protected static final String WHAT = "what";
    protected static final String WHO = "who";
    protected static final String HOW = "how";

    protected final static Map<Long, FocusArea> faCache = new HashMap<>();
    protected Map<String, List<OntologyTerm>> ontologySpaceCache = new HashMap<>();
    protected List<BaseContestPhaseWrapper> phases;

    private ContestType contestType;

    protected List<BaseContestTeamRoleWrapper> contestTeamMembersByRole;

    protected final Contest contest;

    public BaseContestWrapper(Contest contest) {
        this.contest = contest;
    }

    public void persist() throws SystemException {
        contest.persist();
    }

    public long getContestPK() {
        return contest.getContestPK();
    }

    public void setContestPK(long ContestPK) {
        contest.setContestPK(ContestPK);
    }

    public String getContestName() {
        return contest.getContestName();
    }

    public void setContestName(String ContestName) {
        contest.setContestName(ContestName);
    }

    public String getContestShortName() {
        return contest.getContestShortName();
    }

    public void setContestShortName(String ContestShortName) {
        contest.setContestShortName(ContestShortName);
    }

    public Date getCreated() {
        return contest.getCreated();
    }

    public void setCreated(Date created) {
        contest.setCreated(created);
    }

    public Date getUpdated() {
        return contest.getUpdated();
    }

    public void setUpdated(Date updated) {
        contest.setUpdated(updated);
    }

    public long getAuthorId() {
        return contest.getAuthorId();
    }

    public void setAuthorId(long authorId) {
        contest.setAuthorId(authorId);
    }

    public boolean getContestActive() {
        return contest.getContestActive();
    }

    public boolean isContestActive() {
        return contest.isContestActive();
    }

    public void setContestActive(boolean contestActive) {
        contest.setContestActive(contestActive);
    }

    public long getPlanTemplateId() {
        return contest.getPlanTemplateId();
    }

    public void setPlanTemplateId(long planTemplateId) {
        contest.setPlanTemplateId(planTemplateId);
    }

    public long getFocusAreaId() {
        return contest.getFocusAreaId();
    }

    public void setFocusAreaId(long focusAreaId) {
        contest.setFocusAreaId(focusAreaId);
    }

    public long getContestLogoId() {
        return contest.getContestLogoId();
    }

    public void setContestLogoId(long contestLogoId) {
        contest.setContestLogoId(contestLogoId);
    }

    public boolean getFeatured() {
        return contest.getFeatured();
    }

    public boolean isFeatured() {
        return contest.isFeatured();
    }

    public void setFeatured(boolean featured) {
        contest.setFeatured(featured);
    }

    public boolean getPlansOpenByDefault() {
        return contest.getPlansOpenByDefault();
    }

    public boolean isPlansOpenByDefault() {
        return contest.isPlansOpenByDefault();
    }

    public void setPlansOpenByDefault(boolean plansOpenByDefault) {
        contest.setPlansOpenByDefault(plansOpenByDefault);
    }

    public long getSponsorLogoId() {
        return contest.getSponsorLogoId();
    }

    public void setSponsorLogoId(long sponsorLogoId) {
        contest.setSponsorLogoId(sponsorLogoId);
    }

    public String getSponsorText() {
        return contest.getSponsorText();
    }

    public void setSponsorText(String sponsorText) {
        contest.setSponsorText(sponsorText);
    }

    public String getSponsorLink() {
        return contest.getSponsorLink();
    }

    public void setSponsorLink(String sponsorLink) {
        contest.setSponsorLink(sponsorLink);
    }

    public boolean getSponsorLinkAvailable() {
        return !contest.getSponsorLink().equals("");
    }

    public int getFlag() {
        return contest.getFlag();
    }

    public void setFlag(int flag) {
        contest.setFlag(flag);
    }

    public String getFlagText() {
        return contest.getFlagText();
    }

    public void setFlagText(String flagText) {
        contest.setFlagText(flagText);
    }

    public String getFlagTooltip() {
        return contest.getFlagTooltip();
    }

    public void setFlagTooltip(String flagTooltip) {
        contest.setFlagTooltip(flagTooltip);
    }

    public long getGroupId() {
        return contest.getGroupId();
    }

    public void setGroupId(long groupId) {
        contest.setGroupId(groupId);
    }

    public long getDiscussionGroupId() {
        return contest.getDiscussionGroupId();
    }

    public void setDiscussionGroupId(long discussionGroupId) {
        contest.setDiscussionGroupId(discussionGroupId);
    }

    public int getWeight() {
        return contest.getWeight();
    }

    public void setWeight(int weight) {
        contest.setWeight(weight);
    }

    public long getContestTier() {
        return contest.getContestTier();
    }

    public void setContestTier(long contestTier) {
        contest.setContestTier(contestTier);
    }

    public boolean getContestPrivate() {
        return contest.getContestPrivate();
    }

    public void setContestPrivate(boolean contestPrivate) {
        contest.setContestPrivate(contestPrivate);
    }

    public String getResourcesUrl() {
        return contest.getResourcesUrl();
    }

    public void setResourcesUrl(String resourcesUrl) {
        contest.setResourcesUrl(resourcesUrl);
    }

    public boolean getShow_in_list_view(){ return contest.getShow_in_list_view();}

    public void setShow_in_list_view(boolean show_in_list_view){ contest.setShow_in_list_view(show_in_list_view);}

    public boolean getShow_in_tile_view(){ return contest.getShow_in_tile_view();}

    public void setShow_in_tile_view(boolean show_in_tile_view){ contest.setShow_in_tile_view(show_in_tile_view);}

    public boolean getShow_in_outline_view(){ return contest.getShow_in_outline_view();}

    public void setShow_in_outline_view(boolean show_in_tile_view){ contest.setShow_in_outline_view(show_in_tile_view);}

    public boolean getHideRibbons() {
        return contest.getHideRibbons();
    }

    public void setHideRibbons(boolean hideRibbons) throws SystemException {
        contest.setHideRibbons(hideRibbons);
    }

    public long getProposalsCount() throws PortalException, SystemException {
        return ContestLocalServiceUtil.getProposalsCount(contest);
    }

    public long getTotalProposalsCount() throws PortalException, SystemException {
        Set<Proposal> proposalList = new HashSet<>();
        List<ContestPhase> contestPhases = ContestPhaseLocalServiceUtil.getPhasesForContest(contest);
        for(ContestPhase contestPhase : contestPhases){
            List<Proposal> proposals = ProposalLocalServiceUtil.getActiveProposalsInContestPhase(contestPhase.getContestPhasePK());
            proposalList.addAll(proposals);
        }
        return proposalList.size();
    }

    public long getCommentsCount() throws PortalException, SystemException {
        return ContestLocalServiceUtil.getCommentsCount(contest);
    }

    public List<OntologyTerm> getWho() throws PortalException, SystemException {
        return getTermFromSpace(WHO);
    }

    public List<OntologyTerm> getWhat() throws PortalException, SystemException {
        return getTermFromSpace(WHAT);
    }

    public List<OntologyTerm> getWhere() throws PortalException,
            SystemException {
        return getTermFromSpace(WHERE);
    }

    public List<OntologyTerm> getHow() throws PortalException,
            SystemException {
        return getTermFromSpace(HOW);
    }

    protected List<OntologyTerm> getTermFromSpace(String space)
            throws PortalException, SystemException {

        if (!ontologySpaceCache.containsKey(space) && (getFocusAreaId() > 0)) {
            if (!faCache.containsKey(contest.getFocusAreaId())) {
                FocusArea fa = FocusAreaLocalServiceUtil.getFocusArea(contest
                        .getFocusAreaId());
                if (fa == null) {
                    ontologySpaceCache.put(space, null);
                    return null;
                }
                faCache.put(fa.getId(), fa);
            }
            List<OntologyTerm> terms = new ArrayList<>();
            for (OntologyTerm t : FocusAreaLocalServiceUtil.getTerms(faCache.get(contest.getFocusAreaId()))) {
                if (OntologyTermLocalServiceUtil.getSpace(t).getName()
                        .equalsIgnoreCase(space)) {
                    terms.add(t);
                }
            }
            ontologySpaceCache.put(space, terms.isEmpty() ? null : terms);
        }
        return ontologySpaceCache.get(space);
    }

    public List<BaseContestPhaseWrapper> getPhases() throws SystemException, PortalException {
        if (phases == null) {
            phases = new ArrayList<>();
            for (ContestPhase phase : ContestLocalServiceUtil.getAllPhases(contest)) {
                phases.add(new BaseContestPhaseWrapper(phase));
            }
        }
        return phases;
    }

    public List<BaseContestTeamRoleWrapper> getContestTeamMembersByRole() throws PortalException, SystemException {
        if (contestTeamMembersByRole == null) {
            contestTeamMembersByRole = new ArrayList<>();
            Map<String, List<User>> teamRoleUsersMap = new TreeMap<>();
            for (ContestTeamMember ctm : ContestLocalServiceUtil.getTeamMembers(contest)) {
                List<User> roleUsers = teamRoleUsersMap.get(ctm.getRole());
                if (roleUsers == null) {
                    roleUsers = new ArrayList<>();
                    teamRoleUsersMap.put(ctm.getRole(), roleUsers);
                }
                try {
                    roleUsers.add(ContestTeamMemberLocalServiceUtil.getUser(ctm));
                } catch(SystemException e){
                    _log.warn("Could not add user role: " + e);
                }
            }

            for (String role : teamRoleUsersMap.keySet()) {
                contestTeamMembersByRole.add(new BaseContestTeamRoleWrapper(role, teamRoleUsersMap.get(role)));
            }
        }
        return contestTeamMembersByRole;
    }

    public boolean getHasUserRoleInContest(User userInQuestion, String role) throws SystemException, PortalException {

        for (BaseContestTeamRoleWrapper c : getContestTeamMembersByRole()) {
            if (c.getRoleName().equalsIgnoreCase(role)) {
                for(User user : c.getUsers()){
                    if(user.equals(userInQuestion)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public ContestType getContestType() throws SystemException {
        if (contestType == null) {
            contestType = ContestTypeLocalServiceUtil.fetchContestType(contest.getContestTypeId());
        }
        return contestType;
    }

    public Contest getWrapped() {
        return contest;
    }

    public String getContestUrl() {
        return ContestLocalServiceUtil.getContestLinkUrl(contest);
    }
}
