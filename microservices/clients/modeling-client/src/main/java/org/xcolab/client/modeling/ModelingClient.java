package org.xcolab.client.modeling;

import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Simulation;

import org.xcolab.client.modeling.pojo.Model;
import org.xcolab.client.modeling.pojo.ModelCategoryDto;
import org.xcolab.client.modeling.pojo.ModelDiscussionDto;
import org.xcolab.client.modeling.pojo.ModelGlobalPreference;
import org.xcolab.client.modeling.pojo.ModelGlobalPreferenceDto;
import org.xcolab.client.modeling.pojo.ModelInputGroup;
import org.xcolab.client.modeling.pojo.ModelInputGroupDto;
import org.xcolab.client.modeling.pojo.ModelInputItem;
import org.xcolab.client.modeling.pojo.ModelInputItemDto;
import org.xcolab.client.modeling.pojo.ModelOutputChartOrder;
import org.xcolab.client.modeling.pojo.ModelOutputChartOrderDto;
import org.xcolab.client.modeling.pojo.ModelOutputItem;
import org.xcolab.client.modeling.pojo.ModelOutputItemDto;
import org.xcolab.client.modeling.pojo.ModelPosition;
import org.xcolab.client.modeling.pojo.ModelPositionDto;
import org.xcolab.client.modeling.roma.RomaClientUtil;
import org.xcolab.commons.http.client.RestResource1;
import org.xcolab.commons.http.client.RestResource2L;
import org.xcolab.commons.http.client.enums.ServiceNamespace;
import org.xcolab.commons.http.dto.DtoUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelingClient {

    private final ServiceNamespace namespace;

    private final RestResource1<Model, Long> modelResource;
    private final RestResource1<ModelCategoryDto, Long> modelCategoryResource;
    private final RestResource1<ModelDiscussionDto, Long> modelDiscussionResource;
    private final RestResource2L<Model, ModelGlobalPreferenceDto> modelPreferenceResource;
    private final RestResource1<ModelInputGroupDto, Long> modelInputGroupResource;
    private final RestResource1<ModelInputItemDto, Long> modelInputItemResource;
    private final RestResource1<ModelOutputChartOrderDto, Long> modelOutputChartOrderResource;
    private final RestResource1<ModelOutputItemDto, Long> modelOutputItemResource;
    private final RestResource1<ModelPositionDto, Long> modelPositionResource;

    private ModelingClient(ServiceNamespace namespace) {
        this.namespace = namespace;

        modelResource = new RestResource1<>(ModelingResource.MODELS, Model.TYPES, namespace);
        modelPreferenceResource = new RestResource2L<>(modelResource, "preferences",
                ModelGlobalPreferenceDto.TYPES);

        modelCategoryResource = new RestResource1<>(ModelingResource.MODEL_CATEGORIES,
                ModelCategoryDto.TYPES, namespace);
        modelDiscussionResource = new RestResource1<>(ModelingResource.MODEL_DISCUSSIONS,
                ModelDiscussionDto.TYPES, namespace);
        modelInputGroupResource = new RestResource1<>(ModelingResource.MODEL_INPUT_GROUPS,
                ModelInputGroupDto.TYPES, namespace);
        modelInputItemResource = new RestResource1<>(ModelingResource.MODEL_INPUT_ITEMS,
                ModelInputItemDto.TYPES, namespace);
        modelOutputChartOrderResource =
                new RestResource1<>(ModelingResource.MODEL_OUTPUT_CHART_ORDERS,
                        ModelOutputChartOrderDto.TYPES, namespace);
        modelOutputItemResource = new RestResource1<>(ModelingResource.MODEL_OUTPUT_ITEMS,
                ModelOutputItemDto.TYPES, namespace);
        modelPositionResource = new RestResource1<>(ModelingResource.MODEL_POSITIONS,
                ModelPositionDto.TYPES, namespace);
    }

    public static ModelingClient fromNamespace(ServiceNamespace namespace) {
        return new ModelingClient(namespace);
    }

    public ModelGlobalPreference getModelPreference(long modelId) {
        return modelPreferenceResource.resolveParent(modelResource.id(modelId))
                .list()
                .executeWithResult()
                .getOneIfExists().toPojo(namespace);
    }

    public boolean updateModelPreference(ModelGlobalPreference pojo) {
        return modelPreferenceResource.resolveParent(modelResource.id(pojo.getModelId()))
                .update(new ModelGlobalPreferenceDto(pojo), pojo.getModelId())
                .execute();
    }

    public List<ModelInputGroup> getInputGroups(Simulation sim) {
        return DtoUtil.toPojos(modelInputGroupResource.list()
                .queryParam("modelId", sim.getId())
                .execute(), namespace);
    }

    public List<ModelInputGroup> getChildGroups(ModelInputGroup group) {
        return DtoUtil.toPojos(modelInputGroupResource.list()
                .queryParam("parentGroupPk", group.getModelInputGroupPK())
                .execute(), namespace);
    }

    public List<ModelInputItem> getInputItems(ModelInputGroup group) {
        return DtoUtil.toPojos(modelInputItemResource.list()
                .queryParam("modelInputGroupPk", group.getModelInputGroupPK())
                .execute(), namespace);
    }

    public ModelInputGroup getParentGroup(ModelInputGroup group) {
        final Long parentGroupPK = group.getParentGroupPK();
        return modelInputGroupResource.get(parentGroupPK)
                .execute().toPojo(namespace);
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
        return modelInputGroupResource.create(new ModelInputGroupDto(group))
                .execute().toPojo(namespace);
    }

    public void updateModelInputGroup(ModelInputGroup group) {
        modelInputGroupResource.update(new ModelInputGroupDto(group), group.getModelInputGroupPK())
                .execute();
    }

    public ModelInputGroup getModelInputGroup(Long groupId) {
        return modelInputGroupResource.get(groupId)
                .execute().toPojo(namespace);
    }

    public void deleteModelInputGroup(Long modelInputGroupPK) {
        modelInputGroupResource.delete(modelInputGroupPK)
                .execute();
    }

    public List<ModelInputItem> getItemsForModel(Simulation sim) {
        return DtoUtil.toPojos(modelInputItemResource.list()
                .queryParam("modelId", sim.getId())
                .execute(), namespace);
    }

    public ModelInputItem getItemForMetaData(Long modelId, MetaData md) {
        return modelInputItemResource.list()
                .queryParam("modelId", modelId)
                .queryParam("modelInputId", md.getId())
                .executeWithResult()
                .getOneIfExists().toPojo(namespace);
    }



    public List<ModelInputItem> getItemForGroupId(Long groupid) {
        return DtoUtil.toPojos(modelInputItemResource.list()
                .queryParam("modelInputGroupPk", groupid)
                .execute(), namespace);
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
        if (item.getModelInputItemPK() == null) {
            modelInputItemResource.create(new ModelInputItemDto(item));
        } else {
            modelInputItemResource.update(new ModelInputItemDto(item), item.getModelInputItemPK());
        }
    }

    public void deleteModelInputItem(ModelInputItem item) {
        modelInputItemResource.delete(item.getModelInputItemPK())
                .execute();
    }

    public void updateModelInputItem(ModelInputItem item) {
        modelInputItemResource.update(new ModelInputItemDto(item), item.getModelInputItemPK())
                .execute();
    }

    public ModelInputItem createModelInputItem(ModelInputItem item) {
        return modelInputItemResource.create(new ModelInputItemDto(item))
                .execute().toPojo(namespace);
    }

    public ModelOutputChartOrder getModelOutputChartOrder(Simulation sim, String label) {
        return modelOutputChartOrderResource.list()
                .queryParam("modelId", sim.getId())
                .queryParam("label", label)
                .executeWithResult()
                .getFirstIfExists().toPojo(namespace);
    }

    public ModelOutputChartOrder createModelOutputChartOrder(ModelOutputChartOrder pojo) {
        return modelOutputChartOrderResource.create(new ModelOutputChartOrderDto(pojo))
                .execute().toPojo(namespace);
    }

    public void updateModelOutputChartOrder(ModelOutputChartOrder pojo) {
        modelOutputChartOrderResource
                .update(new ModelOutputChartOrderDto(pojo), pojo.getModelOutputChartOrderPK())
                .execute();
    }

    public void deleteModelOutputChartOrder(ModelOutputChartOrder pojo) {
        modelOutputChartOrderResource
                .delete(pojo.getModelOutputChartOrderPK())
                .execute();
    }

    public ModelOutputItem createModelOutputItem(ModelOutputItem pojo) {
        return modelOutputItemResource.create(new ModelOutputItemDto(pojo))
                .execute().toPojo(namespace);
    }

    public void updateModelOutputItem(ModelOutputItem pojo) {
        modelOutputItemResource
                .update(new ModelOutputItemDto(pojo), pojo.getModelOutputItemId())
                .execute();
    }

    public void deleteModelOutputItem(ModelOutputItem pojo) {
        modelOutputItemResource
                .delete(pojo.getModelOutputItemId())
                .execute();
    }

    public ModelOutputItem getOutputItem(MetaData md) {
        return modelOutputItemResource.get(md.getId())
                .execute().toPojo(namespace);
    }

    public ModelPosition createModelPosition(ModelPosition pojo) {
        return modelPositionResource.create(new ModelPositionDto(pojo))
                .execute().toPojo(namespace);
    }

    public void updateModelPosition(ModelPosition pojo) {
        modelPositionResource
                .update(new ModelPositionDto(pojo), pojo.getPositionId())
                .execute();
    }

    public void deleteModelPosition(ModelPosition pojo) {
        modelPositionResource
                .delete(pojo.getPositionId())
                .execute();
    }

    public List<ModelPosition> getModelPositionsByModelId(Long modelId) {
        return DtoUtil.toPojos(modelPositionResource.list()
                .queryParam("modelId", modelId)
                .execute(), namespace);
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
