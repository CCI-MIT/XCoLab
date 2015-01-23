package org.xcolab.portlets.proposals.requests;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.service.ProposalRatingTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.portlets.proposals.wrappers.ProposalFellowWrapper;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kmang on 25/05/14.
 */
public class FellowProposalScreeningBean extends RatingBean implements Serializable {
    private JudgingSystemActions.FellowAction fellowScreeningAction;

    private String fellowScreeningActionCommentBody;

    private List<Long> selectedJudges;

    private static final String[] EMAIL_TEMPLATES_TO_LOAD = {
            "SCREENING_DO_NOT_ADVANCE_INCOMPLETE",
            "SCREENING_DO_NOT_ADVANCE_OFF_TOPIC",
            "SCREENING_DO_NOT_ADVANCE_OTHER"
    };
    private ContestEmailTemplateBean emailTemplateBean;

    public FellowProposalScreeningBean(ProposalFellowWrapper proposalWrapper) throws PortalException, SystemException {
        super(proposalWrapper, ProposalRatingTypeLocalServiceUtil.getRatingTypesForFellows());

        fellowScreeningAction = proposalWrapper.getFellowAction();
        selectedJudges = proposalWrapper.getSelectedJudges();

        //initialize email templates
        this.emailTemplateBean = new ContestEmailTemplateBean(EMAIL_TEMPLATES_TO_LOAD, proposalWrapper.getName(), proposalWrapper.getContest().getContestShortName());

        fellowScreeningActionCommentBody = proposalWrapper.getFellowActionComment();
    }

    public FellowProposalScreeningBean() {
    }

    public ContestEmailTemplateBean getEmailTemplateBean() {
        return this.emailTemplateBean;
    }

    public int getFellowScreeningAction() {
        if (fellowScreeningAction == null) {
            return JudgingSystemActions.FellowAction.NO_DECISION.getAttributeValue();
        } else {
            return fellowScreeningAction.getAttributeValue();
        }
    }

    public void setFellowScreeningAction(int fellowActionValue) {
        this.fellowScreeningAction = JudgingSystemActions.FellowAction.fromInt(fellowActionValue);
    }

    public void addSelectedJudge(Long userId){
        selectedJudges.add(userId);
    }

    public List<Long> getSelectedJudges() {
        return selectedJudges;
    }

    public void setSelectedJudges(List<Long> selectedJudges) {
        this.selectedJudges = selectedJudges;
    }

    public String getFellowScreeningActionCommentBody() {
        return fellowScreeningActionCommentBody;
    }

    public void setFellowScreeningActionCommentBody(String fellowActionCommentBody) {
        this.fellowScreeningActionCommentBody = fellowActionCommentBody;
    }
}