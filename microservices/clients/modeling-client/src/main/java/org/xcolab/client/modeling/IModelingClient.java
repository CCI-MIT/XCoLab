package org.xcolab.client.modeling;

import edu.mit.cci.roma.client.MetaData;
import edu.mit.cci.roma.client.Simulation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.modeling.pojo.IModelGlobalPreference;
import org.xcolab.client.modeling.pojo.IModelInputGroup;
import org.xcolab.client.modeling.pojo.IModelInputItem;
import org.xcolab.client.modeling.pojo.IModelOutputChartOrder;
import org.xcolab.client.modeling.pojo.IModelOutputItem;
import org.xcolab.client.modeling.pojo.IModelPosition;
import org.xcolab.client.modeling.roma.RomaClientUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@FeignClient("xcolab-modeling-service")
public interface IModelingClient {

    @GetMapping("/models/{modelId}/preferences")
    IModelGlobalPreference getModelPreference(@PathVariable("modelId") Long modelId);

    @PutMapping("/models/preferences")
    boolean updatePreferences(@RequestBody IModelGlobalPreference modelGlobalPreference);

    default List<IModelInputGroup> getInputGroups(Simulation sim) {
        return getInputGroups(sim.getId(), null);
    }

    default List<IModelInputGroup> getChildGroups(IModelInputGroup group) {
        return getInputGroups(null, group.getId());
    }

    @GetMapping("/modelInputGroups")
    List<IModelInputGroup> getInputGroups(
            @RequestParam(value = "modelId", required = false) Long modelId,
            @RequestParam(value = "parentGroupPk", required = false) Long parentGroupPk);

    default Simulation getModel(IModelInputGroup group) throws IOException {
        return RomaClientUtil.client().getSimulation(group.getModelId());
    }

    default MetaData getMetaData(IModelInputGroup group) throws IOException {
        if (group.getNameAndDescriptionMetaDataId() > 0) {
            return RomaClientUtil.client().getMetaData(group.getNameAndDescriptionMetaDataId());
        }
        return null;
    }

    @PostMapping("/modelInputGroups")
    IModelInputGroup createModelInputGroup(@RequestBody IModelInputGroup modelInputGroup);

    @PutMapping("/modelInputGroups")
    boolean updateModelInputGroup(@RequestBody IModelInputGroup modelInputGroup);

    @GetMapping("/modelInputGroups/{parentGroupId}")
    IModelInputGroup getModelInputGroup(@PathVariable("parentGroupId") Long parentGroupId);

    @DeleteMapping("/modelInputGroups/{groupId}")
    boolean deleteModelInputGroup(@PathVariable(value = "groupId") Long groupId);

    @GetMapping("/modelInputItems")
    public List<IModelInputItem> getModelInputItems(
            @RequestParam(value = "modelInputGroupPk", required = false) Long modelInputGroupPk,
            @RequestParam(value = "modelId", required = false) Long modelId,
            @RequestParam(value = "modelInputId", required = false) Long modelInputId);

    default List<IModelInputItem> getInputItems(IModelInputGroup group) {
        return getModelInputItems(group.getId(), null, null);
    }

    default List<IModelInputItem> getItemsForModel(Simulation sim) {
        return getModelInputItems(null, sim.getId(), null);
    }

    default IModelInputItem getItemForMetaData(Long modelId, MetaData metaData) {
        return getModelInputItems(null, modelId, metaData.getId()).stream().findFirst()
                .orElse(null);
    }

    default List<IModelInputItem> getItemForGroupId(Long groupId) {
        return getModelInputItems(groupId, null, null);
    }

    default MetaData getMetaData(IModelInputItem item) throws IOException {
        return RomaClientUtil.client().getMetaData(item.getModelInputItemId());
    }

    default Simulation getModel(IModelInputItem item) throws IOException {
        return RomaClientUtil.client().getSimulation(item.getModelId());
    }

    default Map<String, String> getPropertyMap(IModelInputItem item) {
        String properties = item.getProperties();
        if (properties == null) {
            return Collections.emptyMap();
        }
        Map<String, String> result = new HashMap<>();
        for (String type : properties.split(";")) {
            String[] kv = type.split("=");
            if (kv.length > 1) {
                result.put(kv[0], kv[1]);
            }
        }
        return result;
    }

    default void saveProperties(IModelInputItem item, Map<String, String> props) {
        StringBuilder sb = new StringBuilder();
        for (String key : props.keySet()) {
            sb.append(key);
            sb.append("=");
            sb.append(props.get(key));
            sb.append(";");
        }
        item.setProperties(sb.toString());
        store(item);
    }

    default void store(IModelInputItem item) {
        if (item.getId() == null) {
            createModelInputItem(item);
        } else {
            updateModelInputItem(item);
        }
    }

    @DeleteMapping("/modelInputItems/{id}")
    boolean deleteModelInputItem(@PathVariable("id") Long id);

    @PutMapping("/modelInputItems")
    boolean updateModelInputItem(@RequestBody IModelInputItem modelInputItem);

    @PostMapping("/modelInputItems")
    IModelInputItem createModelInputItem(@RequestBody IModelInputItem modelInputItem);

    @GetMapping("/modelOutputChartOrders")
    IModelOutputChartOrder getModelOutputChartOrder(
            @RequestParam(value = "modelId", required = false) Long modelId,
            @RequestParam(value = "label", required = false) String label);

    @PostMapping("/modelOutputChartOrders")
    IModelOutputChartOrder createModelOutputChartOrder(
            @RequestBody IModelOutputChartOrder modelOutputChartOrder);

    @PutMapping("/modelOutputChartOrders")
    boolean updateModelOutputChartOrder(@RequestBody IModelOutputChartOrder modelOutputChartOrder);

    @DeleteMapping("/modelOutputChartOrders/{id}")
    boolean deleteModelOutputChartOrder(@PathVariable("id") Long id);

    @PostMapping("/modelOutputItems")
    IModelOutputItem createModelOutputItem(@RequestBody IModelOutputItem modelOutputItem);

    @PutMapping("/modelOutputItems")
    boolean updateModelOutputItem(@RequestBody IModelOutputItem modelOutputItem);

    @DeleteMapping("/modelOutputItems")
    boolean deleteModelOutputItem(@RequestBody IModelOutputItem modelOutputItem);

    @GetMapping("/modelOutputItems/{id}")
    IModelOutputItem getOutputItem(@PathVariable("id") Long id);

    @PostMapping("/modelPositions")
    IModelPosition createModelPosition(@RequestBody IModelPosition modelPosition);

    @PutMapping("/modelPositions")
    boolean updateModelPosition(@RequestBody IModelPosition modelPosition);

    @DeleteMapping("/modelPositions")
    boolean deleteModelPosition(@RequestBody IModelPosition modelPosition);

    @GetMapping("/modelPositions")
    List<IModelPosition> getModelPositionsByModelId(
            @RequestParam(value = "modelId", required = false) Long modelId);

    default void setModelPositions(Long modelId, List<Long> positionIds) {
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
