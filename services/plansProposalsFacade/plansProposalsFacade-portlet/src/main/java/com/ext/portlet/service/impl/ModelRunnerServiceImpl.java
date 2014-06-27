package com.ext.portlet.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.ext.portlet.model.ModelGlobalPreference;
import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.models.ui.IllegalUIConfigurationException;
import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelOutputDisplayItem;
import com.ext.portlet.models.ui.ModelUIFactory;
import com.ext.portlet.service.base.ModelRunnerServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.security.ac.AccessControlled;

import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.Variable;
import edu.mit.cci.roma.client.comm.ModelNotFoundException;
import edu.mit.cci.roma.client.comm.ScenarioNotFoundException;

/**
 * The implementation of the model runner remote service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.ext.portlet.service.ModelRunnerService} interface.
 * 
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 * 
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ModelRunnerServiceBaseImpl
 * @see com.ext.portlet.service.ModelRunnerServiceUtil
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class ModelRunnerServiceImpl extends ModelRunnerServiceBaseImpl {

    //private ClientRepository repository;

    /*
     * NOTE FOR DEVELOPERS:
     * 
     * Never reference this interface directly. Always use {@link
     * com.ext.portlet.service.ModelRunnerServiceUtil} to access the model
     * runner remote service.
     */
    public ModelRunnerServiceImpl() throws SystemException {
    }

    @JSONWebService
    @AccessControlled(guestAccessEnabled=true)
    public JSONObject getScenario(long scenarioId) {
        try {
            Scenario scenario = CollaboratoriumModelingService.repository().getScenario(scenarioId);
            return convertScenario(scenario);

        } catch (SystemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalUIConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return JSONFactoryUtil.createJSONObject();
    }

    @JSONWebService
    @AccessControlled(guestAccessEnabled=true)
    public JSONObject getModel(long modelId) throws SystemException, IllegalUIConfigurationException, IOException {

        Simulation simulation = CollaboratoriumModelingService.repository().getSimulation(modelId);
        
        return convertModel(simulation);
    }

    @JSONWebService
    @AccessControlled(guestAccessEnabled=true)
    public JSONObject runModel(long modelId, String inputs) throws IOException, ScenarioNotFoundException,
            ModelNotFoundException, JSONException, SystemException, IllegalUIConfigurationException {
        JSONObject inputsObject = JSONFactoryUtil.createJSONObject(inputs);
        Map<Long, Object> inputsValues = new HashMap<>();
        for (Iterator<String> keyIterator = inputsObject.keys(); keyIterator.hasNext();) {
            String key = keyIterator.next();
            inputsValues.put(Long.parseLong(key), inputsObject.getString(key));
        }

        Simulation simulation = CollaboratoriumModelingService.repository().getSimulation(modelId);

        Scenario scenario = CollaboratoriumModelingService.repository().runModel(simulation, inputsValues, 0L, false);
        return convertScenario(scenario); 
    }
    
    private JSONObject convertScenario(Scenario scenario) throws SystemException, IllegalUIConfigurationException, IOException {
    	ModelGlobalPreference modelPreference = modelGlobalPreferenceLocalService.getByModelId(scenario.getSimulation().getId());
        ModelDisplay display = ModelUIFactory.getInstance().getDisplay(scenario);
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        jsonObject.put("scenarioId", scenario.getId());
        jsonObject.put("modelId", scenario.getSimulation().getId());
        jsonObject.put("modelName", scenario.getSimulation().getName());
        jsonObject.put("modelDescription", scenario.getSimulation().getDescription());

        JSONArray outputsArray = JSONFactoryUtil.createJSONArray();
        for (ModelOutputDisplayItem item : display.getOutputs()) {
            outputsArray.put(item.toJson());
        }
        jsonObject.put("outputs", outputsArray);

        JSONArray inputsArray = JSONFactoryUtil.createJSONArray();
        for (ModelInputDisplayItem item : display.getInputs()) {
            inputsArray.put(item.toJson());
        }
        jsonObject.put("inputs", inputsArray);

        JSONArray inputValuesArray = JSONFactoryUtil.createJSONArray();
        for (Variable item : scenario.getInputSet()) {
            inputValuesArray.put(ModelUIFactory.convertToJson(item));
        }
        jsonObject.put("inputValues", inputValuesArray);
        jsonObject.put("usesCustomInputs", modelPreference.isUsesCustomInputs());
        jsonObject.put("customInputsDefinition", modelPreference.getCustomInputsDefinition());
        return jsonObject;
        
    }

    private JSONObject convertModel(Simulation simulation) throws SystemException, IllegalUIConfigurationException, IOException {
    	ModelGlobalPreference modelPreference = modelGlobalPreferenceLocalService.getByModelId(simulation.getId());
        ModelDisplay display = ModelUIFactory.getInstance().getDisplay(simulation);
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        
        jsonObject.put("modelId", simulation.getId());
        jsonObject.put("modelName", simulation.getName());
        jsonObject.put("modelDescription", simulation.getDescription());

        JSONArray outputsArray = JSONFactoryUtil.createJSONArray();
        for (ModelOutputDisplayItem item : display.getOutputs()) {
            outputsArray.put(item.toJson());
        }
        jsonObject.put("outputs", outputsArray);

        JSONArray inputsArray = JSONFactoryUtil.createJSONArray();
        for (ModelInputDisplayItem item : display.getInputs()) {
            inputsArray.put(item.toJson());
        }
        jsonObject.put("inputs", inputsArray);
        jsonObject.put("usesCustomInputs", modelPreference.isUsesCustomInputs());
        jsonObject.put("customInputsDefinition", modelPreference.getCustomInputsDefinition());
        
        return jsonObject;
    }

}
