package org.xcolab.client.modeling.models.ui;

import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Scenario;
import edu.mit.cci.roma.client.Simulation;
import edu.mit.cci.roma.client.Tuple;
import edu.mit.cci.roma.client.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.modeling.StaticModelingContext;
import org.xcolab.client.modeling.pojo.IModelInputGroup;
import org.xcolab.client.modeling.pojo.IModelInputItem;
import org.xcolab.util.json.JsonUtil;
import org.xcolab.util.json.NullsafeJsonArrayBuilder;
import org.xcolab.util.json.NullsafeJsonObjectBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class ModelUIFactory {

    private static final ModelUIFactory ourInstance = new ModelUIFactory();

    private static final Logger _log = LoggerFactory.getLogger(ModelUIFactory.class);

    private ModelUIFactory() {
    }

    public static ModelUIFactory getInstance() {
        return ourInstance;
    }

    /**
     * Helper function, returns variable for a scenario given its associated metadata
     */
    public static Variable getVariableForMetaData(Scenario s, MetaData md, boolean isInput) {
        Variable result = null;
        for (Variable var : (isInput ? s.getInputSet() : s.getOutputSet())) {
            if (var.getMetaData().equals(md)) {
                result = var;
                break;
            }

        }
        return result;
    }

    public static JsonObjectBuilder convertToJson(Variable var) {
        NullsafeJsonArrayBuilder valuesArray = JsonUtil.nullsafe(Json.createArrayBuilder());
        for (Tuple val : var.getValue()) {
            valuesArray.addArray(val.getValues());
        }
        return JsonUtil.nullsafe(Json.createObjectBuilder())
                .add("id", var.getId())
                .add("metaData", convertToJson(var.getMetaData()))
                .add("values", valuesArray);

    }

    public static JsonObjectBuilder convertToJson(MetaData md) {
        final NullsafeJsonObjectBuilder jsonBuilder =
                JsonUtil.nullsafe(Json.createObjectBuilder())
                        .add("id", md.getId())
                        .add("name", md.getName())
                        .add("description", md.getDescription())
                        .add("externalInfo", md.getExternalInfo())
                        .add("internalName", md.getInternalName())
                        .add("varType", md.getVarType().name())
                        .add("isIndex", md.getIndex())
                        .addArray("categories", md.getCategories())
                        .addArray("labels", md.getLabels())
                        .addArray("default", md.getDefault())
                        .addArray("max", md.getMax())
                        .addArray("min", md.getMin())
                        .addArray("units", md.getUnits())
                        .add("varContext", md.getVarContext().name())
                        .addArray("profiles", md.getProfile());

        if (md.getIndexingMetaData() != null) {
            jsonBuilder.add("indexingMetaData", md.getIndexingMetaData().getId());
        }
        return jsonBuilder;
    }

    /**
     * Returns the layout information for the model
     */
    public ModelDisplay getDisplay(Simulation s)
            throws IllegalUIConfigurationException, IOException {
        return new ModelDisplay(s);
    }

    /**
     * Returns the layout information for the model, and also sets the scenario
     * on the display container (enabling variable retrieval functions through the
     * display classes
     */
    public ModelDisplay getDisplay(Scenario s) throws IllegalUIConfigurationException, IOException {
        return new ModelDisplay(s);
    }

    /**
     * Package scoped helper function, used to build the output layout classes for the Simulation
     */
    List<ModelOutputDisplayItem> parseOutputs(Simulation s) {
        Map<String, ModelOutputDisplayItem> found = new HashMap<>();
        for (MetaData md : s.getOutputs()) {

            if (md.getVarContext() == MetaData.VarContext.INDEXED) {
                ModelOutputIndexedDisplayItem item;
                if (md.getVarType() == MetaData.VarType.FREE) {
                    item = (ModelOutputIndexedDisplayItem) found.get(md.getName());
                    if (item == null) {
                        item = new ModelOutputIndexedDisplayItem(s, md.getName());
                        item.setChartType(ModelOutputChartType.FREE);
                        found.put(md.getName(), item);
                    }
                    item.addSeriesData(md);
                } else if (md.getVarType() == MetaData.VarType.RANGE) {
                    if (md.getLabels().length < 2) {
                        _log.warn("Metadata {} only has one element", md.getName());
                    }
                    item = (ModelOutputIndexedDisplayItem) found.get(md.getLabels()[1]);
                    if (item == null) {
                        item = new ModelOutputIndexedDisplayItem(s, md.getLabels()[1]);
                        item.setChartType(ModelOutputChartType.TIME_SERIES);
                        found.put(md.getLabels()[1], item);
                    }
                    item.addSeriesData(md);

                } else {
                    _log.warn("Unknown variable type {}", md.getVarType());
                    continue;
                }
                if (item.getIndex() == null) {
                    item.setIndex(md.getIndexingMetaData());
                }
            } else if (md.getVarContext() == MetaData.VarContext.SCALAR) {
                found.put(md.getName(), new ModelOutputScalarDisplayItem(s, md));

            }

        }

        return new ArrayList<>(found.values());
    }

    /**
     * Recursive call to process groups
     */
    private ModelInputGroupDisplayItem processGroup(IModelInputGroup group,
            Set<MetaData> bareMetaData, Simulation simulation)
            throws IllegalUIConfigurationException, IOException {
        for (IModelInputItem item : StaticModelingContext.getModelingClient()
                .getInputItems(group)) {
            final MetaData metaData = StaticModelingContext.getModelingClient().getMetaData(item);
            bareMetaData.remove(metaData);
        }
        ModelInputGroupDisplayItem result;
        try {
            result = new ModelInputGroupDisplayItem(simulation, group);
        } catch (IOException e) {
            _log.error("", e);
            return null;
        }
        for (IModelInputGroup g : StaticModelingContext.getModelingClient().getChildGroups(group)) {
            result.addChildGroup(processGroup(g, bareMetaData, simulation));
        }
        return result;
    }

    /**
     * Package scoped helper function, used to build the input layout classes for the Simulation
     */
    public List<ModelInputDisplayItem> parseInputs(Simulation s)
            throws IllegalUIConfigurationException, IOException {
        List<ModelInputDisplayItem> result = new ArrayList<>();
        Set<MetaData> inputs = new HashSet<>(s.getInputs());

        for (IModelInputGroup group : StaticModelingContext.getModelingClient().getInputGroups(s)) {
            if (group.getParentGroupId() <= 0) {
                result.add(processGroup(group, inputs, s));
            }
        }

        //any left overs
        for (MetaData md : inputs) {
            try {
                IModelInputItem item = StaticModelingContext.getModelingClient()
                        .getItemForMetaData(s.getId(), md);
                ModelInputDisplayItem toAdd = item == null ? ModelInputIndividualDisplayItem
                        .create(s, md, ModelInputWidgetType.TEXT_FIELD) : getInputItem(item);
                result.add(toAdd);

            } catch (IOException e) {
                _log.error("", e);
            }
        }

        return result;
    }

    public ModelInputDisplayItem getInputItem(IModelInputItem item) {
        try {
            return new ModelInputIndividualDisplayItem(item);
        } catch (IOException e) {
            _log.error("", e);
        }
        return null;

    }

    public ModelInputGroupDisplayItem getGroupItem(Simulation simulation, IModelInputGroup item) {
        try {
            return new ModelInputGroupDisplayItem(simulation, item);
        } catch (IOException e) {
            _log.error("", e);
        }
        return null;
    }
}
