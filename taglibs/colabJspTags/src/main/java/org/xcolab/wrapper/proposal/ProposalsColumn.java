package org.xcolab.wrapper.proposal;

import java.util.Comparator;

public enum ProposalsColumn {
    NAME(new Comparator<ProposalWrapper>() {

        @Override
        public int compare(ProposalWrapper o1, ProposalWrapper o2) {
            try {
                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            }
            catch (Exception e) {
                return (int) (o1.getProposalId() - o2.getProposalId());
            }
        }
        
    }), 
    AUTHOR(new Comparator<ProposalWrapper>() {

        @Override
        public int compare(ProposalWrapper o1, ProposalWrapper o2) {
            try {
                return o1.getAuthorName().toLowerCase().compareTo(o2.getAuthorName().toLowerCase());
            }
            catch (Exception e) {
                return (int) (o1.getProposalId() - o2.getProposalId());
            }
        }
        
    }),
    SUPPORTERS(new Comparator<ProposalWrapper>() {

        @Override
        public int compare(ProposalWrapper o1, ProposalWrapper o2) {
            try {
                return (int) (o1.getSupportersCount() - o2.getSupportersCount());
            }
            catch (Exception e) {
                return (int) (o1.getProposalId() - o2.getProposalId());
            }
        }
        
    }),
    VOTES(new Comparator<ProposalWrapper>() {

        @Override
        public int compare(ProposalWrapper o1, ProposalWrapper o2) {
            try {
                return (int) (o1.getVotesCount() - o2.getVotesCount());
            }
            catch (Exception e) {
                return (int) (o1.getProposalId() - o2.getProposalId());
            }
        }
        
    }),
    COMMENTS(new Comparator<ProposalWrapper>() {

        @Override
        public int compare(ProposalWrapper o1, ProposalWrapper o2) {
            try {
                return (int) (o1.getCommentsCount() - o2.getCommentsCount());
            }
            catch (Exception e) {
                return (int) (o1.getProposalId() - o2.getProposalId());
            }
        }
    }),
    JUDGESTATUS(new Comparator<ProposalWrapper>() {

        @Override
        public int compare(ProposalWrapper o1, ProposalWrapper o2) {
            return (o1.getJudgeStatus().getStatusValue() - o2.getJudgeStatus().getStatusValue());
        }
    }),
    OVERALLSTATUS(new Comparator<ProposalWrapper>() {

        @Override
        public int compare(ProposalWrapper o1, ProposalWrapper o2) {
            return (o1.getOverallStatus().getStatusValue() - o2.getOverallStatus().getStatusValue());
        }
    }),
    SCREENINGSTATUS(new Comparator<ProposalWrapper>() {

        @Override
        public int compare(ProposalWrapper o1, ProposalWrapper o2) {
            return (o1.getScreeningStatus().getStatusValue() - o2.getScreeningStatus().getStatusValue());
        }
    }),
    MODIFIED(new Comparator<ProposalWrapper>() {

        @Override
        public int compare(ProposalWrapper o1, ProposalWrapper o2) {
            return o1.getLastModifiedDate().compareTo(o2.getLastModifiedDate());
        }
    }),
    CONTRIBUTORS(new Comparator<ProposalWrapper>() {

        @Override
        public int compare(ProposalWrapper o1, ProposalWrapper o2) {
            try {
                if (o1.isOpen()) return o2.isOpen() ? 0 : -1;
                else return o2.isOpen() ? 1 : 0;
            }
            catch (Exception e) {
                return (int) (o1.getProposalId() - o2.getProposalId());
            }
        }
    });
    
    private final Comparator<ProposalWrapper> proposalsComparator;
    
    private ProposalsColumn(Comparator<ProposalWrapper> comparator) {
        proposalsComparator = comparator;
        
    }

    public Comparator<ProposalWrapper> getComparator() {
        return proposalsComparator;
    }

}
