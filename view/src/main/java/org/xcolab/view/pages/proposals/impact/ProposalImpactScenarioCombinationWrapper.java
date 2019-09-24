package org.xcolab.view.pages.proposals.impact;

import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.Tuple;
import edu.mit.cci.roma.client.Variable;
import edu.mit.cci.roma.client.comm.ClientRepository;
import edu.mit.cci.roma.client.comm.ModelNotFoundException;
import edu.mit.cci.roma.client.comm.ScenarioNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProposalImpactScenarioCombinationWrapper {

    private static final Map<String, Double> REGION_AVG_FACTOR;
    private static final Logger _log = LoggerFactory.getLogger(ProposalImpactScenarioCombinationWrapper.class);

    static {
        Map<String, Double> avgFactor = new HashMap<>();
        avgFactor.put("US", 0.1595);
        avgFactor.put("EU", 0.1140);
        avgFactor.put("China", 0.2834);
        avgFactor.put("India", 0.0691);
        avgFactor.put("Other developing", 0.2828);
        avgFactor.put("Other developed", 0.0912);
        REGION_AVG_FACTOR = Collections.unmodifiableMap(avgFactor);
    }

    private static final List<String> validationRegions
            = Arrays.asList("United States", "European Union", "China", "India",
            "Other developing countries", "Other developed countries");
    private static final Long ENROADS_MODEL_ID = 23L;
    private static final Long ENROADS_REGION_INPUT_ID = 814L;

    private static final Long EMF_MODEL_ID = 17L;
    private static final Long EMF_REGIONAL_MODEL_ID = 39L;
    private static final Long EMF_REGION_INPUT_ID = 812L;
    private static final Long EMF_SCENARIO_INPUT_ID = 366L;


    private final Set<Scenario> scenarios;
    private final Map<Long, Scenario> modelIdToScenarioMap;
    private final Map<String, ProposalSimulationScenarioRegionWrapper> regionToProposalSimulationScenarioMap;
    private final Set<String> presentRegion;
    private Map<Long, Object> combinedInputParametersMap;
    private Scenario combinedScenario;
    private Simulation combinedSimulation;
    private ClientRepository romaClient;


    public ProposalImpactScenarioCombinationWrapper(List<ProposalWrapper> proposals) throws IOException {
        presentRegion = new HashSet<>();
        scenarios = new HashSet<>();
        modelIdToScenarioMap = new HashMap<>();
        regionToProposalSimulationScenarioMap = new LinkedHashMap<>();
        fillProposalNameToModelScenarioRegionMap();

        for (ProposalWrapper proposal : proposals) {

            ProposalSimulationScenarioRegionWrapper simulationScenarioRegion = new ProposalSimulationScenarioRegionWrapper(proposal);
            presentRegion.add(simulationScenarioRegion.getRegion());
            Long scenarioId = proposal.getScenarioId();
            Scenario scenarioForProposal = null;
            if (scenarioId != null && scenarioId > 0) {
                try {
                    scenarioForProposal = getScenarioForScenarioId(scenarioId);
                    scenarios.add(scenarioForProposal);
                    Simulation proposalModelSimulation = scenarioForProposal.getSimulation();
                    Long modelId = proposalModelSimulation.getId();
                    modelIdToScenarioMap.put(modelId, scenarioForProposal);
                    String modelName = proposalModelSimulation.getName();
                    simulationScenarioRegion.setSimulation(modelName);
                    simulationScenarioRegion.setScenario(String.valueOf(scenarioId));
                } catch (IOException e) {
                    _log.error(String.format("Can't access scenario for id: %d", scenarioId));
                }
            }

            regionToProposalSimulationScenarioMap.put(simulationScenarioRegion.getRegion(), simulationScenarioRegion);

            if (scenarioForProposal == null) {
                modelIdToScenarioMap.put(0L, null);
            }
        }

        if (!scenarios.isEmpty()) {
            Scenario scenario = (Scenario) scenarios.toArray()[0];
            if (isModelEnRoads(scenario.getSimulation())) {
                combinedSimulation = RomaClientUtil.client().getSimulation(ENROADS_MODEL_ID);
            } else {
                combinedSimulation = RomaClientUtil.client().getSimulation(EMF_MODEL_ID);
            }
            List<Variable> combinedInputParameters = scenario.getInputSet();
        }
    }

    private void fillProposalNameToModelScenarioRegionMap() {
        for (String region : validationRegions) {
            regionToProposalSimulationScenarioMap.put(region, null);
        }
    }

    public Map<String, ProposalSimulationScenarioRegionWrapper> getRegionToProposalSimulationScenarioMap() {
        return regionToProposalSimulationScenarioMap;
    }

    private static boolean isModelEMF(Simulation simulation) {
        return simulation.getName().toLowerCase().contains("emf");
    }

    private static boolean isModelEnRoads(Simulation simulation) {
        return simulation.getName().toLowerCase().contains("enroads");
    }

    private boolean isUsedModelEMF() {
        String nameOfUsedSimulation = combinedSimulation.getName().toLowerCase();
        return nameOfUsedSimulation.contains("emf");
    }

    public Long getModelIdForScenarioId(Long scenarioId) throws IOException {
        return getScenarioForScenarioId(scenarioId).getSimulation().getId();
    }

    public Simulation getModelForScenarioId(Long scenarioId) throws IOException {
        return getScenarioForScenarioId(scenarioId).getSimulation();
    }

    public Boolean isScenarioUsingSameModelId(Long scenarioId) throws IOException {
        return getModelIdForScenarioId(scenarioId).equals(combinedSimulation.getId());
    }

    private Scenario getScenarioForScenarioId(Long scenarioId) throws IOException {
        return RomaClientUtil.client().getScenario(scenarioId);
    }

    public boolean isConsolidationOfScenariosPossible() {
        boolean isConsolidationOfScenariosPossible = false;
        if (isOneSubProposalPerRegionSelected()) {
            if (doAllScenariosUseSameModel()) {
                if (!scenarios.isEmpty()) {
                    if (isUsedModelEMF()) {
                        if (doAllEMFScenariosHaveSameModelRun()) {
                            isConsolidationOfScenariosPossible = true;
                        }
                    } else {
                        isConsolidationOfScenariosPossible = true;
                    }
                }
            }
        }
        return isConsolidationOfScenariosPossible;
    }


    private boolean isOneSubProposalPerRegionSelected() {
        boolean oneSubProposalPerRegionSelected = true;
        for (String region : validationRegions) {
            if (!presentRegion.contains(region)) {
                ProposalSimulationScenarioRegionWrapper proposalSimulationScenarioRegionWrapper = new ProposalSimulationScenarioRegionWrapper();
                proposalSimulationScenarioRegionWrapper.setRegion(region);
                proposalSimulationScenarioRegionWrapper.setSimulation("-");
                regionToProposalSimulationScenarioMap.put(region, proposalSimulationScenarioRegionWrapper);
                oneSubProposalPerRegionSelected = false;
            }
        }
        return oneSubProposalPerRegionSelected;
    }

    private boolean doAllScenariosUseSameModel() {
        boolean isMoreThanOneModelIdInMap = (modelIdToScenarioMap.size() > 1);
        return !isMoreThanOneModelIdInMap;
    }


    private boolean doAllEMFScenariosHaveSameModelRun() {
        boolean allEMFScenariosHaveSameModelRun = true;
        String commonScenarioModelRun = "";
        for (Scenario scenario : scenarios) {
            Map<Long, Object> currentScenarioInputParameters = mapVariableInputParameters(scenario.getInputSet());
            String currentScenarioModelRun = String.valueOf(currentScenarioInputParameters.get(EMF_SCENARIO_INPUT_ID));
            if (commonScenarioModelRun.isEmpty()) {
                commonScenarioModelRun = currentScenarioModelRun;
            } else {
                if (!commonScenarioModelRun.equals(currentScenarioModelRun)) {
                    allEMFScenariosHaveSameModelRun = false;
                    break;
                }
            }
        }
        return allEMFScenariosHaveSameModelRun;
    }


    public void calculateCombinedInputParameters() {
        combinedInputParametersMap = new HashMap<>();
        for (Scenario scenario : scenarios) {
            Map<Long, Object> currentScenarioInputParameters = mapVariableInputParameters(scenario.getInputSet());
            Long regionInputId = 805L;
            if (isModelEMF(scenario.getSimulation())) {
                regionInputId = EMF_REGION_INPUT_ID;
            } else if (isModelEnRoads(scenario.getSimulation())) {
                regionInputId = ENROADS_REGION_INPUT_ID;
            }
            String scenarioRegionName = (String) currentScenarioInputParameters.get(regionInputId);
            double regionFactor = REGION_AVG_FACTOR.get(scenarioRegionName) != null ? REGION_AVG_FACTOR.get(scenarioRegionName) : 1.0;

            for (Map.Entry<Long, Object> entry : currentScenarioInputParameters.entrySet()) {
                final Long inputId = entry.getKey();
                try {
                    double weightedValue = regionFactor * Double.parseDouble((String) entry.getValue());
                    if (combinedInputParametersMap.containsKey(inputId)) {
                        double currentAggregatedValue = Double.parseDouble((String) combinedInputParametersMap.get(inputId));
                        double newAggregatedValue = weightedValue + currentAggregatedValue;
                        combinedInputParametersMap.put(inputId, String.valueOf(newAggregatedValue));
                    } else {
                        combinedInputParametersMap.put(inputId, String.valueOf(weightedValue));
                    }
                } catch (NumberFormatException ignoreRegionString) {
                    if(!inputId.equals(regionInputId)){
                        combinedInputParametersMap.put(inputId, entry.getValue());
                    }
                    // This seems to be not numerical input -> region
                }
            }
        }
    }

    private static Map<Long, Object> mapVariableInputParameters(List<Variable> variableInputParameters) {
        Map<Long, Object> inputParameterMap = new HashMap<>();

        for (Variable variableInputParameter : variableInputParameters) {
            List<Tuple> scenarioInputVariableTuples = variableInputParameter.getValue();

            for (Tuple scenarioInputVariableTuple : scenarioInputVariableTuples) {
                String[] valueStrings = scenarioInputVariableTuple.getValues();

                for (String valueString : valueStrings) {
                    Long variableId = variableInputParameter.getMetaData().getId();
                    inputParameterMap.put(variableId, valueString);
                }
            }
        }
        return inputParameterMap;
    }

    public boolean scenarioInputParameterAreDifferentThanAggregated(Long scenarioId) throws IOException {
        Scenario scenario = getScenarioForScenarioId(scenarioId);
        Map<Long, Object> scenarioInputs = mapVariableInputParameters(scenario.getInputSet());
        for (Map.Entry<Long, Object> entry : scenarioInputs.entrySet()) {
            try {
                final long inputId = entry.getKey();
                final Object scenarioInput = entry.getValue();
                if (!combinedInputParametersMap.get(inputId).equals(scenarioInput)) {
                    return false;
                }
            } catch (Exception e) {
                _log.warn("Couldn't identify whether combined scenario was changed!" + e);
                return false;
            }
        }
        return true;
    }

    public void runCombinedScenarioSimulation()
            throws IOException, ScenarioNotFoundException, ModelNotFoundException {
        if (isConsolidationOfScenariosPossible()) {
            if (combinedInputParametersMap == null) {
                calculateCombinedInputParameters();
            }
            combinedScenario = RomaClientUtil.client().runModel(combinedSimulation, combinedInputParametersMap, 0L, false);
        }
    }

    public Long getOutputScenarioId() {
        if (combinedScenario != null) {
            return combinedScenario.getId();
        }
        return null;
    }

    public Long getOutputModelId() {
        if (combinedSimulation != null) {
            return combinedSimulation.getId();
        }
        return null;
    }

}
