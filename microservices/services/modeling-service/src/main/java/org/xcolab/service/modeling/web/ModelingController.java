package org.xcolab.service.modeling.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.ModelCategory;
import org.xcolab.model.tables.pojos.ModelDiscussion;
import org.xcolab.model.tables.pojos.ModelGlobalPreference;
import org.xcolab.model.tables.pojos.ModelInputGroup;
import org.xcolab.model.tables.pojos.ModelInputItem;
import org.xcolab.model.tables.pojos.ModelOutputChartOrder;
import org.xcolab.model.tables.pojos.ModelOutputItem;
import org.xcolab.model.tables.pojos.ModelPosition;
import org.xcolab.service.modeling.domain.ModelCategoryDao;
import org.xcolab.service.modeling.domain.ModelDiscussionDao;
import org.xcolab.service.modeling.domain.ModelGlobalPreferenceDao;
import org.xcolab.service.modeling.domain.ModelInputGroupDao;
import org.xcolab.service.modeling.domain.ModelInputItemDao;
import org.xcolab.service.modeling.domain.ModelOutputChartOrderDao;
import org.xcolab.service.modeling.domain.ModelOutputItemDao;
import org.xcolab.service.modeling.domain.ModelPositionDao;
import org.xcolab.service.modeling.exceptions.NotFoundException;

import java.util.Collections;
import java.util.List;

@RestController
public class ModelingController {

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

    @GetMapping("/models/{modelId}/preferences")
    public List<ModelGlobalPreference> listPreferences(@PathVariable long modelId) {
        return Collections.singletonList(modelGlobalPreferenceDao.getByModelId(modelId)
                .orElseGet(() -> {
                    ModelGlobalPreference modelGlobalPreference = new ModelGlobalPreference();
                    modelGlobalPreference.setModelId(modelId);
                    modelGlobalPreference.setUsesCustomInputs(false);
                    return modelGlobalPreferenceDao.create(modelGlobalPreference);
                }));
    }

//    modelCategoryResource = new RestResource1<>(modelingService, "modelCategories", ModelCategoryDto.TYPES);
//    modelDiscussionResource = new RestResource1<>(modelingService, "modelDiscussions", ModelDiscussionDto.TYPES);
//    modelInputGroupResource = new RestResource1<>(modelingService, "modelInputGroups", ModelInputGroupDto.TYPES);
//    modelInputItemResource = new RestResource1<>(modelingService, "modelInputItems", ModelInputItemDto.TYPES);
//    modelOutputChartOrderResource = new RestResource1<>(modelingService, "modelOutputChartOrders", ModelOutputChartOrderDto.TYPES);
//    modelOutputItemResource = new RestResource1<>(modelingService, "modelOutputItems", ModelOutputItemDto.TYPES);
//    modelPositionResource = new RestResource1<>(modelingService, "modelPositions", ModelPositionDto.TYPES);

    @GetMapping("/modelCategories")
    public List<ModelCategory> listModelCategories() {
        return modelCategoryDao.list();
    }

