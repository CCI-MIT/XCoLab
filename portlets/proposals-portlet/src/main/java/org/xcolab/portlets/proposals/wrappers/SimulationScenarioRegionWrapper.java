package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.Contest;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * Created by Thomas on 7/2/2015.
 */
public class SimulationScenarioRegionWrapper {
    private String simulation = "No simulation available";
    private String scenario = "-";
    private String region = "";

    public SimulationScenarioRegionWrapper(String simulation, String region) {
        this.simulation = simulation;
        this.region = region;
    }

    public SimulationScenarioRegionWrapper(ProposalWrapper proposalWrapper) throws SystemException, PortalException{
        Contest contestForProposal = proposalWrapper.getContest();
        ContestWrapper contestWrapper = new ContestWrapper(contestForProposal);
        this.region = contestWrapper.getWhereName();
    }

    public SimulationScenarioRegionWrapper() {
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

}
