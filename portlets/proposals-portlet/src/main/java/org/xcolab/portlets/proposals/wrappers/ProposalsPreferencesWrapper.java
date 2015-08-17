package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.ContestEmailTemplate;
import com.ext.portlet.service.ContestEmailTemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.utils.judging.EmailTemplateWrapper;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

public class ProposalsPreferencesWrapper {

    private final static String TERMS_OF_SERVICE_PREF = "TERMS_OF_SERVICE";

    private PortletPreferences preferences;

    private String termsOfService;

    private String proposalIdsToBeMoved;
    private long moveFromContestId;
    private long moveToContestPhaseId;

    private long ribbonId = -1;

    private EmailTemplateWrapper termsOfServiceTemplateWrapper;

    public ProposalsPreferencesWrapper() {

    }

    public ProposalsPreferencesWrapper(PortletRequest request) {
        this.preferences = request.getPreferences();
        try {
            termsOfService = getTermsOfServiceTemplateWrapper().getHeader();
        } catch(SystemException | PortletException e) {
            //should never happen
            throw new RuntimeException(e);
        }
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
}
