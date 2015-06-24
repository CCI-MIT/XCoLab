package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.Proposal;
import com.ext.portlet.models.CollaboratoriumModelingService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.Variable;
import edu.mit.cci.roma.client.Tuple;
import edu.mit.cci.roma.client.comm.ClientRepository;
import edu.mit.cci.roma.client.comm.ModelNotFoundException;
import edu.mit.cci.roma.client.comm.ScenarioNotFoundException;
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

    List<Variable> combinedInputParameters;
    Map<Long, Object> combinedInputParametersMap;
    Scenario combinedScenario;
    Simulation usedSimulation;
    ClientRepository romaClient;


    public ProposalImpactScenarioCombinationWrapper(List<Proposal> proposals) throws Exception{
        initRomaClient();
        scenarios = new HashSet<>();
        scenariosToProposalMap = new HashMap<>();
        modelIdToScenarioMap = new HashMap<>();
        proposalToModelMap = new HashMap<>();

        for(Proposal proposal : proposals) {
            //Long dummyScenarioId = 11621L;
            ProposalWrapper proposalWrapper = new ProposalWrapper(proposal);
            Long scenarioId = proposalWrapper.getScenarioId();
            Scenario scenarioForProposal = getScenarioForScenarioId(scenarioId);
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
            combinedInputParameters = scenario.getInputSet();
        }
    }

    public Map<ProposalWrapper, Simulation> getProposalToModelMap() {
        return proposalToModelMap;
    }

    private boolean isUsedModelEMF(){
        String nameOfUsedSimulation = usedSimulation.getName().toLowerCase();
        return nameOfUsedSimulation.contains("emf");
    }

    public Long getModelIdForScenarioId(Long scenarioId)throws Exception{
        return getScenarioForScenarioId(scenarioId).getSimulation().getId();
    }

    private ClientRepository getRomaClient(){
        if(romaClient == null) {
            initRomaClient();
        }
        return romaClient;
    }

    private void initRomaClient(){
        try{
            romaClient = CollaboratoriumModelingService.repository();
        } catch (Exception e){
            // TODO check why that fails
        }
    }

    public Scenario getScenarioForScenarioId(Long scenarioId) throws Exception{
        return getRomaClient().getScenario(scenarioId);
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
        return !isMoreThanOneModelIdInMap;
    }

    public void calculateCombinedInputParameters() {

        int scenarioCount = 0;
        combinedInputParametersMap = new HashMap<>();
        for(Scenario scenario : scenarios) {
            String scenarioRegionName = "US";// TODO replace with proper callscenario.getDescription();
            double regionFactor = RegionClimateImpact.valueOf(scenarioRegionName).getRegionClimateImpactFactor();
            List<Variable> scenarioInputSet = scenario.getInputSet();

            for (int scenarioInputSetIndex = 0; scenarioInputSetIndex < scenarioInputSet.size(); scenarioInputSetIndex++) {
                List<Tuple> scenarioInputVariableTuples = scenarioInputSet.get(scenarioInputSetIndex).getValue();
                List<Tuple> aggregatedScenarioInputVariableTuples = combinedInputParameters.get(scenarioInputSetIndex).getValue();

                for (int scenarioInputVariableTuplesIndex = 0; scenarioInputVariableTuplesIndex < scenarioInputVariableTuples.size(); scenarioInputVariableTuplesIndex++) {
                    String[] valueStrings = scenarioInputVariableTuples.get(scenarioInputVariableTuplesIndex).getValues();
                    String[] aggregatedValueStrings = aggregatedScenarioInputVariableTuples.get(scenarioInputVariableTuplesIndex).getValues();

                    for (int valueStringsIndex = 0; valueStringsIndex < valueStrings.length; valueStringsIndex++) {
                        double weightedValue = regionFactor * Double.parseDouble(valueStrings[valueStringsIndex]);
                        double newAggregatedValue;
                        if (scenarioCount == 0) {
                            newAggregatedValue = weightedValue;
                        } else {
                            double currentAggregatedValue = Double.parseDouble(aggregatedValueStrings[valueStringsIndex]);
                            newAggregatedValue = weightedValue + currentAggregatedValue;
                        }
                        aggregatedValueStrings[valueStringsIndex] = String.valueOf(newAggregatedValue);
                        Long variableId = scenarioInputSet.get(scenarioInputSetIndex).getMetaData().getId();
                        combinedInputParametersMap.put(variableId, String.valueOf(newAggregatedValue));
                    }
                }
            }
            scenarioCount++;
        }
    }

    public boolean scenarioInputParameterAreDifferentThanAggeragted(Long scenarioId) throws Exception{
        Scenario scenario = getScenarioForScenarioId(scenarioId);
        List<Variable> scenarioInputParameters = scenario.getInputSet();

        for (int scenarioInputSetIndex = 0; scenarioInputSetIndex < scenarioInputParameters.size(); scenarioInputSetIndex++) {
            List<Tuple> scenarioInputVariableTuples = scenarioInputParameters.get(scenarioInputSetIndex).getValue();
            List<Tuple> aggregatedScenarioInputVariableTuples = combinedInputParameters.get(scenarioInputSetIndex).getValue();

            for (int scenarioInputVariableTuplesIndex = 0; scenarioInputVariableTuplesIndex < scenarioInputVariableTuples.size(); scenarioInputVariableTuplesIndex++) {
                String[] valueStrings = scenarioInputVariableTuples.get(scenarioInputVariableTuplesIndex).getValues();
                String[] aggregatedValueStrings = aggregatedScenarioInputVariableTuples.get(scenarioInputVariableTuplesIndex).getValues();

                for (int valueStringsIndex = 0; valueStringsIndex < valueStrings.length; valueStringsIndex++) {
                    if(!aggregatedValueStrings[valueStringsIndex].equals(valueStrings[valueStringsIndex])){
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void runCombinedScenarioSimulation() throws PortalException, SystemException, IOException, ScenarioNotFoundException, ModelNotFoundException{
       if(isConsolidationOfScenariosPossible()){
           if(isUsedModelEMF()){
               combinedScenario = (Scenario) scenarios.toArray()[0];
           } else{
               combinedScenario = getRomaClient().runModel(usedSimulation,combinedInputParametersMap, 0L, false);
           }
       }
    }

    public Long getOutputScenarioId(){
      if(combinedScenario != null){
          return combinedScenario.getId();
        }
        else return null;
    }
    public Long getOutputModelId(){
      if(usedSimulation != null){
          return usedSimulation.getId();
        }
        else return null;
    }

}
