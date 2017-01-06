package org.xcolab.view.pages.modeling.admin;

import edu.mit.cci.roma.client.EntityState;
import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Simulation;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ModelWrapper implements Simulation {

    private final Simulation wrapped;

    public ModelWrapper(Simulation wrapped) {

        this.wrapped = wrapped;
    }

    public String getEditUrl() {
        return ModelsAdminController.getTabMapping(getId(), null);
    }

    public String getInputWidgetsUrl() {
        return ModelsAdminController.getTabMapping(getId(), "inputWidgets");
    }

    public String getOutputWidgetsUrl() {
        return ModelsAdminController.getTabMapping(getId(), "outputWidgets");
    }

    public String getInputTabsUrl() {
        return ModelsAdminController.getTabMapping(getId(), "inputTabs");
    }

    public String getModelDisplayByJsonUrl() {
        return ModelsAdminController.getTabMapping(getId(), "modelDisplayByJson");
    }

    @Override
    public Long getId() {
        return wrapped.getId();
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription();
    }

    @Override
    public void setDescription(String s) {
        wrapped.setDescription(s);
    }

    @Override
    public URL getURL() {
        return wrapped.getURL();
    }

    @Override
    public void setURL(String s) {
        wrapped.setURL(s);
    }

    @Override
    public void setURL(URL url) {
        wrapped.setURL(url);
    }

    @Override
    public Date getCreation() {
        return wrapped.getCreation();
    }

    @Override
    public void setCreation(Date date) {
        wrapped.setCreation(date);
    }

    @Override
    public List<MetaData> getInputs() {
        return wrapped.getInputs();
    }

    @Override
    public void addInput(MetaData metaData) {
        wrapped.addInput(metaData);
    }

    @Override
    public List<MetaData> getOutputs() {
        return wrapped.getOutputs();
    }

    @Override
    public void addOutput(MetaData metaData) {
        wrapped.addOutput(metaData);
    }

    @Override
    public List<MetaData> getCombinedOutputs() {
        return wrapped.getCombinedOutputs();
    }

    @Override
    public String getName() {
        return wrapped.getName();
    }

    @Override
    public void setName(String s) {
        wrapped.setName(s);
    }

    @Override
    public void setState(EntityState entityState) {
        wrapped.setState(entityState);
    }

    @Override
    public EntityState getState() {
        return wrapped.getState();
    }

    @Override
    public void setType(String s) {
        wrapped.setType(s);
    }

    @Override
    public String getType() {
        return wrapped.getType();
    }

    @Override
    public Set<Simulation> getChildren() {
        return wrapped.getChildren();
    }

    @Override
    public Map<String, String> getUpdate() {
        return wrapped.getUpdate();
    }

    @Override
    public boolean isDirty() {
        return wrapped.isDirty();
    }
}
