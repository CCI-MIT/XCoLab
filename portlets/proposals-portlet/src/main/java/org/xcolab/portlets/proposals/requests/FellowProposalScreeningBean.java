package org.xcolab.portlets.proposals.requests;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.service.ProposalRatingTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.apache.commons.lang.StringUtils;
import org.xcolab.portlets.proposals.wrappers.ProposalFellowWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalsPreferencesWrapper;
import org.xcolab.utils.judging.ProposalJudgingCommentHelper;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kmang on 25/05/14.
 */
public class FellowProposalScreeningBean extends RatingBean implements Serializable {
    private JudgingSystemActions.FellowAction fellowScreeningAction;

    private String fellowScreeningActionCommentBody;

    private List<Long> selectedJudges;

    private String[] fellowCommentFooters = {"", "", "", ""};
    private String[] fellowCommentHeaders = {"", "", "", ""};

    public FellowProposalScreeningBean(ProposalFellowWrapper wrapper, ProposalsPreferencesWrapper preferencesWrapper) throws PortalException, SystemException {
        super(wrapper, ProposalRatingTypeLocalServiceUtil.getRatingTypesForFellows());

        fellowScreeningAction = wrapper.getFellowAction();
        selectedJudges = wrapper.getSelectedJudges();

        // Initialize comment headers and footers
        //TODO: decide if the header and footer should still be visible here since the text is quite lengthy now
        fellowCommentHeaders = new String[]{
                "","","",
                //ProposalJudgingCommentHelper.getCommentHeader(removeLineBreaks(preferencesWrapper.getScreeningIncompleteText())),
                //ProposalJudgingCommentHelper.getCommentHeader(removeLineBreaks(preferencesWrapper.getScreeningOfftopicText())),
                ""
        };
        fellowCommentFooters = new String[]{
                "","","",
                //ProposalJudgingCommentHelper.getCommentFooter(removeLineBreaks(preferencesWrapper.getScreeningIncompleteText())),
                //ProposalJudgingCommentHelper.getCommentFooter(removeLineBreaks(preferencesWrapper.getScreeningOfftopicText())),
                ""
        };
        fellowScreeningActionCommentBody = wrapper.getFellowActionComment();
    }

    public FellowProposalScreeningBean() {
    }

    public int getFellowScreeningAction() {
        return fellowScreeningAction.getAttributeValue();
    }

    public void setFellowScreeningAction(int fellowActionValue) {
        this.fellowScreeningAction = JudgingSystemActions.FellowAction.fromInt(fellowActionValue);
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

    public String[] getFellowCommentHeaders() {
        return fellowCommentHeaders;
    }

    public String[] getFellowCommentFooters() {
        return fellowCommentFooters;
    }

    private String removeLineBreaks(String html) {
        return StringUtils.replace(html, "\n", "");
    }
}