package org.xcolab.portlets.proposals.requests;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.model.ContestPhase;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalsPreferencesWrapper;
import org.xcolab.utils.judging.ProposalJudgingCommentHelper;

/**
 * Created by kmang on 25/05/14.
 */
public class ProposalAdvancingBean {

    private static final String[] EMAIL_TEMPLATES_TO_LOAD = {
            "ADVANCING_ADVANCE_TO_SEMIFINALIST",
            "ADVANCING_DO_NOT_ADVANCE"
    };
    private ContestEmailTemplateBean emailTemplateBean;

    private JudgingSystemActions.AdvanceDecision advanceDecision;

    @NotBlank
    private String advanceComment;

    public ProposalAdvancingBean() {
    }

    public ProposalAdvancingBean(ProposalWrapper wrapper) throws PortalException, SystemException {
        advanceDecision = wrapper.getJudgeDecision();
        advanceComment = wrapper.getProposalReview();

        //initialize email templates
        this.emailTemplateBean = new ContestEmailTemplateBean(EMAIL_TEMPLATES_TO_LOAD, wrapper.getName(), wrapper.getContest().getContestName());
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
