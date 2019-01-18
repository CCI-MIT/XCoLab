package org.xcolab.service.contest.proposal.helper.autopromotion;



import org.xcolab.client.contest.pojo.IProposalContestPhaseAttribute;
import org.xcolab.client.contest.pojo.tables.pojos.ProposalContestPhaseAttribute;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

/**
 * Created by johannes on 9/8/15.
 *
 * Helper class to promote proposals in the given phase.
 */
public class PhasePromotionHelper {

    public static IProposalContestPhaseAttribute createProposalContestPhasePromotionDoneAttribute(long proposalId, long currentPhaseId) {
        //save the information that the promotion has been done.
        if (currentPhaseId != 0) {
            IProposalContestPhaseAttribute proposalContestPhaseAttribute = new ProposalContestPhaseAttribute();
            proposalContestPhaseAttribute.setProposalId(proposalId);
            proposalContestPhaseAttribute.setContestPhaseId(currentPhaseId);
            proposalContestPhaseAttribute.setName(ProposalContestPhaseAttributeKeys.PROMOTE_DONE);
            proposalContestPhaseAttribute.setAdditionalId(0L);
            proposalContestPhaseAttribute.setStringValue("true");
            return proposalContestPhaseAttribute;
        }
        return null;
    }
}
