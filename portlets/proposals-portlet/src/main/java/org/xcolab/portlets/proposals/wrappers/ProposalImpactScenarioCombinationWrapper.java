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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Thomas on 6/22/2015.
 */
public class ProposalImpactScenarioCombinationWrapper {

    private static  Map<Long, String> INPUT_ID_REGION;

    static {
        Map<Long, String> aMap =  new HashMap<>();
        aMap.put(814L, "EMF");
        aMap.put(805L, "ENROADS");
        INPUT_ID_REGION = Collections.unmodifiableMap(aMap);
    }

    private final static Long EMF_REGION_INPUT_ID  = 814L;
    private final static Long ENROADS_REGION_INPUT_ID  = 805L;
    private final static Long ENROADS_MODEL_ID  = 23L;
    private final static Long EMF_MODEL_ID  = 17L;
    private final static Long MODEL_ID_COMBINED_SCENARIOS  =  ENROADS_MODEL_ID; //41L;

    Set<Scenario> scenarios;
    Map<Long, Scenario> modelIdToScenarioMap;
    Map<ProposalWrapper, Simulation> proposalToModelMap;
    Map<Scenario, Proposal> scenariosToProposalMap;

    List<Variable> combinedInputParameters;
    Map<Long, Object> combinedInputParametersMap;
    Scenario combinedScenario;
    Simulation combinedSimulation;
    ClientRepository romaClient;


    public ProposalImpactScenarioCombinationWrapper(List<Proposal> proposals) throws Exception{
        initRomaClient();
        scenarios = new HashSet<>();
        scenariosToProposalMap = new HashMap<>();
        modelIdToScenarioMap = new HashMap<>();
        proposalToModelMap = new HashMap<>();

        for(Proposal proposal : proposals) {
            ProposalWrapper proposalWrapper = new ProposalWrapper(proposal);
            Long scenarioId = proposalWrapper.getScenarioId();
            //Long scenarioId = 11621L;
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
            if(isModelEnRoads(scenario.getSimulation())){
                combinedSimulation = getRomaClient().getSimulation(ENROADS_MODEL_ID);
            } else{
                combinedSimulation = getRomaClient().getSimulation(EMF_MODEL_ID);
            }
            combinedInputParameters = scenario.getInputSet();
        }
    }

    public Map<ProposalWrapper, Simulation> getProposalToModelMap() {
        return proposalToModelMap;
    }

    private static boolean isModelEMF(Simulation simulation){
        return simulation.getName().toLowerCase().contains("emf");
    }
    private static boolean isModelEnRoads(Simulation simulation){
        return simulation.getName().toLowerCase().contains("enroads");
    }
    private boolean isUsedModelEMF(){
        String nameOfUsedSimulation = combinedSimulation.getName().toLowerCase();
        return nameOfUsedSimulation.contains("emf");
    }

    public Long getModelIdForScenarioId(Long scenarioId)throws Exception{
        return getScenarioForScenarioId(scenarioId).getSimulation().getId();
    }

    public Simulation getModelForScenarioId(Long scenarioId)throws Exception{
        return getScenarioForScenarioId(scenarioId).getSimulation();
    }

    public boolean isCombinedScenario(Long scenarioId) throws Exception{
        Long modelId = getModelIdForScenarioId(scenarioId);
        return (MODEL_ID_COMBINED_SCENARIOS == modelId);
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
            // TODO implement: Wait for roma Client Thread to be stared!
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
        combinedInputParametersMap = new HashMap<>();
        for(Scenario scenario : scenarios) {
            Map<Long, Object> currentScenarioInputParameters = mapVariableInputParameters(scenario.getInputSet());
            Long regionInputId = 805L;
            if (isModelEMF(scenario.getSimulation())) {
                regionInputId = EMF_REGION_INPUT_ID;
            } else if (isModelEnRoads(scenario.getSimulation())) {
                regionInputId = ENROADS_REGION_INPUT_ID;
            }
            String scenarioRegionName = (String) currentScenarioInputParameters.get(regionInputId);
            double regionFactor = RegionClimateImpact.valueOf(scenarioRegionName).getRegionClimateImpactFactor();

            for (Long inputId : currentScenarioInputParameters.keySet()) {
                try{
                    double weightedValue = regionFactor * Double.parseDouble((String) currentScenarioInputParameters.get(inputId));
                    if (combinedInputParametersMap.containsKey(inputId)) {
                        double currentAggregatedValue = Double.parseDouble((String) combinedInputParametersMap.get(inputId));
                        double newAggregatedValue = weightedValue + currentAggregatedValue;
                        combinedInputParametersMap.put(inputId, String.valueOf(newAggregatedValue));
                    } else {
                        combinedInputParametersMap.put(inputId, String.valueOf(weightedValue));
                    }
                } catch (NumberFormatException e){
                        // This seems to be not numerical input
                    combinedInputParametersMap.put(inputId, currentScenarioInputParameters.get(inputId));
                }
            }
        }
    }

    private static Map<Long, Object> mapVariableInputParameters(List<Variable> variableInputParameters){
        Map<Long, Object> inputParameterMap = new HashMap<>();

        for (int scenarioInputSetIndex = 0; scenarioInputSetIndex < variableInputParameters.size(); scenarioInputSetIndex++) {
            List<Tuple> scenarioInputVariableTuples = variableInputParameters.get(scenarioInputSetIndex).getValue();

            for (int scenarioInputVariableTuplesIndex = 0; scenarioInputVariableTuplesIndex < scenarioInputVariableTuples.size(); scenarioInputVariableTuplesIndex++) {
                String[] valueStrings = scenarioInputVariableTuples.get(scenarioInputVariableTuplesIndex).getValues();

                for (int valueStringsIndex = 0; valueStringsIndex < valueStrings.length; valueStringsIndex++) {
                    Long variableId = variableInputParameters.get(scenarioInputSetIndex).getMetaData().getId();
                    inputParameterMap.put(variableId, valueStrings[valueStringsIndex]);
                }
            }
        }
        return inputParameterMap;
    }

    public boolean scenarioInputParameterAreDifferentThanAggregated(Long scenarioId) throws Exception{
        Scenario scenario = getScenarioForScenarioId(scenarioId);
        Map<Long, Object> scenarioInputs = mapVariableInputParameters(scenario.getInputSet());
        for( Long inputId : scenarioInputs.keySet()){
            if(!combinedInputParametersMap.get(inputId).equals(scenarioInputs.get(inputId))){
                return false;
            }
        }
        return true;
    }

    public void runCombinedScenarioSimulation() throws PortalException, SystemException, IOException, ScenarioNotFoundException, ModelNotFoundException{
       if(isConsolidationOfScenariosPossible()){
           if(isUsedModelEMF()){
               combinedScenario = (Scenario) scenarios.toArray()[0];
           } else{
               combinedScenario = getRomaClient().runModel(combinedSimulation, combinedInputParametersMap, 0L, false);
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
      if(combinedSimulation != null){
          return combinedSimulation.getId();
        }
        else return null;
    }

}
