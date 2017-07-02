package org.xcolab.view.pages.proposals.wrappers;


import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.entity.utils.WidgetPreference;
import org.xcolab.entity.utils.notifications.EmailTemplateWrapper;
import org.xcolab.util.attributes.AttributeGetter;

import java.io.IOException;


public class ProposalsPreferencesWrapper extends WidgetPreference {

    private final static String TERMS_OF_SERVICE_PREF = "TERMS_OF_SERVICE";
    private final static String CALL_TO_ACTION = "CALL_TO_ACTION";
    private final static String CONTEST_TYPE_ID = "CONTEST_TYPE_ID";

    private final static String CALL_TO_ACTION_DEFAULT = "";

    private String termsOfService;
    private String callToAction;
    private String contestTypeId;
    private ContestType contestType;

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
        this(null);
    }
    public ProposalsPreferencesWrapper(String preferenceId) {
        super(preferenceId);



        termsOfService = getTermsOfServiceTemplateWrapper().getHeader();
        callToAction = (prefs.has(CALL_TO_ACTION))?(prefs.getString(CALL_TO_ACTION)):(CALL_TO_ACTION_DEFAULT);
        contestTypeId = (prefs.has(CONTEST_TYPE_ID))?(prefs.getString(CONTEST_TYPE_ID)):("0");

        contestType = ContestTypeClient.getContestType(Long.parseLong(contestTypeId));

        proposalIdsToBeMoved = "";
        moveFromContestId = -1;
        moveToContestId = -1;
    }

    private EmailTemplateWrapper getTermsOfServiceTemplateWrapper() {
        if (termsOfServiceTemplateWrapper != null) {
            return termsOfServiceTemplateWrapper;
        }
        termsOfServiceTemplateWrapper = new EmailTemplateWrapper(
                EmailTemplateClientUtil.getContestEmailTemplateByType(TERMS_OF_SERVICE_PREF), "", "");

        return termsOfServiceTemplateWrapper;
    }

    public void store() throws  IOException {

        ContestEmailTemplate template = EmailTemplateClientUtil.getContestEmailTemplateByType(TERMS_OF_SERVICE_PREF);
        template.setHeader(termsOfService);
        EmailTemplateClientUtil.updateContestEmailTemplate(template);

        prefs.put(CALL_TO_ACTION, callToAction);
        prefs.put(CONTEST_TYPE_ID, contestTypeId);


        savePreferences(prefs,preferenceId);
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
}
