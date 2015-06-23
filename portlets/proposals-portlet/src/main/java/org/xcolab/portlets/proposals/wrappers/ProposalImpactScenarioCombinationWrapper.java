package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.models.CollaboratoriumModelingService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.Variable;
import edu.mit.cci.roma.client.Tuple;
import org.xcolab.portlets.proposals.utils.RegionClimateImpact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Thomas on 6/22/2015.
 */
public class ProposalImpactScenarioCombinationWrapper {
    Set<Scenario> scenarios;

    Map<Long, Scenario> modelIdToScenarioMap;
    Map<ProposalWrapper, Simulation> proposalToModelMap;
    Map<Scenario, Proposal> scenariosToProposalMap;

    Set<Variable> combinedInputParameters;
    Scenario combinedScenario;
    Simulation usedSimulation;

    public ProposalImpactScenarioCombinationWrapper(List<Proposal> proposals) throws Exception{
        scenarios = new HashSet<>();
        scenariosToProposalMap = new HashMap<>();
        modelIdToScenarioMap = new HashMap<>();
        proposalToModelMap = new HashMap<>();

        for(Proposal proposal : proposals) {
            Scenario scenarioForProposal = CollaboratoriumModelingService.repository().getScenario(proposal.getProposalId());
            scenarios.add(scenarioForProposal);
            scenariosToProposalMap.put(scenarioForProposal, proposal);
            Simulation proposalModelSimulation = scenarioForProposal.getSimulation();
            Long modelId = proposalModelSimulation.getId();
            modelIdToScenarioMap.put(modelId, scenarioForProposal);
            proposalToModelMap.put(new ProposalWrapper(proposal), proposalModelSimulation);
        }

        if(scenarios.size() > 0) {
            Scenario scenario = (Scenario) scenarios.toArray()[0];
            usedSimulation = scenario.getSimulation();
        }
    }

    public Map<ProposalWrapper, Simulation> getProposalToModelMap() {
        return proposalToModelMap;
    }

    private boolean isUsedModelEMF(){
        String nameOfUsedSimulation = usedSimulation.getName().toLowerCase();
        return nameOfUsedSimulation.contains("emf");
    }

    public boolean isConsolidationOfScenariosPossible() {
        boolean isConsolidationOfScenariosPossible = false;
        if (doAllScenariosUseSameModel()) {
            if(isUsedModelEMF()){
                if(scenarios.size() == 1){
                    isConsolidationOfScenariosPossible = true;
                }
            } else{
                isConsolidationOfScenariosPossible = true;
            }
        }
        return isConsolidationOfScenariosPossible;
    }

    public boolean doAllScenariosUseSameModel(){
        boolean isMoreThanOneModelIdInMap = (modelIdToScenarioMap.size() > 1);
        return isMoreThanOneModelIdInMap;
    }

    public void calculateCombinedInputParameters() {

        for(Scenario scenario : scenarios) {
            String scenarioRegionName = "US";// TODO replace with proper callscenario.getDescription();
            double regionFactor = RegionClimateImpact.valueOf(scenarioRegionName).getRegionClimateImpactFactor();

            for(Variable scenarioInputVariable : scenario.getInputSet()){
                if(!combinedInputParameters.contains(scenarioInputVariable)){

                    for(Tuple scenarioInputVariableTuple : scenarioInputVariable.getValue()){
                        String[] test = scenarioInputVariableTuple.getValues();
                    }
                    //double weightedValue = scenarioInputVariable.
                    combinedInputParameters.add(scenarioInputVariable);

                } else {

                }
            }
        }
    }

    public void runCombinedScenarioSimuation() throws PortalException, SystemException, IOException{
       if(isConsolidationOfScenariosPossible()){
           if(isUsedModelEMF()){
               combinedScenario = (Scenario) scenarios.toArray()[0];
           } else{
               //combinedScenario = CollaboratoriumModelingService.repository().
               //        runModel(usedSimulation, combinedInputParameters, null, true);
           }
       }
    }

    public Long getOutputScenarioId(){
      if(combinedScenario != null){
          return combinedScenario.getId();
        }
        else return null;
    }

}
