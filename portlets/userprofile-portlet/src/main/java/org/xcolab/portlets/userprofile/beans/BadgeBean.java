package org.xcolab.portlets.userprofile.beans;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseRibbonType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseRibbonTypeLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.portlets.userprofile.entity.Badge;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.exceptions.DatabaseAccessException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BadgeBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static Logger _log = LoggerFactory.getLogger(BadgeBean.class);
    private final long userID;
    private final List<Badge> badges;

    public BadgeBean(long userID) {
        this.userID = userID;
        badges = new ArrayList<>();
        fetchBadges();
    }

    private void fetchBadges() {
        try {
            for (Proposal p : ProposalLocalServiceUtil
                    .getProposals(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
                if (!ProposalLocalServiceUtil.isUserAMember(p.getProposalId(), userID)) {
                    continue;
                }
                try {
                    ContestPhaseRibbonType ribbon = getRibbonType(p);
                    int proposalRibbon = (ribbon == null) ? -1 : ribbon.getRibbon();
                    if (proposalRibbon > 0) {
                        String badgeText = ribbon.getHoverText();
                        Contest contest = Proposal2PhaseLocalServiceUtil
                                .getCurrentContestForProposal(p.getProposalId());
                        String proposalTitle = ProposalAttributeLocalServiceUtil
                                .getAttribute(p.getProposalId(), ProposalAttributeKeys.NAME, 0)
                                .getStringValue();
                        badges.add(new Badge(proposalRibbon, badgeText, p, proposalTitle, contest));
                    }
                } catch (SystemException | PortalException e) {
                    _log.warn("Could not add badge to user profile view for userId: {} and proposalId: {}",
                            p.getProposalId(), e);
                }
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            //can't happen, we know the proposal exists
        }
    }

    private ContestPhaseRibbonType getRibbonType(Proposal p) throws PortalException, SystemException {
        ContestPhaseRibbonType contestPhaseRibbonType = null;
        List<Long> phasesForProposal = Proposal2PhaseLocalServiceUtil.getContestPhasesForProposal(p.getProposalId());

        long contestPhaseType = 0;
        for (Long phaseId : phasesForProposal) {
            try {
                long typeId = ProposalContestPhaseAttributeLocalServiceUtil
                        .getProposalContestPhaseAttribute(p.getProposalId(),
                                phaseId, ProposalContestPhaseAttributeKeys.RIBBON).getNumericValue();

                ContestPhase contestPhase = ContestPhaseLocalServiceUtil.getContestPhase(phaseId);

                // Only consider the latest ribbon per proposal
                if (contestPhase.getContestPhaseType() > contestPhaseType) {
                    contestPhaseRibbonType = ContestPhaseRibbonTypeLocalServiceUtil.getContestPhaseRibbonType(typeId);
                    contestPhaseType = contestPhase.getContestPhaseType();
                }

            } catch (NoSuchProposalContestPhaseAttributeException ignored) {
            }
        }

        return contestPhaseRibbonType;
    }

    public List<Badge> getBadges() {
        return badges;
    }

    @Override
    public String toString() {
        return badges.toString();
    }
}