package org.xcolab.portlets.reporting.beans.proposalsinyear.proposalversiondeterminer;

import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalVersionLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author pdeboer
 *         First created on 27/07/14 at 19:26
 */
public class GetLastVersionOfPhaseType implements ProposalVersionDeterminer {
	final List<Long> contestPhaseTypeIDs;
	private List<ContestPhase> contestPhases;

	public GetLastVersionOfPhaseType(List<Long> contestPhaseTypeIDs) {
		this.contestPhaseTypeIDs = contestPhaseTypeIDs;
		try {
			contestPhases = ContestPhaseLocalServiceUtil.getContestPhases(0, Integer.MAX_VALUE);
		} catch (SystemException e) {
			e.printStackTrace();
		}
	}

	public GetLastVersionOfPhaseType(List<Long> contestPhaseTypeIDs, List<ContestPhase> contestPhases) {
		this.contestPhaseTypeIDs = contestPhaseTypeIDs;
		this.contestPhases = contestPhases;
	}

	public GetLastVersionOfPhaseType(Long contestPhaseTypeID, List<ContestPhase> contestPhases) {
		this.contestPhaseTypeIDs = Arrays.asList(contestPhaseTypeID);
		this.contestPhases = contestPhases;
	}

	@Override
	public int getTargetVersion(Proposal proposal) {
		try {
			List<Proposal2Phase> proposalAssignments = Proposal2PhaseLocalServiceUtil.getByProposalId(proposal.getProposalId());
			ContestPhase targetPhase = getTargetContestPhase(proposalAssignments);
			if (targetPhase == null) {
				System.err.println("Phase not found for " + proposal.getProposalId());
				return -1;
			}
			ProposalVersion targetProposalVersion = getTargetProposalVersion(proposal, targetPhase);

			return targetProposalVersion.getVersion();
		} catch (Exception e) {
			throw new RuntimeException(proposal.getProposalId() + "", e);
		}
	}

	private ProposalVersion getTargetProposalVersion(Proposal proposal, ContestPhase targetPhase) throws SystemException {
		ProposalVersion targetProposalVersion = null;
		ProposalVersion firstVersion = null;
		List<ProposalVersion> proposalVersions = ProposalVersionLocalServiceUtil.getByProposalId(proposal.getProposalId(), 0, Integer.MAX_VALUE);
		for (ProposalVersion proposalVersion : proposalVersions) {
			if (firstVersion == null || proposalVersion.getVersion() < firstVersion.getVersion())
				firstVersion = proposalVersion;

			if (targetPhase.getPhaseEndDate() == null || proposalVersion.getCreateDate().before(targetPhase.getPhaseEndDate()))
				if (targetProposalVersion == null || targetProposalVersion.getVersion() < proposalVersion.getVersion())
					targetProposalVersion = proposalVersion;
		}

		return targetProposalVersion == null ? firstVersion : targetProposalVersion;
	}

	private ContestPhase getTargetContestPhase(List<Proposal2Phase> proposalAssignments) {
		ContestPhase targetPhase = null;
		Date currentPhaseEndDate = null;
		for (Proposal2Phase proposalAssignment : proposalAssignments) {
			for (ContestPhase contestPhase : contestPhases) {
				if (proposalAssignment.getContestPhaseId() == contestPhase.getContestPhasePK()) {
					if (this.contestPhaseTypeIDs.contains(contestPhase.getContestPhaseType()) && (currentPhaseEndDate == null || contestPhase.getPhaseEndDate() == null || contestPhase.getPhaseEndDate().after(currentPhaseEndDate))) {
						targetPhase = contestPhase;
						currentPhaseEndDate = targetPhase.getPhaseEndDate();
						break;
					}
				}
			}
		}
		return targetPhase;
	}


}
