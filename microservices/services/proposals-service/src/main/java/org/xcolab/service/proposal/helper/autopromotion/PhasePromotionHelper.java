package org.xcolab.service.proposal.helper.autopromotion;



import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.phases.ContestPhase;

import org.xcolab.model.tables.pojos.Proposal;
import org.xcolab.model.tables.pojos.ProposalContestPhaseAttribute;
import org.xcolab.service.proposal.domain.proposal.ProposalDao;
import org.xcolab.service.proposal.domain.proposalcontestphaseattribute.ProposalContestPhaseAttributeDao;
import org.xcolab.service.proposal.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;

/**
 * Created by johannes on 9/8/15.
 *
 * Helper class to promote proposals in the given phase.
 */
public class PhasePromotionHelper {




    public static ProposalContestPhaseAttribute createProposalContestPhasePromotionDoneAttribute(long proposalId, long currentPhaseId) {
        //save the information that the promotion has been done.
        if (currentPhaseId != 0) {
            ProposalContestPhaseAttribute proposalContestPhaseAttribute = new ProposalContestPhaseAttribute();
            proposalContestPhaseAttribute.setProposalId(proposalId);
            proposalContestPhaseAttribute.setContestPhaseId(currentPhaseId);
            proposalContestPhaseAttribute.setName(ProposalContestPhaseAttributeKeys.PROMOTE_DONE);
            proposalContestPhaseAttribute.setAdditionalId(0l);
            proposalContestPhaseAttribute.setStringValue("true");
            return proposalContestPhaseAttribute;

        }
        return null;
    }
}
