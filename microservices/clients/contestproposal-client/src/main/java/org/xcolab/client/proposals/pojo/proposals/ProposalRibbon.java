package org.xcolab.client.proposals.pojo.proposals;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseRibbonType;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.ProposalContestPhaseAttribute;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.http.client.RestService;

public class ProposalRibbon {

    private ContestPhaseRibbonType contestPhaseRibbonType;
    private final Proposal proposalWrapper;

    private final RestService proposalRestService;

    public ProposalRibbon(Proposal proposalWrapper, RestService restService) {
        this.proposalWrapper = proposalWrapper;
        this.proposalRestService = restService;
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


            RestService contestService =  proposalRestService.withServiceName(CoLabService.CONTEST.getServiceName());
            contestPhase = ContestClient.fromService(contestService).getContestPhase(proposalWrapper.getContestPhase().getContestPhasePK());

            if (contestPhase == null) {
                //_log.info(String.format("Could not retrieve ribbon type. Wrapper for proposal %d in Contest %d has no contestPhase.",
                //        proposalId, proposalWrapper.getContestPK()));
                return null;
            }

                ribbonAttribute = ProposalPhaseClient.fromService(proposalRestService)
                        .getProposalContestPhaseAttribute(
                                proposalId,
                                contestPhase.getContestPhasePK(),
                                ProposalContestPhaseAttributeKeys.RIBBON
                        );
                if (ribbonAttribute != null) {
                    long typeId = ribbonAttribute.getNumericValue();
                    if (typeId >= 0) {
                        contestPhaseRibbonType = ContestClient.fromService(contestService).getContestPhaseRibbonType(typeId);
                    }
                } else {
                    //_log.warn(String.format("Could not retrieve ribbon type for proposal %d", proposalId));
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
       // _log.error(String.format("Could not get ribbon title: ContestPhaseRibbonType was null for proposal %d", proposalWrapper.getProposalId()));
        return "";
    }
}
