package org.xcolab.client.modeling;

import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Simulation;

import org.xcolab.client.modeling.pojo.ModelGlobalPreference;
import org.xcolab.client.modeling.pojo.ModelInputGroup;
import org.xcolab.client.modeling.pojo.ModelInputItem;
import org.xcolab.client.modeling.pojo.ModelOutputChartOrder;
import org.xcolab.client.modeling.pojo.ModelOutputItem;
import org.xcolab.client.modeling.pojo.ModelPosition;
import org.xcolab.util.http.client.RestService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public final class ModelingClientUtil {

    private static final RestService modelingService = new RestService("modeling-service");

    private static final ModelingClient client = ModelingClient.fromService(modelingService);

    private ModelingClientUtil() {
    }

    public static ModelGlobalPreference getModelPreference(long modelId) {
        return client.getModelPreference(modelId);
    }

    public static List<ModelInputGroup> getInputGroups(
            Simulation sim) {
        return client.getInputGroups(sim);
    }

    public static List<ModelInputGroup> getChildGroups(
            ModelInputGroup group) {
        return client.getChildGroups(group);
    }

    public static List<ModelInputItem> getInputItems(
            ModelInputGroup group) {
        return client.getInputItems(group);
    }

    public static ModelInputGroup getParent(
            ModelInputGroup group) {
        return client.getParent(group);
    }

    public static Simulation getModel(
            ModelInputGroup group) throws IOException {
        return client.getModel(group);
    }

    public static MetaData getMetaData(
            ModelInputGroup group) throws IOException {
        return client.getMetaData(group);
    }

    public static ModelInputGroup createModelInputGroup(ModelInputGroup group) {
        return client.createModelInputGroup(group);
    }

    public static ModelInputGroup getModelInputGroup(Long groupId) {
        return client.getModelInputGroup(groupId);
    }

    public static void updateModelInputGroup(ModelInputGroup group) {
        client.updateModelInputGroup(group);
    }

    public static void deleteModelInputGroup(Long modelInputGroupPK) {
        client.deleteModelInputGroup(modelInputGroupPK);
    }

    public static List<ModelInputItem> getItemsForModel(Simulation sim) {
        return client.getItemsForModel(sim);
    }

    public static ModelInputItem getItemForMetaData(Long modelId, MetaData md) {
        return client.getItemForMetaData(modelId, md);
    }

    public static List<ModelInputItem> getItemForGroupId(Long groupid) {
        return client.getItemForGroupId(groupid);
    }

    public static MetaData getMetaData(ModelInputItem item) throws IOException {
        return client.getMetaData(item);
    }

    public static Simulation getModel(ModelInputItem item) throws IOException {
        return client.getModel(item);
    }

    public static Map<String, String> getPropertyMap(
            ModelInputItem item) {
        return client.getPropertyMap(item);
    }

    public static void saveProperties(ModelInputItem item,
            Map<String, String> props) {
        client.saveProperties(item, props);
    }

    public static void store(ModelInputItem item) {
        client.store(item);
    }

    public static void deleteModelInputItem(ModelInputItem item) {
        client.deleteModelInputItem(item);
    }

    public static void updateModelInputItem(ModelInputItem item) {
        client.updateModelInputItem(item);
    }

    public static ModelInputItem createModelInputItem(ModelInputItem item) {
        return client.createModelInputItem(item);
    }

    public static ModelOutputChartOrder getModelOutputChartOrder(
            Simulation sim, String label) {
        return client.getModelOutputChartOrder(sim, label);
    }

    public static ModelOutputChartOrder createModelOutputChartOrder(
            ModelOutputChartOrder pojo) {
        return client.createModelOutputChartOrder(pojo);
    }

    public static void updateModelOutputChartOrder(ModelOutputChartOrder pojo) {
        client.updateModelOutputChartOrder(pojo);
    }

    public static void deleteModelOutputChartOrder(ModelOutputChartOrder pojo) {
        client.deleteModelOutputChartOrder(pojo);
    }

    public static ModelOutputItem createModelOutputItem(
            ModelOutputItem pojo) {
        return client.createModelOutputItem(pojo);
    }

    public static void updateModelOutputItem(ModelOutputItem pojo) {
        client.updateModelOutputItem(pojo);
    }

    public static void deleteModelOutputItem(ModelOutputItem pojo) {
        client.deleteModelOutputItem(pojo);
    }

    public static ModelOutputItem getOutputItem(MetaData md) {
        return client.getOutputItem(md);
    }

    public static ModelPosition createModelPosition(
            ModelPosition pojo) {
        return client.createModelPosition(pojo);
    }

    public static void updateModelPosition(ModelPosition pojo) {
        client.updateModelPosition(pojo);
    }

    public static void deleteModelPosition(ModelPosition pojo) {
        client.deleteModelPosition(pojo);
    }

    public static List<ModelPosition> getModelPositionsByModelId(Long modelId) {
        return client.getModelPositionsByModelId(modelId);
    }

    public static void setModelPositions(Long modelId, List<Long> positionIds) {
        client.setModelPositions(modelId, positionIds);
    }
}