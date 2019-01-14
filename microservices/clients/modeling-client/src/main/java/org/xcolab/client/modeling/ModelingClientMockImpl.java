package org.xcolab.client.modeling;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.modeling.pojo.IModelGlobalPreference;
import org.xcolab.client.modeling.pojo.IModelInputGroup;
import org.xcolab.client.modeling.pojo.IModelInputItem;
import org.xcolab.client.modeling.pojo.IModelOutputChartOrder;
import org.xcolab.client.modeling.pojo.IModelOutputItem;
import org.xcolab.client.modeling.pojo.IModelPosition;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class ModelingClientMockImpl implements IModelingClient {

    @Override
    public IModelGlobalPreference getModelPreference(Long modelId) {
        return null;
    }

    @Override
    public boolean updatePreferences(IModelGlobalPreference modelGlobalPreference) {
        return false;
    }

    @Override
    public List<IModelInputGroup> getInputGroups(Long modelId, Long parentGroupPk) {
        return Collections.emptyList();
    }

    @Override
    public IModelInputGroup createModelInputGroup(IModelInputGroup modelInputGroup) {
        return null;
    }

    @Override
    public boolean updateModelInputGroup(IModelInputGroup modelInputGroup) {
        return false;
    }

    @Override
    public IModelInputGroup getModelInputGroup(Long parentGroupId) {
        return null;
    }

    @Override
    public boolean deleteModelInputGroup(Long groupId) {
        return false;
    }

    @Override
    public List<IModelInputItem> getModelInputItems(Long modelInputGroupPk, Long modelId,
            Long modelInputId) {
        return Collections.emptyList();
    }

    @Override
    public boolean deleteModelInputItem(Long id) {
        return false;
    }

    @Override
    public boolean updateModelInputItem(IModelInputItem modelInputItem) {
        return false;
    }

    @Override
    public IModelInputItem createModelInputItem(IModelInputItem modelInputItem) {
        return null;
    }

    @Override
    public IModelOutputChartOrder getModelOutputChartOrder(Long modelId, String label) {
        return null;
    }

    @Override
    public IModelOutputChartOrder createModelOutputChartOrder(
            IModelOutputChartOrder modelOutputChartOrder) {
        return null;
    }

    @Override
    public boolean updateModelOutputChartOrder(IModelOutputChartOrder modelOutputChartOrder) {
        return false;
    }

    @Override
    public boolean deleteModelOutputChartOrder(Long id) {
        return false;
    }

    @Override
    public IModelOutputItem createModelOutputItem(IModelOutputItem modelOutputItem) {
        return null;
    }

    @Override
    public boolean updateModelOutputItem(IModelOutputItem modelOutputItem) {
        return false;
    }

    @Override
    public boolean deleteModelOutputItem(IModelOutputItem modelOutputItem) {
        return false;
    }

    @Override
    public IModelOutputItem getOutputItem(Long id) {
        return null;
    }

    @Override
    public IModelPosition createModelPosition(IModelPosition modelPosition) {
        return null;
    }

    @Override
    public boolean updateModelPosition(IModelPosition modelPosition) {
        return false;
    }

    @Override
    public boolean deleteModelPosition(IModelPosition modelPosition) {
        return false;
    }

    @Override
    public List<IModelPosition> getModelPositionsByModelId(Long modelId) {
        return Collections.emptyList();
    }
}
