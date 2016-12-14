package org.xcolab.service.modeling.domain;

import org.xcolab.model.tables.pojos.ModelPosition;

import java.util.List;
import java.util.Optional;

public interface ModelPositionDao {

    Optional<ModelPosition> get(long id);

    List<ModelPosition> list();

    List<ModelPosition> findByGiven(Long modelId);

    ModelPosition create(ModelPosition pojo);

    boolean update(ModelPosition pojo);

    boolean delete(long id);
}
