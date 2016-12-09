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
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2L;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.dto.DtoUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelingClient {

    private final RestService modelingService;

    private final RestResource1<Model, Long> modelResource;
    private final RestResource1<ModelCategoryDto, Long> modelCategoryResource;
    private final RestResource1<ModelDiscussionDto, Long> modelDiscussionResource;
    private final RestResource2L<Model, ModelGlobalPreferenceDto> modelPreferenceResource;
    private final RestResource1<ModelInputGroupDto, Long> modelInputGroupResource;
    private final RestResource1<ModelInputItemDto, Long> modelInputItemResource;
    private final RestResource1<ModelOutputChartOrderDto, Long> modelOutputChartOrderResource;
    private final RestResource1<ModelOutputItemDto, Long> modelOutputItemResource;
    private final RestResource1<ModelPositionDto, Long> modelPositionResource;

    private ModelingClient(RestService modelingService) {
        this.modelingService = modelingService;
        modelResource = new RestResource1<>(this.modelingService, "models", Model.TYPES);
        modelPreferenceResource = new RestResource2L<>(modelResource, "preferences", ModelGlobalPreferenceDto.TYPES);

        modelCategoryResource = new RestResource1<>(modelingService, "modelCategories", ModelCategoryDto.TYPES);
        modelDiscussionResource = new RestResource1<>(modelingService, "modelDiscussions", ModelDiscussionDto.TYPES);
        modelInputGroupResource = new RestResource1<>(modelingService, "modelInputGroups", ModelInputGroupDto.TYPES);
        modelInputItemResource = new RestResource1<>(modelingService, "modelInputItems", ModelInputItemDto.TYPES);
        modelOutputChartOrderResource = new RestResource1<>(modelingService, "modelOutputChartOrders", ModelOutputChartOrderDto.TYPES);
        modelOutputItemResource = new RestResource1<>(modelingService, "modelOutputItems", ModelOutputItemDto.TYPES);
        modelPositionResource = new RestResource1<>(modelingService, "modelPositions", ModelPositionDto.TYPES);
    }

    public static ModelingClient fromService(RestService modelingService) {
        return new ModelingClient(modelingService);
    }

    public ModelGlobalPreference getModelPreference(long modelId) {
        return modelPreferenceResource.resolveParent(modelResource.id(modelId))
                .list()
                .executeWithResult()
                .getOneIfExists().toPojo(modelingService);
    }

    public boolean updateModelPreference(ModelGlobalPreference pojo) {
        return modelPreferenceResource.resolveParent(modelResource.id(pojo.getModelId()))
                .update(new ModelGlobalPreferenceDto(pojo), pojo.getModelId())
                .execute();
    }

    public List<ModelInputGroup> getInputGroups(Simulation sim) {
        return DtoUtil.toPojos(modelInputGroupResource.list()
                .queryParam("modelId", sim.getId())
                .execute(), modelingService);
    }

    public List<ModelInputGroup> getChildGroups(ModelInputGroup group) {
        return DtoUtil.toPojos(modelInputGroupResource.list()
                .queryParam("parentModelId", group.getModelInputGroupPK())
                .execute(), modelingService);
    }

    public List<ModelInputItem> getInputItems(ModelInputGroup group) {
        return DtoUtil.toPojos(modelInputItemResource.list()
                .queryParam("modelInputGroupId", group.getModelInputGroupPK())
                .execute(), modelingService);
    }

    public ModelInputGroup getParent(ModelInputGroup group) {
        return modelInputGroupResource.list()
                .queryParam("parentGroupId", group.getParentGroupPK())
                .executeWithResult()
                .getOneIfExists().toPojo(modelingService);
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
                .execute().toPojo(modelingService);
    }

    public void updateModelInputGroup(ModelInputGroup group) {
        modelInputGroupResource.update(new ModelInputGroupDto(group), group.getModelInputGroupPK())
                .execute();
    }

    public ModelInputGroup getModelInputGroup(Long groupId) {
        return modelInputGroupResource.get(groupId)
                .execute().toPojo(modelingService);
    }

    public void deleteModelInputGroup(Long modelInputGroupPK) {
        modelInputGroupResource.delete(modelInputGroupPK)
                .execute();
    }

    public List<ModelInputItem> getItemsForModel(Simulation sim) {
        return DtoUtil.toPojos(modelInputItemResource.list()
                .queryParam("modelId", sim.getId())
                .execute(), modelingService);
    }

    public ModelInputItem getItemForMetaData(Long modelId, MetaData md) {
        return modelInputItemResource.list()
                .queryParam("modelId", modelId)
                .queryParam("modelInputId", md.getId())
                .executeWithResult()
                .getOneIfExists().toPojo(modelingService);
    }



    public List<ModelInputItem> getItemForGroupId(Long groupid) {
        return DtoUtil.toPojos(modelInputItemResource.list()
                .queryParam("modelGroupId", groupid)
                .execute(), modelingService);
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
                .execute().toPojo(modelingService);
    }

    public ModelOutputChartOrder getModelOutputChartOrder(Simulation sim, String label) {
        return modelOutputChartOrderResource.list()
                .queryParam("modelId", sim.getId())
                .queryParam("label", label)
                .executeWithResult()
                .getFirstIfExists().toPojo(modelingService);
    }

    public ModelOutputChartOrder createModelOutputChartOrder(ModelOutputChartOrder pojo) {
        return modelOutputChartOrderResource.create(new ModelOutputChartOrderDto(pojo))
                .execute().toPojo(modelingService);
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
                .execute().toPojo(modelingService);
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
                .execute().toPojo(modelingService);
    }

    public ModelPosition createModelPosition(ModelPosition pojo) {
        return modelPositionResource.create(new ModelPositionDto(pojo))
                .execute().toPojo(modelingService);
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
                .execute(), modelingService);
    }

    public void setModelPositions(Long modelId, List<Long> positionIds) {
        //TODO: implement
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
