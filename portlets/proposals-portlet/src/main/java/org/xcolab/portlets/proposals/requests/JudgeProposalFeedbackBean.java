package org.xcolab.portlets.proposals.requests;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.hibernate.validator.constraints.NotBlank;
import org.xcolab.portlets.proposals.wrappers.ProposalJudgeWrapper;

import javax.validation.constraints.NotNull;

/**
 * @author pdeboer
 */
public class JudgeProposalFeedbackBean {
    @NotNull
    private Long judgeRating;

    @NotBlank
    private String judgeComment;

    public JudgeProposalFeedbackBean(ProposalJudgeWrapper wrapper) throws PortalException, SystemException {
        judgeRating = wrapper.getJudgeRating();
        judgeComment = wrapper.getJudgeComment();
    }

    public JudgeProposalFeedbackBean() {

    }

    public Long getJudgeRating() {
        return judgeRating;
    }

    public void setJudgeRating(Long judgeRating) {
        this.judgeRating = judgeRating;
    }

    public String getJudgeComment() {
       return judgeComment;
    }

    public void setJudgeComment(String judgeComment) {
        this.judgeComment = judgeComment;
    }

}
