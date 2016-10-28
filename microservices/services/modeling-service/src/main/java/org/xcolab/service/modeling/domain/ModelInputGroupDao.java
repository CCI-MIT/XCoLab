package org.xcolab.service.modeling.domain;

import org.xcolab.model.tables.pojos.ModelInputGroup;

import java.util.List;
import java.util.Optional;

public interface ModelInputGroupDao {

    Optional<ModelInputGroup> get(long id);

    List<ModelInputGroup> list();

    ModelInputGroup create(ModelInputGroup pojo);

    boolean update(ModelInputGroup pojo);

    boolean delete(long id);
}
