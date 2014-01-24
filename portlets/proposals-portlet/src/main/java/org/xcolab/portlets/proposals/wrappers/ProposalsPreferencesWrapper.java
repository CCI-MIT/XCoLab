package org.xcolab.portlets.proposals.wrappers;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

public class ProposalsPreferencesWrapper {

    private final static String TERMS_OF_SERVICE_PREF = "DEFAULT_DESCRIPTION";
    private String termsOfService;

    private final static String JUDGING_INCOMPLETE_TEXT = "JUDGING_INCOMPLETE_TEXT";
    private String judgingIncompleteText;

    private final static String JUDGINGE_REJECTION_TEXT = "JUDGINGE_REJECTION_TEXT";
    private String judgingRejectionText;

    private final static String JUDGING_ACCEPTANCE_TEXT = "JUDGING_ACCEPTANCE_TEXT";
    private String judgingAcceptanceText;

    private final static String JUDGING_OFFTOPIC_TEXT = "JUDGING_OFFTOPIC_TEXT";
    private String judgingOfftopicText;

    public final static String JUDGING_TEMPLATE_PLACEHOLDER = "{JUDGING_TEMPLATE_PLACEHOLDER}";

    public ProposalsPreferencesWrapper() {

    }

    public ProposalsPreferencesWrapper(PortletRequest request) {
        PortletPreferences preferences = request.getPreferences();
        termsOfService = preferences.getValue(TERMS_OF_SERVICE_PREF, "");
        judgingIncompleteText = preferences.getValue(JUDGING_INCOMPLETE_TEXT, "your proposal was incomplete "+JUDGING_TEMPLATE_PLACEHOLDER);
        judgingRejectionText = preferences.getValue(JUDGINGE_REJECTION_TEXT, "your proposal was rejected "+JUDGING_TEMPLATE_PLACEHOLDER);
        judgingAcceptanceText = preferences.getValue(JUDGING_ACCEPTANCE_TEXT, "your proposal was accepted "+JUDGING_TEMPLATE_PLACEHOLDER);
        judgingOfftopicText = preferences.getValue(JUDGING_OFFTOPIC_TEXT, "your proposal was offtopic "+JUDGING_TEMPLATE_PLACEHOLDER);
    }

    public void store(PortletRequest request) throws ReadOnlyException, ValidatorException, IOException {
        PortletPreferences preferences = request.getPreferences();
        preferences.setValue(TERMS_OF_SERVICE_PREF, termsOfService);
        preferences.setValue(JUDGING_INCOMPLETE_TEXT, judgingIncompleteText);
        preferences.setValue(JUDGINGE_REJECTION_TEXT, judgingRejectionText);
        preferences.setValue(JUDGING_OFFTOPIC_TEXT, judgingOfftopicText);
        preferences.setValue(JUDGING_ACCEPTANCE_TEXT, judgingAcceptanceText);
        preferences.store();
    }

    public static String replaceJudgingTemplate(String templateText, String templateReplacement) {
        return StringUtils.replace(templateText, JUDGING_TEMPLATE_PLACEHOLDER, templateReplacement);
    }

    public static String getJudgingTemplatePlaceholder() {
        return JUDGING_TEMPLATE_PLACEHOLDER;
    }

    public String getTermsOfService() {
        return termsOfService;
    }

    public void setTermsOfService(String termsOfService) {
        this.termsOfService = termsOfService;
    }

    public String getJudgingIncompleteText() {
        return judgingIncompleteText;
    }

    public void setJudgingIncompleteText(String judgingIncompleteText) {
        this.judgingIncompleteText = judgingIncompleteText;
    }

    public String getJudgingRejectionText() {
        return judgingRejectionText;
    }

    public void setJudgingRejectionText(String judgingRejectionText) {
        this.judgingRejectionText = judgingRejectionText;
    }

    public String getJudgingAcceptanceText() {
        return judgingAcceptanceText;
    }

    public void setJudgingAcceptanceText(String judgingAcceptanceText) {
        this.judgingAcceptanceText = judgingAcceptanceText;
    }

    public String getJudgingOfftopicText() {
        return judgingOfftopicText;
    }

    public void setJudgingOfftopicText(String judgingOfftopicText) {
        this.judgingOfftopicText = judgingOfftopicText;
    }
}