    @GetMapping("/modelCategories/{id}")
    public ModelCategory getModelCategory(@PathVariable long id) throws NotFoundException {
        return modelCategoryDao.get(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/modelCategories")
    public ModelCategory createModelCategory(@RequestBody ModelCategory pojo) {
        return modelCategoryDao.create(pojo);
    }

    @PutMapping("/modelCategories/{id}")
    public boolean updateModelCategory(@PathVariable long id,
            @RequestBody ModelCategory pojo) {
        return modelCategoryDao.update(pojo);
    }

    @DeleteMapping("/modelCategories/{id}")
    public boolean deleteModelCategory(@PathVariable long id) {
        return modelCategoryDao.delete(id);
    }

    @GetMapping("/modelDiscussions")
    public List<ModelDiscussion> listModelDiscussions() {
        return modelDiscussionDao.list();
    }

    @GetMapping("/modelDiscussions/{id}")
    public ModelDiscussion getModelDiscussion(@PathVariable long id) throws NotFoundException {
        return modelDiscussionDao.get(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/modelDiscussions")
    public ModelDiscussion createModelDiscussion(@RequestBody ModelDiscussion pojo) {
        return modelDiscussionDao.create(pojo);
    }

    @PutMapping("/modelDiscussions/{id}")
    public boolean updateModelDiscussion(@PathVariable long id,
            @RequestBody ModelDiscussion pojo) {
        return modelDiscussionDao.update(pojo);
    }

    @DeleteMapping("/modelDiscussions/{id}")
    public boolean deleteModelDiscussion(@PathVariable long id) {
        return modelDiscussionDao.delete(id);
    }

    @GetMapping("/modelInputGroups")
    public List<ModelInputGroup> listModelInputGroups() {
        return modelInputGroupDao.list();
    }

    @GetMapping("/modelInputGroups/{id}")
    public ModelInputGroup getModelInputGroup(@PathVariable long id) throws NotFoundException {
        return modelInputGroupDao.get(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/modelInputGroups")
    public ModelInputGroup createModelInputGroup(@RequestBody ModelInputGroup pojo) {
        return modelInputGroupDao.create(pojo);
    }

    @PutMapping("/modelInputGroups/{id}")
    public boolean updateModelInputGroup(@PathVariable long id,
            @RequestBody ModelInputGroup pojo) {
        return modelInputGroupDao.update(pojo);
    }

    @DeleteMapping("/modelInputGroups/{id}")
    public boolean deleteModelInputGroup(@PathVariable long id) {
        return modelInputGroupDao.delete(id);
    }

    @GetMapping("/modelInputItems")
    public List<ModelInputItem> listModelInputItems() {
        return modelInputItemDao.list();
    }

    @GetMapping("/modelInputItems/{id}")
    public ModelInputItem getModelInputItem(@PathVariable long id) throws NotFoundException {
        return modelInputItemDao.get(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/modelInputItems")
    public ModelInputItem createModelInputItem(@RequestBody ModelInputItem pojo) {
        return modelInputItemDao.create(pojo);
    }

    @PutMapping("/modelInputItems/{id}")
    public boolean updateModelInputItem(@PathVariable long id,
            @RequestBody ModelInputItem pojo) {
        return modelInputItemDao.update(pojo);
    }

    @DeleteMapping("/modelInputItems/{id}")
    public boolean deleteModelInputItem(@PathVariable long id) {
        return modelInputItemDao.delete(id);
    }

    @GetMapping("/modelOutputChartOrders")
    public List<ModelOutputChartOrder> listModelOutputChartOrders() {
        return modelOutputChartOrderDao.list();
    }

    @GetMapping("/modelOutputChartOrders/{id}")
    public ModelOutputChartOrder getModelOutputChartOrder(@PathVariable long id) throws NotFoundException {
        return modelOutputChartOrderDao.get(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/modelOutputChartOrders")
    public ModelOutputChartOrder createModelOutputChartOrder(@RequestBody ModelOutputChartOrder pojo) {
        return modelOutputChartOrderDao.create(pojo);
    }

    @PutMapping("/modelOutputChartOrders/{id}")
    public boolean updateModelOutputChartOrder(@PathVariable long id,
            @RequestBody ModelOutputChartOrder pojo) {
        return modelOutputChartOrderDao.update(pojo);
    }

    @DeleteMapping("/modelOutputChartOrders/{id}")
    public boolean deleteModelOutputChartOrder(@PathVariable long id) {
        return modelOutputChartOrderDao.delete(id);
    }

    @GetMapping("/modelOutputItems")
    public List<ModelOutputItem> listModelOutputItems() {
        return modelOutputItemDao.list();
    }

    @GetMapping("/modelOutputItems/{id}")
    public ModelOutputItem getModelOutputItem(@PathVariable long id) throws NotFoundException {
        return modelOutputItemDao.get(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/modelOutputItems")
    public ModelOutputItem createModelOutputItem(@RequestBody ModelOutputItem pojo) {
        return modelOutputItemDao.create(pojo);
    }

    @PutMapping("/modelOutputItems/{id}")
    public boolean updateModelOutputItem(@PathVariable long id,
            @RequestBody ModelOutputItem pojo) {
        return modelOutputItemDao.update(pojo);
    }

    @DeleteMapping("/modelOutputItems/{id}")
    public boolean deleteModelOutputItem(@PathVariable long id) {
        return modelOutputItemDao.delete(id);
    }

    @GetMapping("/modelPositions")
    public List<ModelPosition> listModelPositions() {
        return modelPositionDao.list();
    }

    @GetMapping("/modelPositions/{id}")
    public ModelPosition getModelPosition(@PathVariable long id) throws NotFoundException {
        return modelPositionDao.get(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/modelPositions")
    public ModelPosition createModelPosition(@RequestBody ModelPosition pojo) {
        return modelPositionDao.create(pojo);
    }

    @PutMapping("/modelPositions/{id}")
    public boolean updateModelPosition(@PathVariable long id,
            @RequestBody ModelPosition pojo) {
        return modelPositionDao.update(pojo);
    }

    @DeleteMapping("/modelPositions/{id}")
    public boolean deleteModelPosition(@PathVariable long id) {
        return modelPositionDao.delete(id);
    }

}
