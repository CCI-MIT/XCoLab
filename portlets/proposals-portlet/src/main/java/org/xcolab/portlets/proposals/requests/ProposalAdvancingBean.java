package org.xcolab.portlets.proposals.requests;

import org.hibernate.validator.constraints.NotBlank;

import com.ext.portlet.JudgingSystemActions;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.proposals.ProposalJudgeRatingClientUtil;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

public class ProposalAdvancingBean extends RatingBean {

    private static final String[] EMAIL_TEMPLATES_TO_LOAD = {
            "ADVANCING_ADVANCE_TO_SEMIFINALIST",
            "ADVANCING_DO_NOT_ADVANCE"
    };
    private ContestEmailTemplateBean emailTemplateBean;

    private JudgingSystemActions.AdvanceDecision advanceDecision;

    @NotBlank(message = "Please enter a comment before saving the decision")
    private String advanceComment;

    public ProposalAdvancingBean() {
    }

    public ProposalAdvancingBean(ProposalWrapper wrapper) throws PortalException, SystemException {
        super(wrapper, ProposalJudgeRatingClientUtil.getRatingTypesForJudges());

        advanceDecision = wrapper.getJudgeDecision();
        advanceComment = wrapper.getProposalReview();

        this.emailTemplateBean = new ContestEmailTemplateBean(EMAIL_TEMPLATES_TO_LOAD, wrapper.getName(), wrapper.getContest().getContestShortName());
    }

    public ContestEmailTemplateBean getEmailTemplateBean() {
        return this.emailTemplateBean;
    }

    public int getAdvanceDecision() {
        return advanceDecision.getAttributeValue();
    }

    public void setAdvanceDecision(int judgeDecisionValue) {
        this.advanceDecision = JudgingSystemActions.AdvanceDecision.fromInt(judgeDecisionValue);
    }

    public String getAdvanceComment() {
        return advanceComment;
    }

    public void setAdvanceComment(String advanceComment) {
        this.advanceComment = advanceComment;
    }
}
