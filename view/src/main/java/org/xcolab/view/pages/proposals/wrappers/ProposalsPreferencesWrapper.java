package org.xcolab.view.pages.proposals.wrappers;


import org.json.JSONObject;

import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.entity.utils.email.notifications.EmailTemplateWrapper;

import java.io.IOException;


import javax.servlet.http.HttpServletRequest;


public class ProposalsPreferencesWrapper {

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

    public ProposalsPreferencesWrapper() {
    }

    public ProposalsPreferencesWrapper(HttpServletRequest request) {

        JSONObject preferences = new JSONObject(ConfigurationAttributeKey.PORTLET_PROPOSALS_PREFERENCES.get());

        termsOfService = getTermsOfServiceTemplateWrapper().getHeader();
        callToAction = (preferences.has(CALL_TO_ACTION))?(preferences.getString(CALL_TO_ACTION)):(CALL_TO_ACTION_DEFAULT);
        contestTypeId = (preferences.has(CONTEST_TYPE_ID))?(preferences.getString(CONTEST_TYPE_ID)):("0");

        contestType = ContestClientUtil.getContestType(Long.parseLong(contestTypeId));

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
        JSONObject prefs = new JSONObject();
        ContestEmailTemplate template = EmailTemplateClientUtil.getContestEmailTemplateByType(TERMS_OF_SERVICE_PREF);
        template.setHeader(termsOfService);
        EmailTemplateClientUtil.updateContestEmailTemplate(template);

        prefs.put(CALL_TO_ACTION, callToAction);
        prefs.put(CONTEST_TYPE_ID, contestTypeId);


        ConfigurationAttribute configurationAttribute = new ConfigurationAttribute();
        configurationAttribute.setName(ConfigurationAttributeKey.PORTLET_PROPOSALS_PREFERENCES.name());
        configurationAttribute.setStringValue(prefs.toString());
        AdminClient.updateConfigurationAttribute(configurationAttribute);
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
