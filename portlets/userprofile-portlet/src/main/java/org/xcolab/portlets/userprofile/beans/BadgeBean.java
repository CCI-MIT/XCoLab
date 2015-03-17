package org.xcolab.portlets.userprofile.beans;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseRibbonType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.*;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.xcolab.portlets.userprofile.entity.Badge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: patrickhiesel
 * Date: 9/6/13
 * Time: 5:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class BadgeBean implements Serializable{

    private static final long serialVersionUID = 1L;
    private final static Log _log = LogFactoryUtil.getLog(BadgeBean.class);
    private long userID; // USER the Badges belong to
    private List<Badge> badges;


    public BadgeBean(long userID){
        this.userID = userID;
        badges = new ArrayList<Badge>();
        try{
            fetchBadges();
        } catch (Exception e){ e.printStackTrace(); }
    }



    private void fetchBadges() throws SystemException,PortalException{
        // Iterate over all plans
        for(Proposal p : ProposalLocalServiceUtil.getProposals(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
            if (!ProposalLocalServiceUtil.isUserAMember(p.getProposalId(),userID)) continue;
            try {
                ContestPhaseRibbonType ribbon = getRibbonType(p);
                int planRibbon = (ribbon == null) ? -1 : ribbon.getRibbon();
                if (planRibbon > 0) {
                    // Plan won a contest
                    String badgeText = ribbon.getHoverText();
                    long contestId = Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(p.getProposalId()).getContestPK();
                    String planTitle =  ProposalLocalServiceUtil.getAttribute(p.getProposalId(), ProposalAttributeKeys.NAME,0).getStringValue();
                    badges.add(new Badge(planRibbon, badgeText, p.getProposalId(), planTitle, contestId));
                }
            } catch (Exception e) {
                _log.warn("Could nod add badge to user profile view for userId: " + userID + " and proposalId: " + p.getProposalId(), e);
            }
        }
    }

    public List<Badge> getBadges(){
        return badges;
    }

    @Override
    public String toString(){
        return badges.toString();
    }

    private ContestPhaseRibbonType getRibbonType(Proposal p) throws PortalException, SystemException {
        ContestPhaseRibbonType contestPhaseRibbonType = null;
        List<Long> phasesForProposal =  Proposal2PhaseLocalServiceUtil.getContestPhasesForProposal(p.getProposalId());

        long contestPhaseType = 0;
        for (Long phaseId : phasesForProposal) {
            try {
                long typeId = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(p.getProposalId(),
                        phaseId, ProposalContestPhaseAttributeKeys.RIBBON).getNumericValue();

                ContestPhase contestPhase = ContestPhaseLocalServiceUtil.getContestPhase(phaseId);

                // Only consider the latest ribbon per proposal
                if (contestPhase.getContestPhaseType() > contestPhaseType) {
                    contestPhaseRibbonType = ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonType(typeId);
                    contestPhaseType = contestPhase.getContestPhaseType();
                }

            }
            catch (NoSuchProposalContestPhaseAttributeException e) {
                // ignore
            }
        }

        return contestPhaseRibbonType;
    }
}