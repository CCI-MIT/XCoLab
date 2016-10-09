package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchModelInputItemException;
import com.ext.portlet.model.ModelInputItem;
import org.xcolab.client.modeling.RomaClientUtil;
import com.ext.portlet.service.ModelInputItemLocalServiceUtil;
import com.ext.portlet.service.base.ModelInputItemLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Simulation;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The implementation of the model input item local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ModelInputItemLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ModelInputItemLocalServiceBaseImpl
 * @see com.ext.portlet.service.ModelInputItemLocalServiceUtil
 */
public class ModelInputItemLocalServiceImpl
    extends ModelInputItemLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ModelInputItemLocalServiceUtil} to access the model input item local service.
     */
    


    private static final Log _log = LogFactoryUtil.getLog(ModelInputItemLocalServiceImpl.class);

    @Override
    public List<ModelInputItem> getItemsForModel(Simulation sim) {
        try {
            return modelInputItemPersistence.findByModelId(sim.getId());
        } catch (SystemException e) {
            _log.error("WTF now",e);
        }
        return Collections.emptyList();

    }

    @Override
    public ModelInputItem getItemForMetaData(Long modelId, MetaData md) {
        try {
            return modelInputItemPersistence.findByModelIdModelInputId(modelId, md.getId());
        } catch (NoSuchModelInputItemException e) {
            //ignore
        } catch (SystemException e) {
            _log.error("Unexpected error",e);
        }
        return null;

    }

   

    @Override
    public List<ModelInputItem> getItemForGroupId(Long groupid) {
        try {
            return modelInputItemPersistence.findByModelGroupId(groupid);
        } catch (SystemException e) {
            _log.error("goddamn!",e);
        }
        return null;

    }
    
    

    @Override
    public MetaData getMetaData(ModelInputItem item) throws SystemException, IOException {
        return RomaClientUtil.repository().getMetaData(item.getModelInputItemID());
    }

    @Override
    public Simulation getModel(ModelInputItem item) throws SystemException, IOException {
        return RomaClientUtil.repository().getSimulation(item.getModelId());
    }

    @Override
    public Map<String,String> getPropertyMap(ModelInputItem item) {
        return parseTypes(item.getProperties());
    }
    
     private static Map<String,String> parseTypes(String props) {
        if (props == null) {
            return Collections.emptyMap();
        }
        Map<String,String> result = new HashMap<>();
        for (String type:props.split(";")) {
            String[] kv = type.split("=");
            if (kv.length>1) {
                result.put(kv[0],kv[1]);
            }
        }
        return result;
    }
     
    @Override
    public void saveProperties(ModelInputItem item, Map<String, String> props) throws SystemException {
        StringBuilder sb = new StringBuilder();
        
        for (String key: props.keySet()) {
            sb.append(key);
            sb.append("=");
            sb.append(props.get(key));
            sb.append(";");
        }
        
        item.setProperties(sb.toString());
        store(item);
        
    }
    
    @Override
    public void store(ModelInputItem item) throws SystemException {
        if (item.isNew()) {
            ModelInputItemLocalServiceUtil.addModelInputItem(item);
        }
        else {
            ModelInputItemLocalServiceUtil.updateModelInputItem(item);
        }
    }
}
