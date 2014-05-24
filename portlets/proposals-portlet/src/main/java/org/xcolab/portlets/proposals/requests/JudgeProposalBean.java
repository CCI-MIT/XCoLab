package org.xcolab.portlets.proposals.requests;

import com.ext.portlet.JudgingSystemActions;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.portlets.proposals.wrappers.ProposalJudgeWrapper;

import java.util.List;

/**
 * @author pdeboer
 */
public class JudgeProposalBean {
    private Long judgeRating;
    private String judgeComment;
    private Long fellowRating;
    private JudgingSystemActions.JudgeDecision judgeDecision;
    private JudgingSystemActions.FellowAction fellowAction;
    private String fellowComment;
    private List<Long> selectedJudges;

    public JudgeProposalBean(ProposalJudgeWrapper wrapper) throws PortalException, SystemException {

        judgeRating = wrapper.getJudgeRating();
        fellowRating = wrapper.getFellowRating();

        judgeDecision = wrapper.getJudgeDecision();
        fellowAction = wrapper.getFellowAction();
        selectedJudges = wrapper.getSelectedJudges();

        fellowComment = wrapper.getFellowComment();

        judgeRating = wrapper.getJudgeRating();
        judgeComment = wrapper.getJudgeComment();
    }

    public JudgeProposalBean() {

    }

    public Long getJudgeRating() {
        return judgeRating;
    }

    public void setJudgeRating(Long judgeRating) {
        this.judgeRating = judgeRating;
    }

    public Long getFellowRating() {
        return fellowRating;
    }

    public void setFellowRating(Long fellowRating) {
        this.fellowRating = fellowRating;
    }

    public int getJudgeDecision() {
        return judgeDecision.getAttributeValue();
    }

    public void setJudgeDecision(int judgeDecisionValue) {
        this.judgeDecision = JudgingSystemActions.JudgeDecision.fromInt(judgeDecisionValue);
    }

    public int getFellowAction() {
        return fellowAction.getAttributeValue();
    }

    public void setFellowAction(int fellowActionValue) {
        this.fellowAction = JudgingSystemActions.FellowAction.fromInt(fellowActionValue);
    }

    public String getJudgeComment() {
       return judgeComment;
    }

    public void setJudgeComment(String judgeComment) {
        this.judgeComment = judgeComment;
    }

    public List<Long> getSelectedJudges() {
        return selectedJudges;
    }

    public void setSelectedJudges(List<Long> selectedJudges) {
        this.selectedJudges = selectedJudges;
    }

    public String getFellowComment() {
        return fellowComment;
    }

    public void setFellowComment(String fellowComment) {
        this.fellowComment = fellowComment;
    }

}
