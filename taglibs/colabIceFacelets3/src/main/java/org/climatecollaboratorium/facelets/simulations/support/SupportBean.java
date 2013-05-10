package org.climatecollaboratorium.facelets.simulations.support;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.ext.portlet.models.ui.ModelDisplay;
import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputGroupDisplayItem;
import com.ext.portlet.models.ui.ModelInputIndividualDisplayItem;
import com.ext.portlet.models.ui.ModelInputWidgetType;

public class SupportBean {
    
    public List<SelectItem> getInputWidgets() {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        
        for(ModelInputWidgetType type: ModelInputWidgetType.values()) {
            selectItems.add(new SelectItem(type.name(), type.name()));
        }
        return selectItems;
    }
    
    public static List<SelectItem> getModelInputsOptions(ModelDisplay display) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        selectItems.add(new SelectItem(null, "-- choose --"));
        
        for(ModelInputDisplayItem item: display.getInputs()) {
            if (item instanceof ModelInputIndividualDisplayItem) {
                selectItems.add(new SelectItem(item.getMetaData().getId(), item.getName()));
            } 
            else if (item instanceof ModelInputGroupDisplayItem) {
                for (ModelInputDisplayItem individualItem: ((ModelInputGroupDisplayItem) item).getDisplayItems()) {
                    selectItems.add(new SelectItem(individualItem.getMetaData().getId(), individualItem.getName()));
                }
            }
        }
        return selectItems;
    }


}
