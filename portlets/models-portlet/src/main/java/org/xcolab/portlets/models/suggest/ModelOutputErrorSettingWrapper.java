package org.xcolab.portlets.models.suggest;

import javax.faces.event.ActionEvent;

import org.xcolab.portlets.models.SimulationDetailsBean;

import com.ext.portlet.models.ui.ErrorPolicy;
import com.ext.portlet.models.ui.ModelOutputDisplayItem;
import com.ext.portlet.models.ui.ModelOutputIndexedDisplayItem;
import com.ext.portlet.models.ui.ModelOutputSeriesDisplayItem;
import com.liferay.portal.kernel.exception.SystemException;

import edu.mit.cci.simulation.client.TupleStatus;

public class ModelOutputErrorSettingWrapper {
    private ModelOutputDisplayItem item;
    private TupleStatus status;
    private SimulationDetailsBean bean;
    private ErrorPolicy policy;
    private String msg;
    
    public ModelOutputErrorSettingWrapper(ModelOutputDisplayItem item, TupleStatus status, SimulationDetailsBean bean) {
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
    
    public void update(ActionEvent event) throws SystemException {
        if (item instanceof ModelOutputSeriesDisplayItem) {
            ((ModelOutputSeriesDisplayItem) item).setErrorBehavior(status, policy, msg);
        }
        else if (item instanceof ModelOutputIndexedDisplayItem) {
            ((ModelOutputIndexedDisplayItem) item).setErrorBehavior(status, policy, msg);
        }
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
