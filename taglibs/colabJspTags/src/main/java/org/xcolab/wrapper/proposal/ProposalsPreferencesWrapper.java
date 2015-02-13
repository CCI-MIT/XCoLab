package org.xcolab.wrapper.proposal;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;
import java.io.IOException;

public class ProposalsPreferencesWrapper {

    private final static String TERMS_OF_SERVICE_PREF = "DEFAULT_DESCRIPTION";

    private PortletPreferences preferences;

    private String termsOfService;

    private String proposalIdsToBeMoved;
    private long moveFromContestId;
    private long moveToContestPhaseId;

    private long ribbonId = -1;

    public ProposalsPreferencesWrapper() {

    }

    public ProposalsPreferencesWrapper(PortletRequest request) {
        this.preferences = request.getPreferences();
        termsOfService = preferences.getValue(TERMS_OF_SERVICE_PREF, "");
        proposalIdsToBeMoved = "";
        moveFromContestId = -1;
        moveToContestPhaseId = -1;
    }

    public void store(PortletRequest request) throws ReadOnlyException, ValidatorException, IOException {
        PortletPreferences preferences = request.getPreferences();
        preferences.setValue(TERMS_OF_SERVICE_PREF, termsOfService);
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
