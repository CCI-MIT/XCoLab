package org.xcolab.portlets.reporting.beans.proposalsinyear.proposalversiondeterminer;

import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalVersionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author pdeboer
 *         First created on 27/07/14 at 19:26
 */
public class GetLastVersionOfPhaseType implements ProposalVersionDeterminer {
    final long phaseTypeId;
    private List<ContestPhase> contestPhases;

    public GetLastVersionOfPhaseType(long phaseTypeId) {
        this.phaseTypeId = phaseTypeId;
        try {
            contestPhases = ContestPhaseLocalServiceUtil.getContestPhases(0, Integer.MAX_VALUE);
        } catch (SystemException e) {
            e.printStackTrace();
        }
    }

    public GetLastVersionOfPhaseType(long phaseTypeId, List<ContestPhase> contestPhases) {
        this.phaseTypeId = phaseTypeId;
        this.contestPhases = contestPhases;
    }

    @Override
    public int getTargetVersion(Proposal proposal) {
        try {
            List<Proposal2Phase> proposalAssignments = Proposal2PhaseLocalServiceUtil.getByProposalId(proposal.getProposalId());
            ContestPhase targetPhase = getTargetContestPhase(proposalAssignments);
            if(targetPhase == null) {
                System.err.println("Phase not found for "+proposal.getProposalId());
                return -1;
            }
            ProposalVersion targetProposalVersion = getTargetProposalVersion(proposal, targetPhase);

            return targetProposalVersion.getVersion();
        } catch (Exception e) {
            throw new RuntimeException(proposal.getProposalId()+"",e);
        }
    }

    private ProposalVersion getTargetProposalVersion(Proposal proposal, ContestPhase targetPhase) throws SystemException {
        ProposalVersion targetProposalVersion = null;
        List<ProposalVersion> proposalVersions = ProposalVersionLocalServiceUtil.getByProposalId(proposal.getProposalId(), 0, Integer.MAX_VALUE);
        for (ProposalVersion proposalVersion : proposalVersions) {
            if(targetPhase.getPhaseEndDate() != null && proposalVersion.getCreateDate().before(targetPhase.getPhaseEndDate()))
                if(targetProposalVersion == null || targetProposalVersion.getVersion() < proposalVersion.getVersion())
                    targetProposalVersion = proposalVersion;
        }
        return targetProposalVersion;
    }

    private ContestPhase getTargetContestPhase(List<Proposal2Phase> proposalAssignments) {
        ContestPhase targetPhase = null;
        for (Proposal2Phase proposalAssignment : proposalAssignments) {
            for (ContestPhase contestPhase : contestPhases) {
                if(proposalAssignment.getContestPhaseId() == contestPhase.getContestPhasePK()) {
                    if(contestPhase.getContestPhaseType() == phaseTypeId) {
                        targetPhase = contestPhase;
                        break;
                    }
                }
            }
        }
        return targetPhase;
    }


}
