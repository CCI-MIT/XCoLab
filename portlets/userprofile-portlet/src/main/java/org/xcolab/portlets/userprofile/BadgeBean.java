package org.xcolab.portlets.userprofile;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseRibbonType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
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

    private long userID; // USER the Badges belong to
    private java.util.List<Badge> badges;


    public BadgeBean(long userID){
        this.userID = userID;
        badges = new ArrayList<Badge>();
        try{
            fetchBadges();
        } catch (Exception e){ e.printStackTrace(); }
    }



    private void fetchBadges() throws SystemException,PortalException{
        List<Proposal> proposals;

        // Iterate over all plans
        for(Proposal p : ProposalLocalServiceUtil.getProposals(0,Integer.MAX_VALUE)) {
            if (!ProposalLocalServiceUtil.isUserAMember(p.getProposalId(),userID)) continue;
            ContestPhaseRibbonType ribbon = getRibbonType(p);
            int planRibbon = (ribbon == null) ? -1 : ribbon.getRibbon();

            if (planRibbon > 0) {
                // Plan won a contest
                try {
                    String badgeText = ribbon.getHoverText();
                    long contestId = Proposal2PhaseLocalServiceUtil.getCurrentContestForProposal(p.getProposalId()).getContestPK();
                    String planTitle =  ProposalLocalServiceUtil.getAttribute(p.getProposalId(), ProposalAttributeKeys.NAME,0).getStringValue();
                    badges.add(new Badge(planRibbon, badgeText, p.getProposalId(), planTitle, contestId));
                } catch (Exception e) { }
            }
        }
    }

    public java.util.List<Badge> getBadges(){
        return badges;
    }

    @Override
    public String toString(){
        return badges.toString();
    }

    private ContestPhaseRibbonType getRibbonType(Proposal p) throws PortalException, SystemException {
        ContestPhaseRibbonType contestPhaseRibbonType = null;
        List<Long> phasesForProposal =  Proposal2PhaseLocalServiceUtil.getContestPhasesForProposal(p.getProposalId());
        ContestPhase contestPhase = ContestPhaseLocalServiceUtil.getContestPhase(phasesForProposal.get(phasesForProposal.size() - 1));
        try {
            long typeId = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(p.getProposalId(),
                    contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.RIBBON).getNumericValue();
            contestPhaseRibbonType = ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonType(typeId);
        }
        catch (NoSuchProposalContestPhaseAttributeException e) {
            // ignore
        }

        return contestPhaseRibbonType;
    }
}