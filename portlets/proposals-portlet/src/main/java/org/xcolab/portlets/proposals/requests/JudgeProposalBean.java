package org.xcolab.portlets.proposals.requests;

import com.ext.portlet.JudgingSystemActions;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.hibernate.validator.constraints.NotBlank;
import org.xcolab.portlets.proposals.wrappers.ProposalSectionWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pdeboer
 */
public class JudgeProposalBean {
    private Long judgeRating;
    private Long fellowRating;
    private JudgingSystemActions.JudgeAction judgeAction;
    private JudgingSystemActions.FellowAction fellowAction;
    private String judgeComment;
    private List<Long> selectedJudges;

    public JudgeProposalBean(ProposalWrapper wrapper) throws PortalException, SystemException {

        judgeRating = wrapper.getJudgeRating();
        fellowRating = wrapper.getFellowRating();

        judgeAction = wrapper.getJudgeAction();
        fellowAction = wrapper.getFellowAction();
        selectedJudges = wrapper.getSelectedJudges();

    }

    public JudgeProposalBean(){

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

    public JudgingSystemActions.JudgeAction getJudgeAction() {
        return judgeAction;
    }

    public void setJudgeAction(JudgingSystemActions.JudgeAction judgeAction) {
        this.judgeAction = judgeAction;
    }

    public JudgingSystemActions.FellowAction getFellowAction() {
        return fellowAction;
    }

    public void setFellowAction(JudgingSystemActions.FellowAction fellowAction) {
        this.fellowAction = fellowAction;
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

}
