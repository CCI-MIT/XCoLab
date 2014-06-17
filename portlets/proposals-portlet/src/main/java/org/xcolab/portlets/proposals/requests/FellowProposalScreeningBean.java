package org.xcolab.portlets.proposals.requests;

import com.ext.portlet.JudgingSystemActions;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.xcolab.portlets.proposals.wrappers.ProposalFellowWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalsPreferencesWrapper;
import org.xcolab.utils.judging.ProposalJudgingCommentHelper;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kmang on 25/05/14.
 */
public class FellowProposalScreeningBean implements Serializable {
    private Long fellowScreeningRating;
    private JudgingSystemActions.FellowAction fellowScreeningAction;

    @NotBlank
    private String fellowScreeningCommentBody;

    private List<Long> selectedJudges;

    private String[] fellowCommentFooters = {"", "", "", ""};
    private String[] fellowCommentHeaders = {"", "", "", ""};

    public FellowProposalScreeningBean(ProposalFellowWrapper wrapper, ProposalsPreferencesWrapper preferencesWrapper) throws PortalException, SystemException {
        fellowScreeningRating = wrapper.getFellowRating();
        fellowScreeningAction = wrapper.getFellowAction();
        selectedJudges = wrapper.getSelectedJudges();

        // Initialize comment headers and footers
        fellowCommentHeaders = new String[]{
                "",
                ProposalJudgingCommentHelper.getCommentHeader(removeLineBreaks(preferencesWrapper.getScreeningIncompleteText())),
                ProposalJudgingCommentHelper.getCommentHeader(removeLineBreaks(preferencesWrapper.getScreeningOfftopicText())),
                ""
        };
        fellowCommentFooters = new String[]{
                "",
                ProposalJudgingCommentHelper.getCommentFooter(removeLineBreaks(preferencesWrapper.getScreeningIncompleteText())),
                ProposalJudgingCommentHelper.getCommentFooter(removeLineBreaks(preferencesWrapper.getScreeningOfftopicText())),
                ""
        };
        // Extract comment body from whole comment
        fellowScreeningCommentBody = ProposalJudgingCommentHelper.extractComment(wrapper.getFellowComment());
    }

    public FellowProposalScreeningBean() {
    }

    public Long getFellowScreeningRating() {
        return fellowScreeningRating;
    }

    public void setFellowScreeningRating(Long fellowScreeningRating) {
        this.fellowScreeningRating = fellowScreeningRating;
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

    public String getFellowScreeningCommentBody() {
        return fellowScreeningCommentBody;
    }

    public void setFellowScreeningCommentBody(String fellowCommentBody) {
        this.fellowScreeningCommentBody = fellowCommentBody;
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