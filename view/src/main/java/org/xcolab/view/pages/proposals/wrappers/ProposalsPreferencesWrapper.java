package org.xcolab.view.pages.proposals.wrappers;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.admin.pojo.IEmailTemplate;
import org.xcolab.commons.attributes.AttributeGetter;
import org.xcolab.entity.utils.notifications.EmailTemplateWrapper;
import org.xcolab.util.i18n.I18nUtils;
import org.xcolab.view.widgets.WidgetPreference;

public class ProposalsPreferencesWrapper extends WidgetPreference {

    private static final String TERMS_OF_SERVICE_PREF = "TERMS_OF_SERVICE";
    private static final String CALL_TO_ACTION = "CALL_TO_ACTION";
    private static final String CONTEST_TYPE_ID = "CONTEST_TYPE_ID";

    private static final String CALL_TO_ACTION_DEFAULT = "";

    private String termsOfService;
    private String callToAction;
    private String contestTypeId;
    private final ContestType contestType;

    private String title;
    private String allContestsUrl;
    private String allContestsTitle;

    private String proposalIdsToBeMoved;
    private long moveFromContestId;
    private long moveToContestId;

    private long ribbonId = -1;

    private EmailTemplateWrapper termsOfServiceTemplateWrapper;

    @Override
    public AttributeGetter<String> getConfigurationAttribute() {
        return ConfigurationAttributeKey.PORTLET_PROPOSALS_PREFERENCES;
    }

    public ProposalsPreferencesWrapper() {
        this(null, I18nUtils.DEFAULT_LANGUAGE);
    }

    public ProposalsPreferencesWrapper(String preferenceId, String language) {
        super(preferenceId,language);

        termsOfService = getTermsOfServiceTemplateWrapper().getHeader();
        callToAction = (jsonPreferences.has(CALL_TO_ACTION))?(jsonPreferences.getString(CALL_TO_ACTION)):(CALL_TO_ACTION_DEFAULT);
        contestTypeId = (jsonPreferences.has(CONTEST_TYPE_ID))?(jsonPreferences.getString(CONTEST_TYPE_ID)):("0");

        contestType = StaticAdminContext.getContestTypeClient().getContestType(Long.parseLong(contestTypeId));

        proposalIdsToBeMoved = "";
        moveFromContestId = -1;
        moveToContestId = -1;
    }

    private EmailTemplateWrapper getTermsOfServiceTemplateWrapper() {
        if (termsOfServiceTemplateWrapper != null) {
            return termsOfServiceTemplateWrapper;
        }
        termsOfServiceTemplateWrapper = new EmailTemplateWrapper(
                StaticAdminContext.getEmailTemplateClient().getEmailTemplate(TERMS_OF_SERVICE_PREF), "", "");

        return termsOfServiceTemplateWrapper;
    }

    @Override
    public void savePreferences() {
        IEmailTemplate template = StaticAdminContext.getEmailTemplateClient()
                .getEmailTemplate(TERMS_OF_SERVICE_PREF);
        template.setHeader(termsOfService);
        StaticAdminContext.getEmailTemplateClient().updateEmailTemplate(template);

        jsonPreferences.put(CALL_TO_ACTION, callToAction);
        jsonPreferences.put(CONTEST_TYPE_ID, contestTypeId);


        savePreferencesInternal(jsonPreferences,preferenceId);
    }

    public String getTitle(){
        return title;
    }
    public String getAllContestsUrl(){
        return allContestsUrl;
    }
    public String getAllContestsTitle(){
        return allContestsTitle;
    }
    public long getRibbonId() {
        return ribbonId;
    }

    public void setRibbonId(long ribbonId) {
        this.ribbonId = ribbonId;
    }

    public long getMoveToContestId() {
        return moveToContestId;
    }

    public void setMoveToContestId(long moveToContestId) {
        this.moveToContestId = moveToContestId;
    }

    public String getProposalIdsToBeMoved() {
        return proposalIdsToBeMoved;
    }

    public void setProposalIdsToBeMoved(String proposalIdsToBeMoved) {
        this.proposalIdsToBeMoved = proposalIdsToBeMoved;
    }

    public long getMoveFromContestId() {
        return moveFromContestId;
    }

    public void setMoveFromContestId(long moveFromContestId) {
        this.moveFromContestId = moveFromContestId;
    }

    public String getTermsOfService() {
        return termsOfService;
    }

    public void setTermsOfService(String termsOfService) {
        this.termsOfService = termsOfService;
    }

    public String getCallToAction() {
        return callToAction;
    }

    public void setCallToAction(String callToAction) {
        this.callToAction = callToAction;
    }

    public String getContestTypeId() {
        return contestTypeId;
    }

    public void setContestTypeId(String contestTypeId) {
        this.contestTypeId = contestTypeId;
    }

    public ContestType getContestType() {
        return contestType;
    }

    public ContestType getContestType(String language) {
        return contestType.withLocale(language);
    }
}
