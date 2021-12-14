package org.xcolab.service.modeling.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.modeling.IModelingClient;
import org.xcolab.client.modeling.pojo.IModelCategory;
import org.xcolab.client.modeling.pojo.IModelDiscussion;
import org.xcolab.client.modeling.pojo.IModelGlobalPreference;
import org.xcolab.client.modeling.pojo.IModelInputGroup;
import org.xcolab.client.modeling.pojo.IModelInputItem;
import org.xcolab.client.modeling.pojo.IModelOutputChartOrder;
import org.xcolab.client.modeling.pojo.IModelOutputItem;
import org.xcolab.client.modeling.pojo.IModelPosition;
import org.xcolab.model.tables.pojos.ModelGlobalPreferenceImpl;
import org.xcolab.service.modeling.domain.ModelCategoryDao;
import org.xcolab.service.modeling.domain.ModelDiscussionDao;
import org.xcolab.service.modeling.domain.ModelGlobalPreferenceDao;
import org.xcolab.service.modeling.domain.ModelInputGroupDao;
import org.xcolab.service.modeling.domain.ModelInputItemDao;
import org.xcolab.service.modeling.domain.ModelOutputChartOrderDao;
import org.xcolab.service.modeling.domain.ModelOutputItemDao;
import org.xcolab.service.modeling.domain.ModelPositionDao;
import org.xcolab.service.modeling.exceptions.NotFoundException;

import java.util.List;

@RestController
public class ModelingController implements IModelingClient {

    private final ModelCategoryDao modelCategoryDao;
    private final ModelDiscussionDao modelDiscussionDao;
    private final ModelGlobalPreferenceDao modelGlobalPreferenceDao;
    private final ModelInputGroupDao modelInputGroupDao;
    private final ModelInputItemDao modelInputItemDao;
    private final ModelOutputChartOrderDao modelOutputChartOrderDao;
    private final ModelOutputItemDao modelOutputItemDao;
    private final ModelPositionDao modelPositionDao;

    @Autowired
    public ModelingController(ModelCategoryDao modelCategoryDao,
            ModelDiscussionDao modelDiscussionDao,
            ModelGlobalPreferenceDao modelGlobalPreferenceDao,
            ModelInputGroupDao modelInputGroupDao, ModelInputItemDao modelInputItemDao,
            ModelOutputChartOrderDao modelOutputChartOrderDao,
            ModelOutputItemDao modelOutputItemDao, ModelPositionDao modelPositionDao) {
        this.modelCategoryDao = modelCategoryDao;
        this.modelDiscussionDao = modelDiscussionDao;
        this.modelGlobalPreferenceDao = modelGlobalPreferenceDao;
        this.modelInputGroupDao = modelInputGroupDao;
        this.modelInputItemDao = modelInputItemDao;
        this.modelOutputChartOrderDao = modelOutputChartOrderDao;
        this.modelOutputItemDao = modelOutputItemDao;
        this.modelPositionDao = modelPositionDao;
    }

    @Override
    @GetMapping("/models/{modelId}/preferences")
    public IModelGlobalPreference getModelPreference(@PathVariable Long modelId) {
        return modelGlobalPreferenceDao.getByModelId(modelId)
                .orElseGet(() -> {
                    IModelGlobalPreference modelGlobalPreference = new ModelGlobalPreferenceImpl();
                    modelGlobalPreference.setModelId(modelId);
                    modelGlobalPreference.setUsesCustomInputs(false);
                    return modelGlobalPreferenceDao.create(modelGlobalPreference);
                });
    }

    @Override
    @PutMapping("/models/preferences")
    public boolean updatePreferences(@RequestBody IModelGlobalPreference modelGlobalPreference) {
        return modelGlobalPreferenceDao.update(modelGlobalPreference);
    }

    //---------------
    @GetMapping("/modelCategories")
    public List<IModelCategory> listModelCategories() {
        return modelCategoryDao.list();
    }

