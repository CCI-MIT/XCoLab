package org.xcolab.portlets.proposals.requests;

import com.ext.portlet.JudgingSystemActions;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.portlets.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import java.util.List;

/**
 * Created by kmang on 25/05/14.
 */
public class FellowProposalScreeningBean {
    private Long fellowRating;
    private JudgingSystemActions.FellowAction fellowAction;
    private String fellowComment;
    private List<Long> selectedJudges;

    public FellowProposalScreeningBean() {
    }

    public FellowProposalScreeningBean(ProposalWrapper wrapper) throws PortalException, SystemException {
        fellowRating = wrapper.getFellowRating();
        fellowAction = wrapper.getFellowAction();
        fellowComment = wrapper.getFellowComment();
        selectedJudges = wrapper.getSelectedJudges();
    }

    public Long getFellowRating() {
        return fellowRating;
    }

    public void setFellowRating(Long fellowRating) {
        this.fellowRating = fellowRating;
    }

    public int getFellowAction() {
        return fellowAction.getAttributeValue();
    }

    public void setFellowAction(int fellowActionValue) {
        this.fellowAction = JudgingSystemActions.FellowAction.fromInt(fellowActionValue);
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