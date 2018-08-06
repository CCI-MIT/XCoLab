package org.xcolab.service.proposal.service.proposal;

import org.springframework.stereotype.Service;

import org.xcolab.model.tables.pojos.Proposal2Phase;
import org.xcolab.model.tables.pojos.ProposalVersion;
import org.xcolab.service.proposal.domain.proposal2phase.Proposal2PhaseDao;
import org.xcolab.service.proposal.domain.proposalversion.ProposalVersionDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProposalVersionService {

    private final Proposal2PhaseDao proposal2PhaseDao;
    private final ProposalVersionDao proposalVersionDao;

    private static final long MILLISECONDS_TO_GROUP_VERSIONS = 1000 * 60;

    public ProposalVersionService(Proposal2PhaseDao proposal2PhaseDao,ProposalVersionDao proposalVersionDao ){
        this.proposal2PhaseDao = proposal2PhaseDao;
        this.proposalVersionDao = proposalVersionDao;
    }


    public List<ProposalVersion> getGroupedProposalVersionsForProposalAndContest(Long proposalId, Long contestId, int start, int end){

            List<Proposal2Phase> proposal2Phases =
                    proposal2PhaseDao.findByContestAndProposal(proposalId, contestId);


        Date oldDate = new Date();
        List<ProposalVersion> groupedProposalVersions = new ArrayList<>();
        int counter = 0;
        for (ProposalVersion proposalVersion: proposalVersionDao.findByProposal2Phase(proposal2Phases,proposalId)) {

            if (Math.abs(oldDate.getTime() - proposalVersion.getCreatedAt().getTime()) > MILLISECONDS_TO_GROUP_VERSIONS){

                if(counter >= start && counter <= end) {
                    groupedProposalVersions.add(proposalVersion);
                }
                oldDate = proposalVersion.getCreatedAt();
                counter++;
            }
        }



        return groupedProposalVersions;
    }

}
