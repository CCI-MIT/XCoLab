package org.xcolab.service.modeling.domain;

import org.xcolab.client.modeling.pojo.IModelInputItem;

import java.util.List;
import java.util.Optional;

public interface ModelInputItemDao {

    Optional<IModelInputItem> get(long id);

    List<IModelInputItem> list();

    List<IModelInputItem> findByGiven(Long modelInputGroupPk, Long modelId, Long modelInputId);

    IModelInputItem create(IModelInputItem pojo);

    boolean update(IModelInputItem pojo);

    boolean delete(long id);
}
