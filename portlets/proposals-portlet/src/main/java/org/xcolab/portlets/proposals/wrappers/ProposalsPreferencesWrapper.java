package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.ContestEmailTemplate;
import com.ext.portlet.service.ContestEmailTemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.exception.PortalException;
import org.xcolab.utils.judging.EmailTemplateWrapper;

import java.io.IOException;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

public class ProposalsPreferencesWrapper {

    private final static String TERMS_OF_SERVICE_PREF = "TERMS_OF_SERVICE";
    private final static String CALL_TO_ACTION = "CALL_TO_ACTION";

    private final static String CALL_TO_ACTION_DEFAULT = "The public voting period is open until September 12!" +
            "<a href=\"/community/-/blogs/finalists-selected-vote-to-select-popular-choice-winner-2\">" +
            " See a list of all contest Finalists and how to vote.</a>";

    private String termsOfService;
    private String callToAction;

    private String proposalIdsToBeMoved;
    private long moveFromContestId;
    private long moveToContestPhaseId;

    private long ribbonId = -1;

    private EmailTemplateWrapper termsOfServiceTemplateWrapper;

    public ProposalsPreferencesWrapper() {

    }

    public ProposalsPreferencesWrapper(PortletRequest request) {
        PortletPreferences preferences = request.getPreferences();
        try {
            termsOfService = getTermsOfServiceTemplateWrapper().getHeader();
        } catch(SystemException | PortalException e) {
            //should never happen
            throw new RuntimeException(e);
        }
        callToAction = preferences.getValue(CALL_TO_ACTION, CALL_TO_ACTION_DEFAULT);
        proposalIdsToBeMoved = "";
        moveFromContestId = -1;
        moveToContestPhaseId = -1;
    }

    private EmailTemplateWrapper getTermsOfServiceTemplateWrapper() {
        if (termsOfServiceTemplateWrapper != null) {
            return termsOfServiceTemplateWrapper;
        }

        try {
            termsOfServiceTemplateWrapper = new EmailTemplateWrapper(
                    ContestEmailTemplateLocalServiceUtil.getEmailTemplateByType(TERMS_OF_SERVICE_PREF),
                    "",
                    ""
            );
        } catch (SystemException e) {
            e.printStackTrace();
        }

        return termsOfServiceTemplateWrapper;
    }

    public void store(PortletRequest request) throws ReadOnlyException, ValidatorException, IOException {
        PortletPreferences preferences = request.getPreferences();
        try {
            ContestEmailTemplate template = ContestEmailTemplateLocalServiceUtil.getEmailTemplateByType(TERMS_OF_SERVICE_PREF);
            template.setHeader(termsOfService);
            ContestEmailTemplateLocalServiceUtil.updateContestEmailTemplate(template);

        } catch (SystemException e) {
            e.printStackTrace();
        }
        preferences.setValue(CALL_TO_ACTION, callToAction);
        preferences.store();
    }

    public long getRibbonId() {
        return ribbonId;
    }

    public void setRibbonId(long ribbonId) {
        this.ribbonId = ribbonId;
    }

    public long getMoveToContestPhaseId() {
        return moveToContestPhaseId;
    }

    public void setMoveToContestPhaseId(long moveToContestPhaseId) {
        this.moveToContestPhaseId = moveToContestPhaseId;
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
}
