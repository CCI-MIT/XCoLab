package org.xcolab.portlets.reporting.beans.proposalactivityexport;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalVersionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.portlets.reporting.beans.proposaltextextraction.ProposalTextExtraction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author pdeboer
 *         First created on 18/07/14 at 13:24
 */
public class ProposalActivityExport {
    public List<ProposalActivities> get() throws SystemException, PortalException {
        List<Contest> contests = new ProposalTextExtraction().getContestsIn2013();
        List<ProposalVersion> versions = ProposalVersionLocalServiceUtil.getProposalVersions(0, Integer.MAX_VALUE);


        List<ProposalActivities> ret = new LinkedList<>();
        for (Contest contest : contests) {
            ContestPhase finalistSelection = null;
            ContestPhase proposalCreation = null;

            List<ContestPhase> allPhases = ContestLocalServiceUtil.getAllPhases(contest);
            for (ContestPhase phase : allPhases) {
                if(phase.getContestPhaseType() == 11L) finalistSelection = phase;
                else if(phase.getContestPhaseType() == 1) proposalCreation = phase;
            }

            if(finalistSelection == null || proposalCreation == null) {
                System.err.println("skipped "+contest);
                continue;
            }

            List<Proposal> proposals = ProposalLocalServiceUtil.getProposalsInContestPhase(proposalCreation.getContestPhasePK());
            for (Proposal proposal : proposals) {
                ProposalActivities pa = new ProposalActivities();

                pa.setNumDaysCreationIsBeforeFinalistSelection(getNumDaysBetweenProposalCreationAndPhaseStart(finalistSelection, proposal));
                pa.setNumUpdates(getNumVersionsBeforePhaseStart(versions, finalistSelection, proposal));
                pa.setNumDifferentDaysProposalUpdates(getNumChangesOnDifferentDaysBeforePhaseStart(versions, finalistSelection, proposal));

                pa.setProposal(proposal);

                ret.add(pa);
            }
        }
        return ret;
    }

    private int getNumChangesOnDifferentDaysBeforePhaseStart(List<ProposalVersion> versions, ContestPhase finalistSelection, Proposal proposal) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Set<String> dates = new HashSet<>();
        for (ProposalVersion version : versions) {
            if(version.getProposalId() == proposal.getProposalId())
                if(version.getCreateDate().before(finalistSelection.getPhaseEndDate())) {
                    dates.add(sdf.format(version.getCreateDate()));
                }
        }
        return dates.size();
    }

    private int getNumVersionsBeforePhaseStart(List<ProposalVersion> versions, ContestPhase finalistSelection, Proposal proposal) {
        int count =0;
        for (ProposalVersion proposalVersion : versions) {
            if(proposalVersion.getProposalId() == proposal.getProposalId())
                if(proposalVersion.getCreateDate().before(finalistSelection.getPhaseEndDate()))
                    count++;
        }
        return count;
    }

    private int getNumDaysBetweenProposalCreationAndPhaseStart(ContestPhase finalistSelection, Proposal proposal) {
        long diff = finalistSelection.getPhaseStartDate().getTime() - proposal.getCreateDate().getTime();
        return (int) (diff / (24 * 60 * 60 * 1000));
    }
}
