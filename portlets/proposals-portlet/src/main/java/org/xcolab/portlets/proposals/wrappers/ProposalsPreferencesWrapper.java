package org.xcolab.portlets.proposals.wrappers;

import org.apache.commons.lang.StringUtils;
import org.xcolab.utils.judging.ProposalJudgingCommentHelper;

import java.io.IOException;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

public class ProposalsPreferencesWrapper {

    private final static String TERMS_OF_SERVICE_PREF = "DEFAULT_DESCRIPTION";
    private String termsOfService;

    private final static String SCREENING_INCOMPLETE_TEXT = "SCREENING_INCOMPLETE_TEXT";
    private String screeningIncompleteText;

    private final static String ADVANCE_REJECTION_TEXT = "ADVANCE_REJECTION_TEXT";
    private String advanceRejectionText;

    private final static String ADVANCE_ACCEPTANCE_TEXT = "ADVANCE_ACCEPTANCE_TEXT";
    private String advanceAcceptanceText;

    private final static String SCREENING_OFFTOPIC_TEXT = "SCREENING_OFFTOPIC_TEXT";
    private String screeningOfftopicText;

    public ProposalsPreferencesWrapper() {

    }

    public ProposalsPreferencesWrapper(PortletRequest request) {
        PortletPreferences preferences = request.getPreferences();
        termsOfService = preferences.getValue(TERMS_OF_SERVICE_PREF, "");
        screeningIncompleteText = preferences.getValue(SCREENING_INCOMPLETE_TEXT, "your proposal was incomplete "
                + ProposalJudgingCommentHelper.REVIEW_COMMENT_BEGIN_TAG + ProposalJudgingCommentHelper.REVIEW_COMMENT_END_TAG);
        advanceRejectionText = preferences.getValue(ADVANCE_REJECTION_TEXT, "your proposal was rejected "
                + ProposalJudgingCommentHelper.REVIEW_COMMENT_BEGIN_TAG + ProposalJudgingCommentHelper.REVIEW_COMMENT_END_TAG);
        advanceAcceptanceText = preferences.getValue(ADVANCE_ACCEPTANCE_TEXT, "your proposal was accepted "
                + ProposalJudgingCommentHelper.REVIEW_COMMENT_BEGIN_TAG + ProposalJudgingCommentHelper.REVIEW_COMMENT_END_TAG);
        screeningOfftopicText = preferences.getValue(SCREENING_OFFTOPIC_TEXT, "your proposal was offtopic "
                + ProposalJudgingCommentHelper.REVIEW_COMMENT_BEGIN_TAG + ProposalJudgingCommentHelper.REVIEW_COMMENT_END_TAG);
    }

    public void store(PortletRequest request) throws ReadOnlyException, ValidatorException, IOException {
        PortletPreferences preferences = request.getPreferences();
        preferences.setValue(TERMS_OF_SERVICE_PREF, termsOfService);
        preferences.setValue(SCREENING_INCOMPLETE_TEXT, screeningIncompleteText);
        preferences.setValue(ADVANCE_REJECTION_TEXT, advanceRejectionText);
        preferences.setValue(SCREENING_OFFTOPIC_TEXT, screeningOfftopicText);
        preferences.setValue(ADVANCE_ACCEPTANCE_TEXT, advanceAcceptanceText);
        preferences.store();
    }

    public String getTermsOfService() {
        return termsOfService;
    }

    public void setTermsOfService(String termsOfService) {
        this.termsOfService = termsOfService;
    }

    public String getScreeningIncompleteText() {
        return screeningIncompleteText;
    }

    public void setScreeningIncompleteText(String screeningIncompleteText) {
        this.screeningIncompleteText = screeningIncompleteText;
    }

    public String getAdvanceRejectionText() {
        return advanceRejectionText;
    }

    public void setAdvanceRejectionText(String advanceRejectionText) {
        this.advanceRejectionText = advanceRejectionText;
    }

    public String getAdvanceAcceptanceText() {
        return advanceAcceptanceText;
    }

    public void setAdvanceAcceptanceText(String advanceAcceptanceText) {
        this.advanceAcceptanceText = advanceAcceptanceText;
    }

    public String getScreeningOfftopicText() {
        return screeningOfftopicText;
    }

    public void setScreeningOfftopicText(String screeningOfftopicText) {
        this.screeningOfftopicText = screeningOfftopicText;
    }

    public String getCommentBeginTag() {
        return ProposalJudgingCommentHelper.REVIEW_COMMENT_BEGIN_TAG;
    }

    public String getCommentEndTag() {
        return ProposalJudgingCommentHelper.REVIEW_COMMENT_END_TAG;
    }
}
