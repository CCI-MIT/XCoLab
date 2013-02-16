package org.climatecollaboratorium.facelets.simulations.support;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ext.portlet.models.ui.ModelUIFactory;
import com.liferay.portal.kernel.exception.SystemException;

import edu.mit.cci.roma.client.EntityState;
import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Simulation;


public class SimulationDecorator implements Simulation {
    private Simulation wrapped;
    private boolean visible;
    private int weight;

    public SimulationDecorator(Simulation wrapped) throws SystemException {
        this.wrapped = wrapped;
        
        this.visible = ModelUIFactory.isSimulationVisible(wrapped);
        this.weight = ModelUIFactory.getSimulationWeight(wrapped);
    }

    @Override
    public Set<Simulation> getChildren() {
        return wrapped.getChildren();
    }

    @Override
    public List<MetaData> getCombinedOutputs() {
        return wrapped.getCombinedOutputs();
    }

    @Override
    public Date getCreation() {
        return wrapped.getCreation();
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription();
    }

    @Override
    public Long getId() {
        return wrapped.getId();
    }

    @Override
    public List<MetaData> getInputs() {
        return wrapped.getInputs();
    }

    @Override
    public String getName() {
        return wrapped.getName();
    }

    @Override
    public List<MetaData> getOutputs() {
        return wrapped.getOutputs();
    }

    @Override
    public EntityState getState() {
        return wrapped.getState();
    }

    @Override
    public URL getURL() {
        return wrapped.getURL();
    }

    @Override
    public void setCreation(Date arg0) {
        wrapped.setCreation(arg0);
    }

    @Override
    public void setDescription(String arg0) {
        wrapped.setDescription(arg0);
    }

    @Override
    public void setName(String arg0) {
        wrapped.setName(arg0);
    }

    @Override
    public void setState(EntityState arg0) {
        wrapped.setState(arg0);
    }

    @Override
    public void setURL(String arg0) {
        wrapped.setURL(arg0);

    }

    @Override
    public void setURL(URL arg0) {
        wrapped.setURL(arg0);
    }

    @Override
    public void addInput(MetaData arg0) {
        wrapped.addInput(arg0);
    }

    @Override
    public void addOutput(MetaData arg0) {
        wrapped.addOutput(arg0);
    }

    public Simulation getWrapped() {
        return wrapped;
    }

    public String getFirstParagraph() {
        final String paragraphStartRegex = "<p>";
        final String paragraphEndRegex = "</p>";

        final Pattern paragraphStartPattern = Pattern.compile(paragraphStartRegex);
        final Pattern paragraphEndPattern = Pattern.compile(paragraphEndRegex);

        Matcher startMatcher = paragraphStartPattern.matcher(wrapped.getDescription());
        Matcher endMatcher = paragraphEndPattern.matcher(wrapped.getDescription());

        int startIdx = 0;
        int endIdx = wrapped.getDescription().length();

        if (startMatcher.find()) {
            startIdx = startMatcher.end();
        }

        if (endMatcher.find()) {
            endIdx = endMatcher.start();
        }

        return wrapped.getDescription().substring(startIdx, endIdx);
    }

    @Override
    public Map<String, String> getUpdate() {
        return wrapped.getUpdate();
    }

    @Override
    public boolean isDirty() {
        return wrapped.isDirty();
    }
    
    public boolean isVisible() throws SystemException {
        
        return this.visible;
    }
    
    public void setVisible(boolean visible) throws SystemException {
        ModelUIFactory.setSimulationVisible(wrapped, visible);
        this.visible = visible;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) throws SystemException {
        ModelUIFactory.setSimulationWeight(wrapped, weight);
        this.weight = weight;
    }

    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setType(String arg0) {
        // TODO Auto-generated method stub
        
    }
    

}