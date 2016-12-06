package org.xcolab.portlets.proposals.wrappers;


import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.utils.emailnotification.EmailTemplateWrapper;

import java.io.IOException;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

public class ProposalsPreferencesWrapper {

    private final static String TERMS_OF_SERVICE_PREF = "TERMS_OF_SERVICE";
    private final static String CALL_TO_ACTION = "CALL_TO_ACTION";
    private final static String CONTEST_TYPE_ID = "CONTEST_TYPE_ID";

    private final static String CALL_TO_ACTION_DEFAULT = "";

    private String termsOfService;
    private String callToAction;
    private String contestTypeId;
    private ContestType contestType;

    private String proposalIdsToBeMoved;
    private long moveFromContestId;
    private long moveToContestId;

    private long ribbonId = -1;

    private EmailTemplateWrapper termsOfServiceTemplateWrapper;

    public ProposalsPreferencesWrapper() {
    }

    public ProposalsPreferencesWrapper(PortletRequest request) {
        PortletPreferences preferences = request.getPreferences();
        termsOfService = getTermsOfServiceTemplateWrapper().getHeader();
        callToAction = preferences.getValue(CALL_TO_ACTION, CALL_TO_ACTION_DEFAULT);
        contestTypeId = preferences.getValue(CONTEST_TYPE_ID, "0");

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

    public void store(PortletRequest request) throws ReadOnlyException, ValidatorException, IOException {
        PortletPreferences preferences = request.getPreferences();
        ContestEmailTemplate template = EmailTemplateClientUtil.getContestEmailTemplateByType(TERMS_OF_SERVICE_PREF);
        template.setHeader(termsOfService);
        EmailTemplateClientUtil.updateContestEmailTemplate(template);

        preferences.setValue(CALL_TO_ACTION, callToAction);
        preferences.setValue(CONTEST_TYPE_ID, contestTypeId);
        preferences.store();
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
