package com.ext.portlet.service.impl;

import java.io.IOException;

import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.models.ui.IllegalUIConfigurationException;
import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelOutputDisplayItem;
import com.ext.portlet.models.ui.ModelOutputIndexedDisplayItem;
import com.ext.portlet.models.ui.ModelUIFactory;
import com.ext.portlet.service.base.ModelRunnerServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;

import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Variable;
import edu.mit.cci.roma.client.comm.ClientRepository;

/**
 * The implementation of the model runner remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ModelRunnerService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ModelRunnerServiceBaseImpl
 * @see com.ext.portlet.service.ModelRunnerServiceUtil
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class ModelRunnerServiceImpl extends ModelRunnerServiceBaseImpl {
    
    private ClientRepository repository;
    
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ModelRunnerServiceUtil} to access the model runner remote service.
     */
    public ModelRunnerServiceImpl() throws SystemException {
        repository = CollaboratoriumModelingService.repository();
    }

    @JSONWebService
    public JSONObject getScenario(long scenarioId) {
        try {
            Scenario scenario = repository.getScenario(scenarioId);
            ModelDisplay display = ModelUIFactory.getInstance().getDisplay(scenario);
            JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
            jsonObject.put("scenarioId", scenarioId);
            
            JSONArray jsonArray = JSONFactoryUtil.createJSONArray(); 
            for (ModelOutputDisplayItem item:  display.getOutputs()) {
                jsonArray.put(item.toJson());
            }            
            jsonObject.put("outputs", jsonArray);
            
            return jsonObject; 
            
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
    public JSONObject getModel(long modelId) {
        return JSONFactoryUtil.createJSONObject();
    }

    @JSONWebService
    public JSONObject runModel(long modelId, String inputs) {
        return JSONFactoryUtil.createJSONObject();
    }
}
