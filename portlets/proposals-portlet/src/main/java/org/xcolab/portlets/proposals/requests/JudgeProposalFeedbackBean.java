package org.xcolab.portlets.proposals.requests;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.portlets.proposals.wrappers.ProposalJudgeWrapper;

/**
 * @author pdeboer
 */
public class JudgeProposalFeedbackBean {
    private Long judgeRating;
    private String judgeComment;




    public JudgeProposalFeedbackBean(ProposalJudgeWrapper wrapper) throws PortalException, SystemException {
        judgeRating = wrapper.getJudgeRating();
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
