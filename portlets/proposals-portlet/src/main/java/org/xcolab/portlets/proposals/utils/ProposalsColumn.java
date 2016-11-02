package org.xcolab.portlets.proposals.utils;

import org.xcolab.client.proposals.pojo.Proposal;

import java.util.Comparator;

public enum ProposalsColumn {
    NAME(new Comparator<Proposal>() {

        @Override
        public int compare(Proposal o1, Proposal o2) {
            return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
        }
        
    }), 
    AUTHOR(new Comparator<Proposal>() {

        @Override
        public int compare(Proposal o1, Proposal o2) {
            return o1.getAuthorName().toLowerCase().compareTo(o2.getAuthorName().toLowerCase());
        }
        
    }),
    SUPPORTERS(new Comparator<Proposal>() {

        @Override
        public int compare(Proposal o1, Proposal o2) {
            return (int) (o1.getSupportersCount() - o2.getSupportersCount());
        }
        
    }),
    VOTES(new Comparator<Proposal>() {

        @Override
        public int compare(Proposal o1, Proposal o2) {
            try {
                return (int) (o1.getVotesCount() - o2.getVotesCount());
            }
            catch (Exception e) {
                return (int) (o1.getProposalId() - o2.getProposalId());
            }
        }
        
    }),
    COMMENTS(new Comparator<Proposal>() {

        @Override
        public int compare(Proposal o1, Proposal o2) {
            return (int) (o1.getCommentsCount() - o2.getCommentsCount());
        }
    }),
    JUDGESTATUS(new Comparator<Proposal>() {

        @Override
        public int compare(Proposal o1, Proposal o2) {
            return (o1.getJudgeStatus().getStatusValue() - o2.getJudgeStatus().getStatusValue());
        }
    }),
    OVERALLSTATUS(new Comparator<Proposal>() {

        @Override
        public int compare(Proposal o1, Proposal o2) {
            return (o1.getOverallStatus().getStatusValue() - o2.getOverallStatus().getStatusValue());
        }
    }),
    SCREENINGSTATUS(new Comparator<Proposal>() {

        @Override
        public int compare(Proposal o1, Proposal o2) {
            return (o1.getScreeningStatus().getStatusValue() - o2.getScreeningStatus().getStatusValue());
        }
    }),
    MODIFIED(new Comparator<Proposal>() {

        @Override
        public int compare(Proposal o1, Proposal o2) {
            return o1.getLastModifiedDate().compareTo(o2.getLastModifiedDate());
        }
    }),
    CONTRIBUTORS(new Comparator<Proposal>() {

        @Override
        public int compare(Proposal o1, Proposal o2) {
            if (o1.isOpen()) {
                return o2.isOpen() ? 0 : -1;
            } else {
                return o2.isOpen() ? 1 : 0;
            }
        }
    });
    
    private final Comparator<Proposal> proposalsComparator;
    
    ProposalsColumn(Comparator<Proposal> comparator) {
        proposalsComparator = comparator;
    }

    public Comparator<Proposal> getComparator() {
        return proposalsComparator;
    }

}