    @GetMapping("/modelCategories/{id}")
    public IModelCategory getModelCategory(@PathVariable long id) throws NotFoundException {
        return modelCategoryDao.get(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/modelCategories")
    public IModelCategory createModelCategory(@RequestBody IModelCategory pojo) {
        return modelCategoryDao.create(pojo);
    }

    @PutMapping("/modelCategories/{id}")
    public boolean updateModelCategory(@PathVariable long id,
            @RequestBody IModelCategory pojo) {
        return modelCategoryDao.update(pojo);
    }

    @DeleteMapping("/modelCategories/{id}")
    public boolean deleteModelCategory(@PathVariable long id) {
        return modelCategoryDao.delete(id);
    }

    //---------------

    //---------------
    @GetMapping("/modelDiscussions")
    public List<IModelDiscussion> listModelDiscussions() {
        return modelDiscussionDao.list();
    }

    @GetMapping("/modelDiscussions/{id}")
    public IModelDiscussion getModelDiscussion(@PathVariable long id) {
        return modelDiscussionDao.get(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/modelDiscussions")
    public IModelDiscussion createModelDiscussion(@RequestBody IModelDiscussion pojo) {
        return modelDiscussionDao.create(pojo);
    }

    @PutMapping("/modelDiscussions/{id}")
    public boolean updateModelDiscussion(@PathVariable long id,
            @RequestBody IModelDiscussion pojo) {
        return modelDiscussionDao.update(pojo);
    }

    @DeleteMapping("/modelDiscussions/{id}")
    public boolean deleteModelDiscussion(@PathVariable long id) {
        return modelDiscussionDao.delete(id);
    }
    //---------------

    @Override
    @GetMapping("/modelInputGroups")
    public List<IModelInputGroup> getInputGroups(@RequestParam(required = false) Long modelId,
            @RequestParam(required = false) Long parentGroupPk) {
        return modelInputGroupDao.findByGiven(parentGroupPk, modelId);
    }

    @Override
    @GetMapping("/modelInputGroups/{parentGroupId}")
    public IModelInputGroup getModelInputGroup(@PathVariable Long parentGroupId) {
        return modelInputGroupDao.get(parentGroupId).orElseThrow(NotFoundException::new);
    }

    @Override
    @PostMapping("/modelInputGroups")
    public IModelInputGroup createModelInputGroup(@RequestBody IModelInputGroup modelInputGroup) {
        return modelInputGroupDao.create(modelInputGroup);
    }

    @Override
    @PutMapping("/modelInputGroups")
    public boolean updateModelInputGroup(@RequestBody IModelInputGroup modelInputGroup) {
        return modelInputGroupDao.update(modelInputGroup);
    }

    @Override
    @DeleteMapping("/modelInputGroups/{groupId}")
    public boolean deleteModelInputGroup(@PathVariable Long groupId) {
        return modelInputGroupDao.delete(groupId);
    }

    @Override
    @GetMapping("/modelInputItems")
    public List<IModelInputItem> getModelInputItems(
            @RequestParam(required = false) Long modelInputGroupPk,
            @RequestParam(required = false) Long modelId,
            @RequestParam(required = false) Long modelInputId) {
        return modelInputItemDao.findByGiven(modelInputGroupPk, modelId, modelInputId);
    }

    @GetMapping("/modelInputItems/{id}")
    public IModelInputItem getModelInputItem(@PathVariable long id) throws NotFoundException {
        return modelInputItemDao.get(id).orElseThrow(NotFoundException::new);
    }

    @Override
    @PostMapping("/modelInputItems")
    public IModelInputItem createModelInputItem(@RequestBody IModelInputItem modelInputItem) {
        return modelInputItemDao.create(modelInputItem);
    }

    @Override
    @PutMapping("/modelInputItems")
    public boolean updateModelInputItem(@RequestBody IModelInputItem modelInputItem) {
        return modelInputItemDao.update(modelInputItem);
    }

    @Override
    @DeleteMapping("/modelInputItems/{id}")
    public boolean deleteModelInputItem(@PathVariable Long id) {
        return modelInputItemDao.delete(id);
    }

    @Override
    @GetMapping("/modelOutputChartOrders")
    public IModelOutputChartOrder getModelOutputChartOrder(
            @RequestParam(required = false) Long modelId,
            @RequestParam(required = false) String label) {
        return modelOutputChartOrderDao.findByGiven(modelId, label).stream().findFirst()
                .orElse(null);
    }
    
    @GetMapping("/modelOutputChartOrders/{id}")
    public IModelOutputChartOrder getModelOutputChartOrder(@PathVariable long id)
            throws NotFoundException {
        return modelOutputChartOrderDao.get(id).orElseThrow(NotFoundException::new);
    }

    @Override
    @PostMapping("/modelOutputChartOrders")
    public IModelOutputChartOrder createModelOutputChartOrder(@RequestBody
            IModelOutputChartOrder modelOutputChartOrder) {
        return modelOutputChartOrderDao.create(modelOutputChartOrder);
    }

    @Override
    @PutMapping("/modelOutputChartOrders")
    public boolean updateModelOutputChartOrder(
            @RequestBody IModelOutputChartOrder modelOutputChartOrder) {
        return modelOutputChartOrderDao.update(modelOutputChartOrder);
    }

    @Override
    @DeleteMapping("/modelOutputChartOrders/{id}")
    public boolean deleteModelOutputChartOrder(@PathVariable Long id) {
        return modelOutputChartOrderDao.delete(id);
    }

    @GetMapping("/modelOutputItems")
    public List<IModelOutputItem> listModelOutputItems() {
        return modelOutputItemDao.list();
    }

    @Override
    @GetMapping("/modelOutputItems/{id}")
    public IModelOutputItem getOutputItem(@PathVariable Long id) {
        return modelOutputItemDao.get(id).orElseThrow(NotFoundException::new);
    }

    @Override
    @PostMapping("/modelOutputItems")
    public IModelOutputItem createModelOutputItem(@RequestBody IModelOutputItem modelOutputItem) {
        return modelOutputItemDao.create(modelOutputItem);
    }

    @Override
    @PutMapping("/modelOutputItems")
    public boolean updateModelOutputItem(@RequestBody IModelOutputItem modelOutputItem) {
        return modelOutputItemDao.update(modelOutputItem);
    }

    @Override
    @DeleteMapping("/modelOutputItems")
    public boolean deleteModelOutputItem(@RequestBody IModelOutputItem modelOutputItem) {
        return modelOutputItemDao.delete(modelOutputItem.getModelOutputItemId());
    }

    @Override
    @GetMapping("/modelPositions")
    public List<IModelPosition> getModelPositionsByModelId(
            @RequestParam(required = false) Long modelId) {
        return modelPositionDao.findByGiven(modelId);
    }
    
    @GetMapping("/modelPositions/{id}")
    public IModelPosition getModelPosition(@PathVariable long id) throws NotFoundException {
        return modelPositionDao.get(id).orElseThrow(NotFoundException::new);
    }

    @Override
    @PostMapping("/modelPositions")
    public IModelPosition createModelPosition(@RequestBody IModelPosition modelPosition) {
        return modelPositionDao.create(modelPosition);
    }

    @Override
    @PutMapping("/modelPositions")
    public boolean updateModelPosition(@RequestBody IModelPosition modelPosition) {
        return modelPositionDao.update(modelPosition);
    }

    @Override
    @DeleteMapping("/modelPositions")
    public boolean deleteModelPosition(@RequestBody IModelPosition modelPosition) {
        return modelPositionDao.delete(modelPosition.getPositionId());
    }
}
