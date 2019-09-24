package org.xcolab.view.pages.proposals.impact;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;

public class ProposalSimulationScenarioRegionWrapper {

    private ProposalWrapper proposal;
    private String simulation = "No model selected";
    private String scenario = "-";
    private String region = "";
    private String proposalName = "No proposal selected for this region";

    public ProposalSimulationScenarioRegionWrapper(ProposalWrapper proposal) {
        this.proposal = proposal;
        proposalName = proposal.getName();
        ContestWrapper contestForProposal = proposal.getContest();
        this.region = contestForProposal.getWhereName();
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

    public void setRegion(ContestWrapper contest) {
            this.region = contest.getWhereName();
    }

    public String getProposalUrl() {
        if (proposal != null) {
            return proposal.getProposalUrl();
        } else {
            return "";
        }
    }

    public String getProposalName() {
        return this.proposalName;
    }
}
