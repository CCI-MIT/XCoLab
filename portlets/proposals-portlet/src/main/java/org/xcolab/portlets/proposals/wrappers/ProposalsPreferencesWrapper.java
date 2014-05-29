package org.xcolab.portlets.proposals.wrappers;

import org.xcolab.enums.ContestPhaseType;
import org.xcolab.utils.judging.ProposalJudgingCommentHelper;

import java.io.IOException;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

public class ProposalsPreferencesWrapper {

    private final static String TERMS_OF_SERVICE_PREF = "DEFAULT_DESCRIPTION";

    private PortletPreferences preferences;

    private String termsOfService;

    private final static String SCREENING_INCOMPLETE_TEXT = "SCREENING_INCOMPLETE_TEXT";
    private String screeningIncompleteText;

    private final static String ADVANCE_REJECTION_TEXT_PREFIX = "ADVANCE_REJECTION_TEXT_PHASE_";
    private String advanceRejectionSemifinalistSelectionText; // This is actually the phase which selects semi-finalists, yet it has a different name in the backend..
    private String advanceRejectionFinalistSelectionText;
    private String advanceRejectionWinnerSelectionText;

    private final static String ADVANCE_ACCEPTANCE_TEXT_PREFIX = "ADVANCE_ACCEPTANCE_TEXT_PHASE_";
    private String advanceAcceptanceSemifinalistSelectionText;
    private String advanceAcceptanceFinalistSelectionText;
    private String advanceAcceptanceWinnerSelectionText;

    private final static String SCREENING_OFFTOPIC_TEXT = "SCREENING_OFFTOPIC_TEXT";
    private String screeningOfftopicText;

    public ProposalsPreferencesWrapper() {

    }

    public ProposalsPreferencesWrapper(PortletRequest request) {
        this.preferences = request.getPreferences();
        termsOfService = preferences.getValue(TERMS_OF_SERVICE_PREF, "");
        screeningIncompleteText = preferences.getValue(SCREENING_INCOMPLETE_TEXT, "your proposal was incomplete "
                + ProposalJudgingCommentHelper.REVIEW_COMMENT_BEGIN_TAG + ProposalJudgingCommentHelper.REVIEW_COMMENT_END_TAG);
        screeningOfftopicText = preferences.getValue(SCREENING_OFFTOPIC_TEXT, "your proposal was offtopic "
                + ProposalJudgingCommentHelper.REVIEW_COMMENT_BEGIN_TAG + ProposalJudgingCommentHelper.REVIEW_COMMENT_END_TAG);

        advanceAcceptanceSemifinalistSelectionText = preferences.getValue(ADVANCE_ACCEPTANCE_TEXT_PREFIX + ContestPhaseType.FINALIST_SELECTION.getTypeId(), "your proposal was accepted <br/><br/>"
                + ProposalJudgingCommentHelper.REVIEW_COMMENT_BEGIN_TAG + ProposalJudgingCommentHelper.REVIEW_COMMENT_END_TAG + "<br/><br/>Sincerely,<br/>The Climate CoLab Team");
//        advanceAcceptanceFinalistSelectionText = preferences.getValue(ADVANCE_ACCEPTANCE_TEXT_PREFIX + ContestPhaseType.FINALIST_SELECTION.getTypeId(), "your proposal was accepted "
//                + ProposalJudgingCommentHelper.REVIEW_COMMENT_BEGIN_TAG + ProposalJudgingCommentHelper.REVIEW_COMMENT_END_TAG);
        advanceAcceptanceWinnerSelectionText = preferences.getValue(ADVANCE_ACCEPTANCE_TEXT_PREFIX + ContestPhaseType.SELECTION_OF_WINNERS.getTypeId(), "your proposal was accepted <br/><br/>"
                + ProposalJudgingCommentHelper.REVIEW_COMMENT_BEGIN_TAG + ProposalJudgingCommentHelper.REVIEW_COMMENT_END_TAG + "<br/><br/>Sincerely,<br/>The Climate CoLab Team");

        advanceRejectionSemifinalistSelectionText = preferences.getValue(ADVANCE_REJECTION_TEXT_PREFIX + ContestPhaseType.FINALIST_SELECTION.getTypeId(), "your proposal was rejected <br/><br/>"
                + ProposalJudgingCommentHelper.REVIEW_COMMENT_BEGIN_TAG + ProposalJudgingCommentHelper.REVIEW_COMMENT_END_TAG);
//        advanceRejectionFinalistSelectionText = preferences.getValue(ADVANCE_REJECTION_TEXT_PREFIX + ContestPhaseType.FINALIST_SELECTION.getTypeId(), "your proposal was rejected "
//                + ProposalJudgingCommentHelper.REVIEW_COMMENT_BEGIN_TAG + ProposalJudgingCommentHelper.REVIEW_COMMENT_END_TAG);
        advanceRejectionWinnerSelectionText = preferences.getValue(ADVANCE_REJECTION_TEXT_PREFIX + ContestPhaseType.SELECTION_OF_WINNERS.getTypeId(), "your proposal was rejected <br/><br/>"
                + ProposalJudgingCommentHelper.REVIEW_COMMENT_BEGIN_TAG + ProposalJudgingCommentHelper.REVIEW_COMMENT_END_TAG + "<br/><br/>Sincerely,<br/>The Climate CoLab Team");
    }

