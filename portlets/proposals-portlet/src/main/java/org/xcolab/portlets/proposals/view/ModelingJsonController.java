package org.xcolab.portlets.proposals.view;

import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.Variable;
import edu.mit.cci.roma.client.comm.ModelNotFoundException;
import edu.mit.cci.roma.client.comm.ScenarioNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import org.xcolab.client.modeling.ModelingClientUtil;
import org.xcolab.client.modeling.models.ui.IllegalUIConfigurationException;
import org.xcolab.client.modeling.models.ui.ModelDisplay;
import org.xcolab.client.modeling.models.ui.ModelInputDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelOutputDisplayItem;
import org.xcolab.client.modeling.models.ui.ModelUIFactory;
import org.xcolab.client.modeling.pojo.ModelGlobalPreference;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.util.exceptions.InternalException;

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
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Controller
@RequestMapping("view")
public class ModelingJsonController {

    private final static Logger _log = LoggerFactory.getLogger(ModelingJsonController.class);

    @ResourceMapping("getScenario")
    public void getScenario(ResourceRequest request, ResourceResponse response,
            @RequestParam long scenarioId) throws IOException {
        JsonObject scenarioJson = Json.createObjectBuilder().build();
        try {
            Scenario scenario = RomaClientUtil.client().getScenario(scenarioId);
            scenarioJson = convertScenario(scenario);
        } catch (IOException | IllegalUIConfigurationException e) {
            _log.error("", e);
        }
        response.getPortletOutputStream().write(scenarioJson.toString().getBytes());
    }

    @ResourceMapping("getModel")
    public void getModel(ResourceRequest request, ResourceResponse response,
            @RequestParam long modelId)
            throws IOException, IllegalUIConfigurationException {

        Simulation simulation = RomaClientUtil.client().getSimulation(modelId);
        response.getPortletOutputStream().write(convertModel(simulation).toString().getBytes());
    }

    @ResourceMapping("runModel")
    public void runModel(ResourceRequest request, ResourceResponse response,
            @RequestParam long modelId, @RequestParam String inputs)
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

            response.getPortletOutputStream()
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

        ModelGlobalPreference modelPreference = ModelingClientUtil
                .getModelPreference(scenario.getSimulation().getId());
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
                .add("usesCustomInputs", modelPreference.getUsesCustomInputs())
                .add("customInputsDefinition", modelPreference.getCustomInputsDefinition());
        return jsonBuilder.build();

    }

    private JsonObject convertModel(Simulation simulation)
            throws IllegalUIConfigurationException, IOException {
        ModelGlobalPreference modelPreference = ModelingClientUtil.getModelPreference(simulation.getId());
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
                .add("usesCustomInputs", modelPreference.getUsesCustomInputs())
                .add("customInputsDefinition", modelPreference.getCustomInputsDefinition());

        return jsonBuilder.build();
    }

}
