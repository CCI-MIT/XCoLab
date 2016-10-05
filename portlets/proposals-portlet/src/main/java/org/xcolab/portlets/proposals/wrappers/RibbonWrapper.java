package org.xcolab.portlets.proposals.wrappers;


import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.contest.pojo.ContestPhaseRibbonType;
import org.xcolab.client.proposals.ProposalContestPhaseAttributeClient;
import org.xcolab.client.proposals.pojo.ProposalContestPhaseAttribute;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

/**
 * Wrapper around ContestPhaseRibbonType with utility methods for retrieving information related to the proposal
 */
public class RibbonWrapper {
    private final static Log _log = LogFactoryUtil.getLog(RibbonWrapper.class);

    private ContestPhaseRibbonType contestPhaseRibbonType;
    private final ProposalWrapper proposalWrapper;

    public RibbonWrapper(ProposalWrapper proposalWrapper) {
        this.proposalWrapper = proposalWrapper;
        contestPhaseRibbonType = getContestPhaseRibbonType();
    }

    private ContestPhaseRibbonType getContestPhaseRibbonType() {
        if (contestPhaseRibbonType == null) {
            ProposalContestPhaseAttribute ribbonAttribute;
            final Long proposalId = proposalWrapper.getProposalId();
            if(proposalId == null || proposalId == 0 ){
                return null;
            }
            ContestPhase contestPhase = null;

            contestPhase = ContestClient.getContestPhase(proposalWrapper.getContestPhase().getContestPhasePK());

            if (contestPhase == null) {
                _log.info(String.format("Could not retrieve ribbon type. Wrapper for proposal %d in Contest %d has no contestPhase.",
                        proposalId, proposalWrapper.getContestPK()));
                return null;
            }
                ribbonAttribute = ProposalContestPhaseAttributeClient
                        .getProposalContestPhaseAttribute(
                                proposalId,
                                contestPhase.getContestPhasePK(),
                                ProposalContestPhaseAttributeKeys.RIBBON
                        );
                if (ribbonAttribute != null) {
                    long typeId = ribbonAttribute.getNumericValue();
                    if (typeId >= 0) {
                        contestPhaseRibbonType = ContestClient.getContestPhaseRibbonType(typeId);
                    }
                } else {
                    _log.warn(String.format("Could not retrieve ribbon type for proposal %d", proposalId));
                }

        }
        return contestPhaseRibbonType;
    }

    public int getRibbon() {
        if (contestPhaseRibbonType != null) {
            return contestPhaseRibbonType.getRibbon();
        }
        return 0;
    }
    public long getRibbonId() {
        if (contestPhaseRibbonType != null) {
            return contestPhaseRibbonType.getId_();
        }
        return 0L;
    }

    public String getRibbonText() {
        if (contestPhaseRibbonType != null) {
            return contestPhaseRibbonType.getHoverText();
        }
        return "";
    }

    public int getSortOrder() {
        if (contestPhaseRibbonType != null) {
            return contestPhaseRibbonType.getSortOrder();
        }
        return Integer.MAX_VALUE;
    }

    public String getRibbonTitle() {
        if (contestPhaseRibbonType != null) {
            if (getRibbonText().equalsIgnoreCase("Finalist") || getRibbonText().equalsIgnoreCase("Judges' Special Commendation")){
                return "Finalist";
            } else if (getRibbonText().equalsIgnoreCase("Semi-Finalist")) {
                return "Semi-Finalist";
            } else {
                if(contestPhaseRibbonType.getId_()== 14 ||contestPhaseRibbonType.getId_()== 11 || contestPhaseRibbonType.getId_()== 12) {
                    return "Finalist";
                }else{
                    return "Winner";
                }
            }
        }
        _log.error(String.format("Could not get ribbon title: ContestPhaseRibbonType was null for proposal %d", proposalWrapper.getProposalId()));
        return "";
    }
}
