package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.*;
import com.ext.portlet.service.MessageRecipientStatusLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import org.xcolab.portlets.proposals.utils.ProposalAttributeUtil;

/**
 * Created by patrickhiesel on 19/12/13.
 */
public class ProposalJudgeWrapper extends ProposalWrapper{

    private User currentUser;

    public ProposalJudgeWrapper(Proposal proposal, User currentUser) {
        super(proposal);
        this.currentUser = currentUser;
    }

    public ProposalJudgeWrapper(Proposal proposal, int version, User currentUser) {
        super(proposal, version);
        this.currentUser = currentUser;
    }

    public ProposalJudgeWrapper(Proposal proposal, int version, Contest contest, ContestPhase contestPhase, Proposal2Phase proposal2Phase, User currentUser) {
        super(proposal, version, contest, contestPhase, proposal2Phase);
        this.currentUser = currentUser;
    }

    public boolean getIsJudgeAssignedAndIncomplete() throws SystemException, PortalException {
        if (currentUser == null) return false;
        Proposal p = ProposalLocalServiceUtil.getProposal(this.getProposalId());
        for (long l : this.getSelectedJudges()){
            if (currentUser.getUserId() == l) return !MessageRecipientStatusLocalServiceUtil.didReceiveJudgeCommentForProposal(p, currentUser);
        }
        return false;
    }

    /**
     * get email message that is supposed that is supposed to be sent out to users based on the decisions either the fellow or the judge took
     * @param contestPhaseId
     * @param prefs
     * @return
     */
    public String getEmailMessage(long contestPhaseId, ProposalsPreferencesWrapper prefs) {
        //get fellow decision
        ProposalContestPhaseAttribute p = getProposalContestPhaseAttributeCreateIfNotExists(getProposalId(), contestPhaseId, ProposalContestPhaseAttributeKeys.FELLOW_ACTION, 0);
        JudgingSystemActions.FellowAction fellowAction = JudgingSystemActions.FellowAction.valueOf(p.getStringValue());
        if(fellowAction == JudgingSystemActions.FellowAction.PASSTOJUDGES) {
            //judge decided
            String judgeText = getProposalContestPhaseAttributeCreateIfNotExists(getProposalId(), contestPhaseId, ProposalContestPhaseAttributeKeys.JUDGE_COMMENT, 0).getStringValue();
            ProposalContestPhaseAttribute pa = getProposalContestPhaseAttributeCreateIfNotExists(getProposalId(), contestPhaseId, ProposalContestPhaseAttributeKeys.JUDGE_ACTION, 0);
            JudgingSystemActions.JudgeAction judgeAction = JudgingSystemActions.JudgeAction.valueOf(pa.getStringValue());
            if(judgeAction == JudgingSystemActions.JudgeAction.DONT_MOVE_ON) {
                return prefs.replaceJudgingTemplate(prefs.getJudgingRejectionText(), judgeText);
            }else if(judgeAction == JudgingSystemActions.JudgeAction.MOVE_ON) {
                return prefs.replaceJudgingTemplate(prefs.getJudgingAcceptanceText(), judgeText);
            }
        }else{
            //fellow decided
            String fellowText = getProposalContestPhaseAttributeCreateIfNotExists(getProposalId(), contestPhaseId, ProposalContestPhaseAttributeKeys.FELLOW_COMMENT, 0).getStringValue();
            if(fellowAction == JudgingSystemActions.FellowAction.INCOMPLETE) {
                return prefs.replaceJudgingTemplate(prefs.getJudgingIncompleteText(), fellowText);
            }else if(fellowAction== JudgingSystemActions.FellowAction.OFFTOPIC){
                return prefs.replaceJudgingTemplate(prefs.getJudgingOfftopicText(), fellowText);
            }
        }
        return null;
    }


    private ProposalContestPhaseAttribute getProposalContestPhaseAttributeCreateIfNotExists(long proposalId, long contestPhaseId, String attributeName, long additionalId) {
        try {
            return ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(proposalId, contestPhaseId, attributeName, additionalId);
        } catch (NoSuchProposalContestPhaseAttributeException e) {
            try {
                ProposalContestPhaseAttribute attribute = ProposalContestPhaseAttributeLocalServiceUtil.createProposalContestPhaseAttribute(
                        CounterLocalServiceUtil.increment(ProposalContestPhaseAttribute.class.getName()));
                attribute.setProposalId(proposalId);
                attribute.setContestPhaseId(contestPhaseId);
                attribute.setName(attributeName);
                ProposalContestPhaseAttributeLocalServiceUtil.addProposalContestPhaseAttribute(attribute);
                return attribute;
            } catch (Exception e2) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
