package org.climatecollaboratorium.facelets.simulations.support;

import java.io.IOException;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.facelets.simulations.SimulationBean;

import com.ext.portlet.models.ui.ErrorPolicy;
import com.ext.portlet.models.ui.IllegalUIConfigurationException;
import com.ext.portlet.models.ui.ModelOutputDisplayItem;
import com.ext.portlet.models.ui.ModelOutputIndexedDisplayItem;
import com.ext.portlet.models.ui.ModelOutputSeriesDisplayItem;
import com.liferay.portal.kernel.exception.SystemException;

import edu.mit.cci.simulation.client.TupleStatus;

public class ModelOutputErrorSettingWrapper {
    private ModelOutputDisplayItem item;
    private TupleStatus status;
    private SimulationBean bean;
    private ErrorPolicy policy;
    private String msg;
    
    public ModelOutputErrorSettingWrapper(ModelOutputDisplayItem item, TupleStatus status, SimulationBean bean) {
        this.item = item;
        this.status = status;
        this.bean = bean;
        
        policy = item.getErrorBehavior(status).getPolicy();
        msg = item.getErrorBehavior(status).getMessage(); 
    }
    
    public ModelOutputDisplayItem getItem() {
        return item;
    }
    
    public TupleStatus getStatus() {
        return status;
    }
    
    public void update(ActionEvent event) throws SystemException, IllegalUIConfigurationException, IOException {
        if (item instanceof ModelOutputSeriesDisplayItem) {
            ((ModelOutputSeriesDisplayItem) item).setErrorBehavior(status, policy, msg);
        }
        else if (item instanceof ModelOutputIndexedDisplayItem) {
            ((ModelOutputIndexedDisplayItem) item).setErrorBehavior(status, policy, msg);
        }
        bean.updateDisplay(false);
    }
    
    public String getPolicy() {
        if (policy != null) {
            return policy.name();
        }
        return "";
    }
    
    public void setPolicy(String policy) {
        if (policy.trim().equals("")) {
            this.policy = null;
        } 
        else {
            this.policy = ErrorPolicy.valueOf(policy);
        }
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }


}
