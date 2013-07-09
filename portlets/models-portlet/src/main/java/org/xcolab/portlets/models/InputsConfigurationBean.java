package org.xcolab.portlets.models;

import java.util.Arrays;
import java.util.List;

import org.xcolab.portlets.models.suggest.ModelDisplayWrapper;
import org.xcolab.portlets.models.suggest.ModelInputDisplayItemWrapper;
import org.xcolab.portlets.models.suggest.ModelInputGroupDisplayItemWrapper;

public class InputsConfigurationBean {
    private ModelDisplayWrapper display;
    private ModelInputGroupDisplayItemWrapper defaultTab;
    private SimulationDetailsBean simulationDetailsBean;
    private ModelInputGroupDisplayItemWrapper newGroupWrapper;
    
    public InputsConfigurationBean(ModelDisplayWrapper display, SimulationDetailsBean simulationDetailsBean) {
        this.display = display;
        this.simulationDetailsBean = simulationDetailsBean;
        
        this.defaultTab = new ModelInputGroupDisplayItemWrapper(simulationDetailsBean);

        this.newGroupWrapper = new ModelInputGroupDisplayItemWrapper(simulationDetailsBean);
    }


    public boolean getHasTabs() {
        return display.hasTabs();
    }
    
    public List<ModelInputGroupDisplayItemWrapper> getInputsTabs() {
        if (! display.hasTabs()) {
            return Arrays.asList(new ModelInputGroupDisplayItemWrapper[] { defaultTab });
        }
        
        return simulationDetailsBean.getDisplay().getTabsWrapped();
    }


    public ModelInputGroupDisplayItemWrapper getNewGroupWrapper() {
        return newGroupWrapper;
    }
    
    public List<ModelInputDisplayItemWrapper> getUnassociatedItems() {
        return display.getUnassociatedInputs();
    }


}
