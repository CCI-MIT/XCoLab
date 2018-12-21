package org.xcolab.client.modeling;

import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Simulation;

import org.xcolab.client.modeling.pojo.Model;
import org.xcolab.client.modeling.pojo.ModelCategory;
import org.xcolab.client.modeling.pojo.ModelDiscussion;
import org.xcolab.client.modeling.pojo.ModelGlobalPreference;
import org.xcolab.client.modeling.pojo.ModelInputGroup;
import org.xcolab.client.modeling.pojo.ModelInputItem;
import org.xcolab.client.modeling.pojo.ModelOutputChartOrder;
import org.xcolab.client.modeling.pojo.ModelOutputItem;
import org.xcolab.client.modeling.pojo.ModelPosition;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2L;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelingClient {

    // Default instance when used statically
    private static final ModelingClient INSTANCE = new ModelingClient();

    private final RestResource1<Model, Long> modelResource;
    private final RestResource1<ModelCategory, Long> modelCategoryResource;
    private final RestResource1<ModelDiscussion, Long> modelDiscussionResource;
    private final RestResource2L<Model, ModelGlobalPreference> modelPreferenceResource;
    private final RestResource1<ModelInputGroup, Long> modelInputGroupResource;
    private final RestResource1<ModelInputItem, Long> modelInputItemResource;
    private final RestResource1<ModelOutputChartOrder, Long> modelOutputChartOrderResource;
    private final RestResource1<ModelOutputItem, Long> modelOutputItemResource;
    private final RestResource1<ModelPosition, Long> modelPositionResource;

    public ModelingClient() {
        modelResource = new RestResource1<>(ModelingResource.MODELS, Model.TYPES);
        modelPreferenceResource = new RestResource2L<>(modelResource, "preferences",
                ModelGlobalPreference.TYPES);

        modelCategoryResource = new RestResource1<>(ModelingResource.MODEL_CATEGORIES,
                ModelCategory.TYPES);
        modelDiscussionResource = new RestResource1<>(ModelingResource.MODEL_DISCUSSIONS,
                ModelDiscussion.TYPES);
        modelInputGroupResource = new RestResource1<>(ModelingResource.MODEL_INPUT_GROUPS,
                ModelInputGroup.TYPES);
        modelInputItemResource = new RestResource1<>(ModelingResource.MODEL_INPUT_ITEMS,
                ModelInputItem.TYPES);
        modelOutputChartOrderResource =
                new RestResource1<>(ModelingResource.MODEL_OUTPUT_CHART_ORDERS,
                        ModelOutputChartOrder.TYPES);
        modelOutputItemResource = new RestResource1<>(ModelingResource.MODEL_OUTPUT_ITEMS,
                ModelOutputItem.TYPES);
        modelPositionResource = new RestResource1<>(ModelingResource.MODEL_POSITIONS,
                ModelPosition.TYPES);
    }

    public static ModelingClient instance() {
        return INSTANCE;
    }

    public ModelGlobalPreference getModelPreference(long modelId) {
        return modelPreferenceResource.resolveParentId(modelResource.id(modelId))
                .list()
                .executeWithResult()
                .getOneIfExists();
    }

    public boolean updateModelPreference(ModelGlobalPreference pojo) {
        return modelPreferenceResource.resolveParentId(modelResource.id(pojo.getModelId()))
                .update(new ModelGlobalPreference(pojo), pojo.getModelId())
                .execute();
    }

    public List<ModelInputGroup> getInputGroups(Simulation sim) {
        return modelInputGroupResource.list()
                .queryParam("modelId", sim.getId())
                .execute();
    }

    public List<ModelInputGroup> getChildGroups(ModelInputGroup group) {
        return modelInputGroupResource.list()
                .queryParam("parentGroupPk", group.getId())
                .execute();
    }

    public List<ModelInputItem> getInputItems(ModelInputGroup group) {
        return modelInputItemResource.list()
                .queryParam("modelInputGroupPk", group.getId())
                .execute();
    }

    public ModelInputGroup getParentGroup(ModelInputGroup group) {
        final Long parentGroupPK = group.getParentGroupId();
        return modelInputGroupResource.get(parentGroupPK)
                .execute();
    }

    public Simulation getModel(ModelInputGroup group) throws IOException {
        return RomaClientUtil.client().getSimulation(group.getModelId());
    }

    public MetaData getMetaData(ModelInputGroup group) throws IOException {
        if (group.getNameAndDescriptionMetaDataId() > 0) {
            return RomaClientUtil.client().getMetaData(group.getNameAndDescriptionMetaDataId());
        }
        return null;
    }

    public ModelInputGroup createModelInputGroup(ModelInputGroup group) {
        return modelInputGroupResource.create(new ModelInputGroup(group))
                .execute();
    }

    public void updateModelInputGroup(ModelInputGroup group) {
        modelInputGroupResource.update(new ModelInputGroup(group), group.getId())
                .execute();
    }

    public ModelInputGroup getModelInputGroup(Long groupId) {
        return modelInputGroupResource.get(groupId)
                .execute();
    }

    public void deleteModelInputGroup(Long modelInputGroupPK) {
        modelInputGroupResource.delete(modelInputGroupPK)
                .execute();
    }

    public List<ModelInputItem> getItemsForModel(Simulation sim) {
        return modelInputItemResource.list()
                .queryParam("modelId", sim.getId())
                .execute();
    }

    public ModelInputItem getItemForMetaData(Long modelId, MetaData md) {
        return modelInputItemResource.list()
                .queryParam("modelId", modelId)
                .queryParam("modelInputId", md.getId())
                .executeWithResult()
                .getOneIfExists();
    }



    public List<ModelInputItem> getItemForGroupId(Long groupid) {
        return modelInputItemResource.list()
                .queryParam("modelInputGroupPk", groupid)
                .execute();
    }

    public MetaData getMetaData(ModelInputItem item) throws IOException {
        return RomaClientUtil.client().getMetaData(item.getModelInputItemID());
    }

    public Simulation getModel(ModelInputItem item) throws IOException {
        return RomaClientUtil.client().getSimulation(item.getModelId());
    }

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

    public void saveProperties(ModelInputItem item, Map<String, String> props) {
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

    public void store(ModelInputItem item) {
        if (item.getId() == null) {
            modelInputItemResource.create(new ModelInputItem(item));
        } else {
            modelInputItemResource.update(new ModelInputItem(item), item.getId());
        }
    }

    public void deleteModelInputItem(ModelInputItem item) {
        modelInputItemResource.delete(item.getId())
                .execute();
    }

    public void updateModelInputItem(ModelInputItem item) {
        modelInputItemResource.update(new ModelInputItem(item), item.getId())
                .execute();
    }

    public ModelInputItem createModelInputItem(ModelInputItem item) {
        return modelInputItemResource.create(new ModelInputItem(item))
                .execute();
    }

    public ModelOutputChartOrder getModelOutputChartOrder(Simulation sim, String label) {
        return modelOutputChartOrderResource.list()
                .queryParam("modelId", sim.getId())
                .queryParam("label", label)
                .executeWithResult()
                .getFirstIfExists();
    }

    public ModelOutputChartOrder createModelOutputChartOrder(ModelOutputChartOrder pojo) {
        return modelOutputChartOrderResource.create(new ModelOutputChartOrder(pojo))
                .execute();
    }

    public void updateModelOutputChartOrder(ModelOutputChartOrder pojo) {
        modelOutputChartOrderResource
                .update(new ModelOutputChartOrder(pojo), pojo.getId())
                .execute();
    }

    public void deleteModelOutputChartOrder(ModelOutputChartOrder pojo) {
        modelOutputChartOrderResource
                .delete(pojo.getId())
                .execute();
    }

    public ModelOutputItem createModelOutputItem(ModelOutputItem pojo) {
        return modelOutputItemResource.create(new ModelOutputItem(pojo))
                .execute();
    }

    public void updateModelOutputItem(ModelOutputItem pojo) {
        modelOutputItemResource
                .update(new ModelOutputItem(pojo), pojo.getModelOutputItemId())
                .execute();
    }

    public void deleteModelOutputItem(ModelOutputItem pojo) {
        modelOutputItemResource
                .delete(pojo.getModelOutputItemId())
                .execute();
    }

    public ModelOutputItem getOutputItem(MetaData md) {
        return modelOutputItemResource.get(md.getId())
                .execute();
    }

    public ModelPosition createModelPosition(ModelPosition pojo) {
        return modelPositionResource.create(new ModelPosition(pojo))
                .execute();
    }

    public void updateModelPosition(ModelPosition pojo) {
        modelPositionResource
                .update(new ModelPosition(pojo), pojo.getPositionId())
                .execute();
    }

    public void deleteModelPosition(ModelPosition pojo) {
        modelPositionResource
                .delete(pojo.getPositionId())
                .execute();
    }

    public List<ModelPosition> getModelPositionsByModelId(Long modelId) {
        return modelPositionResource.list()
                .queryParam("modelId", modelId)
                .execute();
    }

    public void setModelPositions(Long modelId, List<Long> positionIds) {
        //TODO COLAB-2581: implement
        throw new UnsupportedOperationException();
//        modelPositionPersistence.removeByModelId(modelId);
//
//        for (Long positionId: positionIds) {
//            Long id = CounterLocalServiceUtil.increment(ModelPosition.class.getName());
//            ModelPosition modelPosition = createModelPosition(id);
//            modelPosition.setModelId(modelId);
//            modelPosition.setPositionId(positionId);
//
//            addModelPosition(modelPosition);
//        }
    }


}
