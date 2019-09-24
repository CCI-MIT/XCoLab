package org.xcolab.view.pages.proposals.view.proposal.json;

import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.Variable;
import edu.mit.cci.roma.client.comm.ModelNotFoundException;
import edu.mit.cci.roma.client.comm.ScenarioNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.modeling.IModelingClient;
import org.xcolab.client.modeling.models.ui.IllegalUIConfigurationException;
import org.xcolab.client.modeling.models.ui.ModelDisplay;
import org.xcolab.client.modeling.models.ui.ModelInputDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelOutputDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelUIFactory;
import org.xcolab.client.modeling.pojo.IModelGlobalPreference;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.commons.exceptions.InternalException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/modeling")
public class ModelingJsonController {

    private static final Logger _log = LoggerFactory.getLogger(ModelingJsonController.class);

    private final IModelingClient modelingClient;

    @Autowired
    public ModelingJsonController(IModelingClient modelingClient) {
        this.modelingClient = modelingClient;
    }

    @GetMapping("scenarios/{scenarioId}")
    public void getScenario(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long scenarioId) throws IOException {
        JsonObject scenarioJson = Json.createObjectBuilder().build();
        try {
            Scenario scenario = RomaClientUtil.client().getScenario(scenarioId);
            scenarioJson = convertScenario(scenario);
        } catch (IOException | IllegalUIConfigurationException e) {
            _log.error("", e);
        }
        response.getOutputStream().write(scenarioJson.toString().getBytes());
    }

    @GetMapping("models/{modelId}")
    public void getModel(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long modelId)
            throws IOException, IllegalUIConfigurationException {

        Simulation simulation = RomaClientUtil.client().getSimulation(modelId);
        response.getOutputStream().write(convertModel(simulation).toString().getBytes());
    }

    @GetMapping("models/{modelId}/run")
    public void runModel(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long modelId, @RequestParam String inputs)
            throws IOException {
        JsonReader jsonReader = Json.createReader(new ByteArrayInputStream(inputs.getBytes("UTF-8")));
        JsonObject inputsObject = jsonReader.readObject();
        Map<Long, Object> inputsValues = new HashMap<>();
        for (Entry<String, JsonValue> entries : inputsObject.entrySet()) {
            String key = entries.getKey();
            inputsValues.put(Long.parseLong(key), inputsObject.getString(key));
        }

        Simulation simulation = RomaClientUtil.client().getSimulation(modelId);

        try {
            Scenario scenario = RomaClientUtil.client()
                    .runModel(simulation, inputsValues, 0L, false);

            if (StringUtils.isNotBlank(scenario.getErrorStackTrace())) {
                _log.error("Error while fetching scenario: {}",
                        Jsoup.parse(scenario.getErrorStackTrace()).getElementById("main").text());
            }

            response.getOutputStream()
                    .write(convertScenario(scenario).toString().getBytes());
        } catch (IllegalUIConfigurationException | ScenarioNotFoundException | ModelNotFoundException e) {
            throw new InternalException(e);
        }
    }

    private JsonObject convertScenario(Scenario scenario)
            throws IOException, IllegalUIConfigurationException {
        if (StringUtils.isNotBlank(scenario.getErrorStackTrace())) {
            return Json.createObjectBuilder()
                    .add("error", true)
                    .add("errorStackTrace", scenario.getErrorStackTrace())
                    .build();
        }

        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();

        IModelGlobalPreference modelPreference =
                modelingClient.getModelPreference(scenario.getSimulation().getId());
        ModelDisplay display = ModelUIFactory.getInstance().getDisplay(scenario);

        jsonBuilder.add("scenarioId", scenario.getId())
                .add("modelId", scenario.getSimulation().getId())
                .add("modelName", scenario.getSimulation().getName())
                .add("modelDescription", scenario.getSimulation().getDescription());

        JsonArrayBuilder outputsArray = Json.createArrayBuilder();
        for (ModelOutputDisplayItem item : display.getOutputs()) {
            outputsArray.add(item.toJson());
        }
        jsonBuilder.add("outputs", outputsArray);

        JsonArrayBuilder inputsArray = Json.createArrayBuilder();
        for (ModelInputDisplayItem item : display.getInputs()) {
            inputsArray.add(item.toJson());
        }
        jsonBuilder.add("inputs", inputsArray);

        JsonArrayBuilder inputValuesArray = Json.createArrayBuilder();
        for (Variable item : scenario.getInputSet()) {
            inputValuesArray.add(ModelUIFactory.convertToJson(item));
        }
        jsonBuilder.add("inputValues", inputValuesArray)
                .add("usesCustomInputs", modelPreference.isUsesCustomInputs())
                .add("customInputsDefinition", modelPreference.getCustomInputsDefinition());
        return jsonBuilder.build();

    }

    private JsonObject convertModel(Simulation simulation)
            throws IllegalUIConfigurationException, IOException {
        IModelGlobalPreference modelPreference = modelingClient.getModelPreference(simulation.getId());
        ModelDisplay display = ModelUIFactory.getInstance().getDisplay(simulation);
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();

        jsonBuilder.add("modelId", simulation.getId())
                .add("modelName", simulation.getName())
                .add("modelDescription", simulation.getDescription());

        JsonArrayBuilder outputsArray = Json.createArrayBuilder();
        for (ModelOutputDisplayItem item : display.getOutputs()) {
            outputsArray.add(item.toJson());
        }
        jsonBuilder.add("outputs", outputsArray);

        JsonArrayBuilder inputsArray = Json.createArrayBuilder();
        for (ModelInputDisplayItem item : display.getInputs()) {
            inputsArray.add(item.toJson());
        }
        jsonBuilder.add("inputs", inputsArray)
                .add("usesCustomInputs", modelPreference.isUsesCustomInputs())
                .add("customInputsDefinition", modelPreference.getCustomInputsDefinition());

        return jsonBuilder.build();
    }

}