    public void store(PortletRequest request) throws ReadOnlyException, ValidatorException, IOException {
        PortletPreferences preferences = request.getPreferences();
        preferences.setValue(TERMS_OF_SERVICE_PREF, termsOfService);
        preferences.setValue(SCREENING_INCOMPLETE_TEXT, screeningIncompleteText);
        preferences.setValue(SCREENING_OFFTOPIC_TEXT, screeningOfftopicText);

        // TODO: enable template for invisible phase
        preferences.setValue(ADVANCE_ACCEPTANCE_TEXT_PREFIX + ContestPhaseType.FINALIST_SELECTION.getTypeId(), advanceAcceptanceSemifinalistSelectionText);
        //preferences.setValue(ADVANCE_ACCEPTANCE_TEXT_PREFIX + ContestPhaseType.FINALIST_SELECTION.getTypeId(), advanceAcceptanceSemifinalistSelectionText);
        preferences.setValue(ADVANCE_ACCEPTANCE_TEXT_PREFIX + ContestPhaseType.SELECTION_OF_WINNERS.getTypeId(), advanceAcceptanceWinnerSelectionText);

        preferences.setValue(ADVANCE_REJECTION_TEXT_PREFIX + ContestPhaseType.FINALIST_SELECTION.getTypeId(), advanceRejectionSemifinalistSelectionText);
        //preferences.setValue(ADVANCE_REJECTION_TEXT_PREFIX + ContestPhaseType.FINALIST_SELECTION.getTypeId(), advanceRejectionSemifinalistSelectionText);
        preferences.setValue(ADVANCE_REJECTION_TEXT_PREFIX + ContestPhaseType.SELECTION_OF_WINNERS.getTypeId(), advanceRejectionWinnerSelectionText);

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

    public String getAdvanceRejectionSemifinalistSelectionText() {
        return advanceRejectionSemifinalistSelectionText;
    }

    public void setAdvanceRejectionSemifinalistSelectionText(String advanceRejectionSemifinalistSelectionText) {
        this.advanceRejectionSemifinalistSelectionText = advanceRejectionSemifinalistSelectionText;
    }

    public String getAdvanceRejectionFinalistSelectionText() {
        return advanceRejectionFinalistSelectionText;
    }

    public void setAdvanceRejectionFinalistSelectionText(String advanceRejectionFinalistSelectionText) {
        this.advanceRejectionFinalistSelectionText = advanceRejectionFinalistSelectionText;
    }

    public String getAdvanceRejectionWinnerSelectionText() {
        return advanceRejectionWinnerSelectionText;
    }

    public void setAdvanceRejectionWinnerSelectionText(String advanceRejectionWinnerSelectionText) {
        this.advanceRejectionWinnerSelectionText = advanceRejectionWinnerSelectionText;
    }

    public String getAdvanceAcceptanceSemifinalistSelectionText() {
        return advanceAcceptanceSemifinalistSelectionText;
    }

    public void setAdvanceAcceptanceSemifinalistSelectionText(String advanceAcceptanceSemifinalistSelectionText) {
        this.advanceAcceptanceSemifinalistSelectionText = advanceAcceptanceSemifinalistSelectionText;
    }

    public String getAdvanceAcceptanceFinalistSelectionText() {
        return advanceAcceptanceFinalistSelectionText;
    }

    public void setAdvanceAcceptanceFinalistSelectionText(String advanceAcceptanceFinalistSelectionText) {
        this.advanceAcceptanceFinalistSelectionText = advanceAcceptanceFinalistSelectionText;
    }

    public String getAdvanceAcceptanceWinnerSelectionText() {
        return advanceAcceptanceWinnerSelectionText;
    }

    public void setAdvanceAcceptanceWinnerSelectionText(String advanceAcceptanceWinnerSelectionText) {
        this.advanceAcceptanceWinnerSelectionText = advanceAcceptanceWinnerSelectionText;
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

    public String getAdvanceRejectionText(long contestPhaseTypeId) {
        return preferences.getValue(ADVANCE_REJECTION_TEXT_PREFIX + contestPhaseTypeId, "your proposal was rejected <br/><br/>"
                + ProposalJudgingCommentHelper.REVIEW_COMMENT_BEGIN_TAG + ProposalJudgingCommentHelper.REVIEW_COMMENT_END_TAG) + "<br/><br/>Sincerely,<br/>The Climate CoLab Team";
    }

    public String getAdvanceAcceptanceText(long contestPhaseTypeId) {
        return preferences.getValue(ADVANCE_ACCEPTANCE_TEXT_PREFIX + contestPhaseTypeId, "your proposal was accepted <br/><br/>"
                + ProposalJudgingCommentHelper.REVIEW_COMMENT_BEGIN_TAG + ProposalJudgingCommentHelper.REVIEW_COMMENT_END_TAG + "<br/><br/>Sincerely,<br/>The Climate CoLab Team");
    }
}
