package org.xcolab.service.modeling.domain;

import org.xcolab.client.modeling.pojo.IModelInputGroup;

import java.util.List;
import java.util.Optional;

public interface ModelInputGroupDao {

    Optional<IModelInputGroup> get(long id);

    List<IModelInputGroup> list();

    List<IModelInputGroup> findByGiven(Long parentGroupPk, Long modelId);

    IModelInputGroup create(IModelInputGroup pojo);

    boolean update(IModelInputGroup pojo);

    boolean delete(long id);
}
