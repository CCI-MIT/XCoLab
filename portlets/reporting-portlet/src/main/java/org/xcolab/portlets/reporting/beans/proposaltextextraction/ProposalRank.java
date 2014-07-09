package org.xcolab.portlets.reporting.beans.proposaltextextraction;

/**
* @author pdeboer
*         First created on 08/07/14 at 16:28
*/
public enum ProposalRank {
    NON_FINALIST(0, 0), SEMI_FINALIST(1, 3), FINALIST(2, 2), WINNER(3, 1);

    public final int fileNum, ribbonType;

    ProposalRank(int fileNum, int ribbonType) {
        this.fileNum = fileNum;
        this.ribbonType = ribbonType;
    }

    public static ProposalRank fromRibbonType(int ribbonType) {
        for (ProposalRank proposalRank : ProposalRank.values()) {
            if(proposalRank.ribbonType == ribbonType) return proposalRank;
        }
        return null;
    }
}
