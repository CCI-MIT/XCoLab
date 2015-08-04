package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.Contest;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * Created by Thomas on 7/2/2015.
 */
public class ProposalSimulationScenarioRegionWrapper {
    private ProposalWrapper proposalWrapper;
    private String simulation = "No model selected";
    private String scenario = "-";
    private String region = "";

    public ProposalSimulationScenarioRegionWrapper(String simulation, String region) {
        this.simulation = simulation;
        this.region = region;
    }

    public ProposalSimulationScenarioRegionWrapper(ProposalWrapper proposalWrapper) throws SystemException, PortalException{
        this.proposalWrapper = proposalWrapper;
        Contest contestForProposal = proposalWrapper.getContest();
        ContestWrapper contestWrapper = new ContestWrapper(contestForProposal);
        this.region = contestWrapper.getWhereName();
    }

    public ProposalSimulationScenarioRegionWrapper() {
    }

    public String getSimulation() {
        return simulation;
    }

    public String getScenario() {
        return scenario;
    }

    public String getRegion() {
        return region;
    }

    public void setSimulation(String simulation) {
        this.simulation = simulation;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setRegion(Contest contest) throws SystemException, PortalException{
        ContestWrapper contestWrapper = new ContestWrapper(contest);
        this.region = contestWrapper.getWhereName();
    }


    public void setProposalWrapper(ProposalWrapper proposalWrapper) {
        this.proposalWrapper = proposalWrapper;
    }

    public String getProposalUrl() {
        if(proposalWrapper != null) {
            return proposalWrapper.getProposalURL();
        } else {
            return "";
        }
    }

}
