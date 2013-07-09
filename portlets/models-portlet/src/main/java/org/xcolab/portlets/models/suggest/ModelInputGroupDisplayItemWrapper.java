package org.xcolab.portlets.models.suggest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.xcolab.portlets.models.SimulationDetailsBean;

import com.ext.portlet.models.ModelInputGroupType;
import com.ext.portlet.models.ui.IllegalUIConfigurationException;
import com.ext.portlet.models.ui.ModelInputDisplayItem;
import com.ext.portlet.models.ui.ModelInputGroupDisplayItem;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import edu.mit.cci.roma.client.MetaData;

public class ModelInputGroupDisplayItemWrapper extends ModelInputDisplayItemWrapper {
    private boolean editing;
    private MetaData md;
    private transient SimulationDetailsBean simulationBean;
    private String name = "";
    private String description = "";
    private String oryginalName = "";
    private String oryginalDescription = "";
    private transient ModelInputGroupDisplayItem groupItem;
    private transient ModelInputGroupDisplayItemWrapper newGroupItem;
    private List<ModelInputDisplayItemWrapper> wrappedItems = new ArrayList<ModelInputDisplayItemWrapper>();
    private List<ModelInputDisplayItemWrapper> inputs = new ArrayList<>();
    private com.ext.portlet.models.ModelInputGroupType groupType = ModelInputGroupType.HORIZONTAL;
    private Long newParentGroupId;


    public ModelInputGroupDisplayItemWrapper(ModelInputGroupDisplayItem groupItem, SimulationDetailsBean bean, Map<Long, Object> inputsValues) {
        super(groupItem, inputsValues);
        simulationBean = bean;
        this.groupItem = groupItem;
        name = groupItem.getName();
        description = groupItem.getDescription();
        oryginalDescription = groupItem.getOriginalDescription();
        oryginalName = groupItem.getOriginalName();
        
        for (ModelInputDisplayItem item: groupItem.getAllItems()) {
            wrappedItems.add(ModelInputDisplayItemWrapper.getInputWrapper(item, bean, inputsValues));
        }
        
    }
    
    public ModelInputGroupDisplayItemWrapper(SimulationDetailsBean bean) {
        super(null, null);
        this.simulationBean = bean;
        this.wrappedItems = bean.getDisplay().getInputsWithGroups();
    }
    
    
    public List<ModelInputDisplayItem> getDisplayItems() {
        return ((ModelInputGroupDisplayItem)groupItem).getDisplayItems();
    }
    
    public void update(ActionEvent e) throws SystemException, IllegalUIConfigurationException, IOException {
        if (groupItem == null) {
            // adding
            ModelInputGroupDisplayItem createdItem = null;
            if (md != null) {
                createdItem = ModelInputGroupDisplayItem.create(simulationBean.getSimulation(), md, groupType, newParentGroupId);
            }
            else {
                createdItem = ModelInputGroupDisplayItem.create(simulationBean.getSimulation(), name, description, groupType, newParentGroupId);
            }

            int maxOrder = Integer.MIN_VALUE;
            for (ModelInputDisplayItem item: simulationBean.getDisplay().getOryginalInputs()) {
                if (item.getOrder() > maxOrder) {
                    maxOrder = item.getOrder();
                }
            }
            createdItem.setOrder(maxOrder+1);
        }
        else {
            groupItem.setDescription(description);
            groupItem.setName(name);
            if (md != null) {
                groupItem.setMetaData(md);
            }
        }
        editing = false;
        simulationBean.refresh();
    }
    
    public void delete(ActionEvent e) throws PortalException, SystemException, IllegalUIConfigurationException, IOException {
        if (groupItem != null) {
            ((ModelInputGroupDisplayItem) groupItem).delete();
            simulationBean.refresh();
        }
    }
    
    
    public void cancel(ActionEvent e) {
        editing = false;
    }
    
    public void edit(ActionEvent e) {
        editing = true;
        name = "";
        description = "";
        if (groupItem != null) {
            name = groupItem.getOriginalName();
            description = groupItem.getOriginalDescription();
        }
    }
    
    public boolean isEditing() {
        return editing;
    }
    
    public Long getMetaDataId() {
        if (groupItem != null && groupItem.getMetaData() != null) {
            return groupItem.getMetaData().getId();
        }
        return null;
    }
    
    public void setMetaDataId(Long mdId) {
        for (MetaData md: simulationBean.getSimulation().getInputs()) {
            if (md.getId().equals(mdId)) {
                this.md = md;
            }
        } 
    }
    
    public boolean isPersistent() {
        return groupItem != null && groupItem.getGroupId() > 0;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isAdding() {
        return groupItem == null;
    }
    
    public String getOryginalDescription() {
        return oryginalDescription;
    }

    public String getOryginalName() {
        return oryginalName;
    }
    

    public List<ModelInputDisplayItemWrapper> getDisplayItemsWrapped() {
        return wrappedItems;
    }
    
    public List<ModelInputDisplayItemWrapper> getAllItems() {
        return wrappedItems;
    }
    
    public List<ModelInputDisplayItemWrapper> getInputs() {
        return wrappedItems;
    }
    
    protected boolean hasValue() {
        return false;
    }
    
    public int getOrder() {
        if (groupItem != null) {
            return groupItem.getOrder();
        }
        return -1;
    }
    
    public void setOrder(int order) throws SystemException {
        if (groupItem != null) {
            groupItem.setOrder(order);
        }   
    }

    public void setOryginalName(String oryginalName) {
        this.oryginalName = oryginalName;
    }

    public void setOryginalDescription(String oryginalDescription) {
        this.oryginalDescription = oryginalDescription;
    }

    public Long getParentGroupId() {
        return groupItem != null ? groupItem.getParentGroupId() : null;
    }

    public void setParentGroupId(Long groupId) throws SystemException, PortalException, IOException {
        newParentGroupId = groupId;
        if (groupId != null && groupId > 0 && groupItem != null) {
            groupItem.setParentGroupId(groupId);
        }
    }

    public com.ext.portlet.models.ModelInputGroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(com.ext.portlet.models.ModelInputGroupType groupType) {
        this.groupType = groupType;
    }

    public ModelInputGroupDisplayItemWrapper getNewGroupItem() {
        if (newGroupItem == null) {
            newGroupItem = new ModelInputGroupDisplayItemWrapper(simulationBean);
        }
        return newGroupItem;
    }

}
