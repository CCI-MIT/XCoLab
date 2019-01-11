package org.xcolab.service.modeling.domain;

import org.xcolab.client.modeling.pojo.IModelPosition;

import java.util.List;
import java.util.Optional;

public interface ModelPositionDao {

    Optional<IModelPosition> get(long id);

    List<IModelPosition> list();

    List<IModelPosition> findByGiven(Long modelId);

    IModelPosition create(IModelPosition pojo);

    boolean update(IModelPosition pojo);

    boolean delete(long id);
}
